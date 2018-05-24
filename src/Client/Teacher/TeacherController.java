package Client.Teacher;

/**
 * Created by MUKTADIR on 12/2/2015.
 */


import Client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import utility.Const;
import utility.Result;
import utility.Teacher.Course;
import utility.Teacher.ResultTableItem;
import utility.notification.NotificationToReceive;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TeacherController {
/*****************************************************************************************/
    /**
     * Notification Tab
     */
    @FXML
    private Tab tabSendNotification;

    @FXML
    private ComboBox<String> cboCourseNo;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtBody;

    @FXML
    private Button btnSend;
    @FXML
    private Button btnReset;

    @FXML
    void reset(ActionEvent event) {
        resetNotificationForm();
    }

    @FXML
    void sendNotification(ActionEvent event) throws IOException {
        /* checking the validity*/
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(cboCourseNo.getValue() == null){
            alert.setContentText("Please select a course.");
            alert.showAndWait();
            return;
        }
        else if(txtTitle.getText().equals("")){
            alert.setContentText("Please enter a suitable Title.");
            alert.showAndWait();
            return;
        }
        else if(txtBody.getText().equals("")){
            alert.setContentText("Please enter a Body.");
            alert.showAndWait();
            return;
        }
        /*end of checking*/

        NotificationToReceive notification = new NotificationToReceive();
        int i = cboCourseNo.getItems().indexOf(cboCourseNo.getValue());
        notification.level = courses.get(i).level + "";
        notification.term = courses.get(i).term + "";
        notification.section = courses.get(i).section;
        notification.title = txtTitle.getText();
        notification.body = txtBody.getText();
        notification.from = main.username;
        main.oos.writeObject("setNotification");
        main.oos.writeObject(notification);

        resetNotificationForm();
    }
    /** end of notification Tab*/
/*****************************************************************************************/

    /** Upload Result Tab*/
    @FXML
    private Tab tabUploadResult;

    @FXML
    private TableView<ResultTableItem> tableResult;

    @FXML
    private TableColumn<ResultTableItem, String> columnResultStudentId;

    @FXML
    private TextField txtResultRoll;

    @FXML
    private TextField txtResultCode;

    @FXML
    private TableColumn<ResultTableItem, Integer> columnResultMarks;

    @FXML
    private ComboBox<String> cboResultCourseNo;

    @FXML
    private TextField txtResultMark;

    @FXML
    private Button btnResultAdd;

    @FXML
    private ComboBox<String> cboResultStudentId;

    @FXML
    private Button btnResultSend;

    @FXML
    private Button btnResultReset;

    @FXML
    private ComboBox<String> cboResultType;

    private ObservableList<ResultTableItem> tableItems;

    @FXML
    void addTableItem(ActionEvent event){
        if(txtResultMark.getText().equals("") || !isNumeric(txtResultMark.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid number...");
            alert.showAndWait();
            return;
        }
        if(txtResultRoll.getText().equals("")  || !isNumeric(txtResultRoll.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter a valid roll number...");
            alert.showAndWait();
            return;
        }
        tableItems.add(new ResultTableItem(txtResultCode.getText() + txtResultRoll.getText(), Integer.parseInt(txtResultMark.getText())));

        txtResultRoll.clear();
        txtResultMark.clear();
    }

    @FXML
    void resetResult(ActionEvent event) {
        txtResultRoll.clear();
        txtResultMark.clear();
        tableItems.clear();
    }

    @FXML
    void sendResult(ActionEvent event) throws IOException {
        if(cboResultCourseNo.getValue() == null){
            showMsg("Please select the course no...");
            return;
        }
        else if(cboResultType.getValue() == null){
            showMsg("Please select the result type...");
            return;
        }

        Result result = new Result();
        result.courseTeacher = main.username;
        result.course = cboResultCourseNo.getValue();
        result.resultType = cboResultType.getValue();

        Iterator<ResultTableItem> it = tableItems.iterator();
        while (it.hasNext()){
            ResultTableItem t = it.next();
            result.addResult(t.studentId, t.mark);
        }

        main.oos.writeObject("setResult");
        main.oos.writeObject(result);

        showMsg(Alert.AlertType.CONFIRMATION, "Successfully uploaded to the server...");
    }
    /** end of upload result tab*/
/*****************************************************************************************/
    /** Class Note Upload*/
    @FXML
    private ComboBox<String> cboClassNoteCourseNo;

    @FXML
    private Button btnClassNoteBrowse;

    @FXML
    private Label lblClassNote;

    @FXML
    private Button btnClassNoteSend;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label lblClassNoteProgress;

    private File file = null;
    @FXML
    void browseClassNote(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Class Note...");
        file = fileChooser.showOpenDialog(main.getStage());
        lblClassNote.setText(file.getName());

    }

    @FXML
    void sendClassNote(ActionEvent event) throws  Exception {
        if(file == null){
            main.showMsg("Please Select a file...");
            return;
        }
        main.oos.writeObject("receiveFile");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        byte[]  buff;
        long fileLength = file.length();
        int current = 0;

        main.oos.writeObject(cboClassNoteCourseNo.getValue());
        main.oos.writeObject(file);

        while (current < fileLength){
            int size = 10000; //10Kb
            if(bis.available() < size) size = bis.available();

            buff = new byte[size];
            bis.read(buff, 0, size);
            main.os.write(buff);

            current += size;

            double percent = (double)current  / fileLength;
            lblClassNoteProgress.setText("Upload Status: " + percent * 100 + "%");
            progressBar.setProgress(percent);
        }
        System.out.println("File uploaded Successfully");
        main.os.flush();
        bis.close();



        NotificationToReceive notification = new NotificationToReceive();
        int i = cboClassNoteCourseNo.getItems().indexOf(cboClassNoteCourseNo.getValue());
        notification.level = courses.get(i).level + "";
        notification.term = courses.get(i).term + "";
        notification.section = courses.get(i).section;
        notification.title = "Class Note uploaded";
        notification.body = "Class note has been uploaded. You can now download it. @DOWNLOAD@"+ cboClassNoteCourseNo.getValue() + " - "+
                main.username + " - "
                +file.getName() ;
        notification.from = main.username;
        main.oos.writeObject("setNotification");
        main.oos.writeObject(notification);

        file = null;
        lblClassNote.setText("No file is selected");

        main.showMsg(Alert.AlertType.CONFIRMATION, "File Successfully uploaded...");
    }

    /**end of class Note Upload*/
/*****************************************************************************************/


    /** Utility */
    private Main main;
    private Alert alert;
    ArrayList<Course> courses;
    private void resetNotificationForm(){
        cboCourseNo.setPromptText("Course No");
        txtBody.setText("");
        txtTitle.setText("");
    }

    private boolean isNumeric(String text){
        for (int i = 0; i < text.length(); i++) {
            if('0' > text.charAt(i) || text.charAt(i) > '9'){
                return false;
            }
        }
        return true;
    }
    private void showMsg(String txt){
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(txt);
        alert.showAndWait();
    }
    private void showMsg(Alert.AlertType type, String txt){
        alert.setAlertType(type);
        alert.setContentText(txt);
        alert.showAndWait();
    }
    public void setMain(Main main) {
        this.main = main;
    }

    public void initializeTeacher() throws IOException, ClassNotFoundException {
        alert = new Alert(Alert.AlertType.ERROR);

        main.oos.writeObject("sendCourses");
        courses = (ArrayList<Course>) main.ois.readObject();
        for (int i = 0; i < courses.size(); i++){
            cboCourseNo.getItems().add(courses.get(i).courseId);
            cboResultCourseNo.getItems().add(courses.get(i).courseId);
            cboClassNoteCourseNo.getItems().add(courses.get(i).courseId);
        }

        //result table tab
        tableItems = FXCollections.observableArrayList();
        tableResult.setItems(tableItems);
        columnResultStudentId.setCellValueFactory(new PropertyValueFactory<ResultTableItem, String>("studentId"));
        columnResultMarks.setCellValueFactory(new PropertyValueFactory<ResultTableItem, Integer>("mark"));

        cboResultType.getItems().addAll("CT-1", "CT-2", "CT-5", "CT-4", "CT-2", "TermFinal");
    }
}



