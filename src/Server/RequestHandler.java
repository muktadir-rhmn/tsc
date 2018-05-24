package Server;

import utility.*;
import utility.SMS.SmsClient;
import utility.Teacher.Course;
import utility.notification.Notification;
import utility.notification.NotificationToReceive;
import utility.notification.NotificationToSend;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by MUKTADIR on 11/21/2015.
 */
public class RequestHandler { //this class is to handle the requests sent by the clients;
    /*
    This class will be used to communicate with the database
    * */
    /*To communicate with the data base, we must:
    1. Create a driver object that will be used to communicate with the database
    2. Establish Connection
    3.
    * */

    private Connection connection;//connection is an interface
    private Statement statement;//statement is also an interface
    private Statement statementForInsert;
    private ResultSet resultSet; //result set is also an interface
    private String sql; //to temporarily hold the sql query
    private SmsClient smsClient; //the sms sender


    RequestHandler() {
        try {

            Class.forName("com.mysql.jdbc.Driver"); //probably this loads the framework

            connection = DriverManager.getConnection(Const.database, Const.dbUserName, Const.dbPassword);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //statementForInsert = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            smsClient = new SmsClient(Const.host, Const.smsPort);
            smsClient.login(Const.smsUserName, Const.smsPassword);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public LoginInfo logIn(String id, String password) throws SQLException {
        sql = "SELECT `studentName` FROM currentstudents WHERE `studentId`='" + id + "' AND `password` = '" + password + "'";
        statement.executeQuery(sql);
        resultSet = statement.getResultSet();
        if (resultSet.next()) {
            LoginInfo ret = new LoginInfo("student", resultSet.getString(1));
            return ret;
        }

        //the head will have two id in the currentteachers "head" and teacherId
        sql = "SELECT `teacherName` FROM currentteachers WHERE `teacherId` = '" + id + "' AND password = '"+ password +"'";
        statement.executeQuery(sql);
        resultSet = statement.getResultSet();
        if (resultSet.next()) {
            LoginInfo ret;
            if(id.equals("head"))
                ret = new LoginInfo("head", resultSet.getString(1));
            else
                ret = new LoginInfo("teacher", resultSet.getString(1));
            return ret;
        }
        return  null;
    }

    public void setNotification(Notification noti) throws SQLException, UnsupportedEncodingException {
        final int ALL = -1;
        int level = ALL, term = ALL ; String section = null; //destination of the notification
        String from = null; //source
        String title = null, body = null; //Structure of the utility

        /*Creating the notification*/

        String t;
        NotificationToReceive notification = (NotificationToReceive) noti;
        t =  notification.level;
        if(!t.equals("All")){
            level = Integer.parseInt(t);
            t  =  notification.term;
            if(!t.equals("All")){
                term = Integer.parseInt(t);
            }
        }
        section =  notification.section;
        from = notification.from;
        title = notification.title + "[" + from+ "]";
        body = notification.body;
        /*end of creation of notification*/

        /*Inserting utility into the database*/
        sql = "SELECT `studentId`,`phone` FROM currentstudents";
        if(level  != ALL) {
            sql = sql + " WHERE level = " + level;
            if(term != ALL) {
                sql = sql +" AND term = " + term ;

                if(section.equals("A")) sql = sql + " AND (Section = 'A2' OR Section = 'A1')";
                else if (section.equals("A1")) sql = sql + " AND ( Section = 'A1')";
                else if(section.equals("A2")) sql = sql + " AND (Section = 'A2')";
                else if(section.equals("B")) sql = sql + " AND (Section = 'B2' OR Section = 'B1')";
                else if(section.equals("B1")) sql = sql + " AND ( Section = 'B1')";
                else if(section.equals("B2")) sql = sql + " AND ( Section = 'B2')";
            }
        }

        statement.executeQuery(sql);
        ResultSet recipientTable = statement.getResultSet();

        Statement statement1 = connection.createStatement();
        while (recipientTable.next()){
            System.out.println();
            System.out.println(recipientTable.getString(1));

            sql = "INSERT INTO `notifications`( `To`, `From`,  `Title`, `Body`) VALUES ('"+ recipientTable.getString(1) + "', '" + from + "' , '"+ title +"' , '" + body+ "')";

            statement1.executeUpdate(sql);

            //smsClient.sendMessage(recipientTable.getString("2"), body);
        }
        /*end of inserting data into the database*/
    }

    public ArrayList<NotificationToSend> getNotification(String id) throws SQLException {
        ArrayList<NotificationToSend> list = new ArrayList<>();

        sql = "SELECT `id`, `Time`, `From`, `Seen`, `Title`, `Body` FROM notifications WHERE Seen = 0 AND `To` = '" + id + "'";
        resultSet = statement.executeQuery(sql);//execute the statement and retrieve data from the database and then store it in resultset


        while (resultSet.next()){
            NotificationToSend tmp = new NotificationToSend();
            tmp.time  = resultSet.getString("Time");
            tmp.from = resultSet.getString("From");
            tmp.title = resultSet.getString("Title");
            tmp.body = resultSet.getString("Body");
            list.add(tmp);
            if(!id.equals("head")) { //the seen of head will not be true unless he approve the result
                resultSet.updateInt("Seen", 1);
                resultSet.updateRow();
            }
        }
        return list;
    }

    public void setResult(Result result) throws SQLException {
        /*
        * this method will do 2 things:
        * 1. Store the result in the database
        * 2. Fill up the notification table
        * */

        Enumeration<String> enumeration = result.marks.keys();
        while (enumeration.hasMoreElements()){
            String roll = enumeration.nextElement();
            int mark = result.marks.get(roll);

            sql = "INSERT INTO results(`StudentId`, `CourseTeacher`, `CourseId`, `ResultType`, `Marks`) VALUES("
                    + "'"+ roll + "', "
                    + "'"+ result.courseTeacher + "', "
                    + "'"+ result.course + "', "
                    + "'"+ result.resultType + "', "
                    + mark
                    +")";
            statement.executeUpdate(sql);
        }


        String title = result.resultType + "[" + result.course + "] Result requires approval";
        String body = result.resultType + " Result from the course " + result.course + " requires " +
                "your approval to be published";
        sql = "INSERT INTO notifications(`To`, `From`, `Title`, `Body`) VALUES('head', "
                +"'"+ result.courseTeacher +"', "
                +"'"+ title +"',"
                +"'"+ body+"'"
                + ")";

        statement.executeUpdate(sql);
    }


    private void insertNotification(String from, String to, String title, String body) throws SQLException {
        sql = "INSERT INTO notifications(`To`, `From`, `Title`, `Body`) VALUES(" +
                "'" + to +"', "
                +"'"+ from +"', "
                +"'"+ title +"',"
                +"'"+ body+"'"
                + ")";

        statementForInsert.executeUpdate(sql);
    }
    public void approve(String teacherName, String titleOfNotification) throws SQLException, UnsupportedEncodingException {
        //extract courseNo and result type
        String courseNo, resultType;
        String[] tmp = titleOfNotification.split("]");
        tmp = tmp[0].split("\\[");
        resultType = tmp[0];
        courseNo = tmp[1];


        /*
        * select each result from the result table and make it approved
        * then add notification in the notification table.
        * also make seen the notification
        * */
        sql = "UPDATE `project`.`notifications` SET `Seen` = '1' WHERE `notifications`.`from` = '" + teacherName + "' and `title` = '" + titleOfNotification +"'";
        statement.executeUpdate(sql);

        Statement resultTableStatement = connection.createStatement();
        Statement notificationStatement = connection.createStatement();

        sql = "SELECT `StudentId`, `CourseTeacher`, `CourseId`, `ResultType`, `Marks`,`Approved` FROM results " +
                "WHERE CourseTeacher='"+ teacherName + "' AND `CourseId` = '" + courseNo +"' AND " +
                "`ResultType` ='" + resultType+"'";
        resultTableStatement.execute(sql);
        resultSet = resultTableStatement.getResultSet();

        Statement tmpStatement = connection.createStatement();//to fetch the phone number
        while (resultSet.next()){
            String title = resultType + "[" + courseNo + "]" + "result has been published";
            String body = resultType + " result of the course " + courseNo + " by " + teacherName + " has been pulished. \n" +
                    "You have got " + resultSet.getInt("Marks") + " marks in the exam.";
            sql = "INSERT INTO `notifications`( `To`, `From`,  `Title`, `Body`) " +
                    "VALUES ('"+ resultSet.getString("StudentId") + "', '" + teacherName + "' , '"+ title +"' , '" + body+ "')";
            notificationStatement.executeUpdate(sql);

            /*sms sending
            tmpStatement.executeQuery("SELECT `phone` FROM `currentstudents` WHERE `studentId` = '" + resultSet.getString("StudentId") + "'");
            ResultSet tmpResultset = tmpStatement.getResultSet();
            smsClient.sendMessage(tmpResultset.getString(1), body);
            */
        }

        sql = "UPDATE `results` SET `Approved`=1 WHERE `CourseTeacher` = '" + teacherName + "' AND `CourseId`='"+ courseNo+"'";
        resultTableStatement.executeUpdate(sql);

    }

    public ArrayList<Course> sendCourses(String teacherId) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();

        sql = "SELECT `CourseId`, `Level`, `Term`, `Section` FROM `coursesbyteacher` WHERE `TeacherId` = '" + teacherId + "'";
        statement.executeQuery(sql);
        resultSet = statement.getResultSet();

        while (resultSet.next()){
            Course course = new Course();
            course.courseId = resultSet.getString(1);
            course.level = resultSet.getInt(2);
            course.term = resultSet.getInt(3);
            course.section = resultSet.getString(4);

            courses.add(course);
        }

        return courses;
    }

    public void receiveFile(InputStream is, File fileName, String CourseId, String teacherId) throws IOException {
        File file = new File( "File//" + CourseId + " - "+ teacherId + " - " +fileName.getName() );
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos  = new BufferedOutputStream(fos);


        long fileSize = fileName.length();
        long current = 0;
        int bytesRead = 0;
        byte[] buff  = new byte[10000];
        while(current < fileSize){
            bytesRead = is.read(buff);
            //if (bytesRead == -1) break;
            bos.write(buff, 0, bytesRead);
            current += bytesRead;
        }
        bos.close();
        System.out.println("received successfully");
    }

    public void sendFile(String fileName, ObjectOutputStream oos, OutputStream os) throws Exception {
        File file = new File("File//" + fileName);
        System.out.println("File Name: " + fileName);
        if(!file.exists()){
            System.out.println("file don't exists");
            return;
        }

        oos.writeObject(file);

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        byte[] buff;
        int current = 0;
        long fileSize = file.length();

        while (current < fileSize){
            int size  = 10000;
            if(bis.available() < size) size = bis.available();

            buff = new byte[size];
            bis.read(buff, 0, size);
            os.write(buff);
            current += size;
        }
        os.flush();
        bis.close();
        System.out.println("File sent successfully...");
    }
}
;