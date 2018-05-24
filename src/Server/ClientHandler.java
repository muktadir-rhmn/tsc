package Server;

import utility.LoginInfo;
import utility.Result;
import utility.notification.Notification;
import utility.notification.NotificationToSend;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by MUKTADIR on 11/21/2015.
 */
public class ClientHandler implements Runnable{

    private Socket client;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private RequestHandler requestHandler;

    ClientHandler(Socket c, RequestHandler r) throws IOException {
        client = c;
        requestHandler = r;
        oos = new ObjectOutputStream(client.getOutputStream());
        ois = new ObjectInputStream(client.getInputStream());

        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            /*check username and password*/
            LoginInfo loginInfo = null;
            String userName = null;
            while (loginInfo == null){
                userName = (String) ois.readObject();
                if (userName.equals("logout")) return;
                String password = (String) ois.readObject();

                synchronized (requestHandler) {
                    loginInfo = requestHandler.logIn(userName, password);
                    oos.writeObject(loginInfo);
                }
            }
            /*end of login*/

            String req;
            while (true) {
                req = (String) ois.readObject();
                System.out.println(req);
                synchronized (requestHandler) {

                    if (req.equals( "logout") ) return;
                    else if (req.equals("sendNotification")){
                        ArrayList<NotificationToSend> not = requestHandler.getNotification(userName);
                        oos.writeObject(not);
                    }  else if (req.equals("setNotification")){
                        requestHandler.setNotification((Notification) ois.readObject());
                        System.out.println("Set Notification Properly");
                    } else if (req.equals("setResult")) {
                        Result result = (Result) ois.readObject();
                        requestHandler.setResult(result);
                    }else if(req.equals("approveResult")){
                        String teacherName = (String) ois.readObject();
                        String title =(String) ois.readObject();
                        requestHandler.approve(teacherName, title);
                    }
                    else if(req.equals("sendCourses")){
                        oos.writeObject(requestHandler.sendCourses(userName));
                    }
                    else if(req.equals("receiveFile")){
                        String courseNo = (String) ois.readObject();
                        File name = (File) ois.readObject();
                        requestHandler.receiveFile(client.getInputStream(), name, courseNo, userName);

                    }
                    else if (req.equals("sendFile")){
                        String fileName = (String) ois.readObject();
                        requestHandler.sendFile(fileName, oos, client.getOutputStream());
                    }
                }
                System.out.println("end of request");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
