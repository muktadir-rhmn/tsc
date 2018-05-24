package Client.Student;

import Client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import utility.notification.NotificationToSend;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class NotificationViewerController {

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblBody;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblFrom;

    @FXML
    private Label lblTime;

    private AnchorPane anchorPane;
    private Tab tabNotification;
    private NotificationToSend notification;
    private Main main;

    public void initializeNotificationViewer(AnchorPane anchorPane, Tab tabRequest, Main main){
        this.anchorPane = anchorPane;
        this.tabNotification = tabRequest;
        this.main = main;

        lblBody.setWrapText(true);

    }

    private String fileName;
    public void setNotification(NotificationToSend notification) {
        this.notification = notification;

        lblTitle.setText(notification.title);
        lblFrom.setText(notification.from);
        lblTime.setText(notification.time);

        if (notification.body.contains("@DOWNLOAD@")){
            String str[] = notification.body.split("@DOWNLOAD@");
            lblBody.setText(str[0]);
            fileName = str[1];
            btnDownload.setVisible(true);
        }
        else{
            lblBody.setText(notification.body);
            btnDownload.setVisible(false);
        }


    }

    @FXML
    void back(ActionEvent event) {
        getback();
    }

    private void getback(){
        tabNotification.setContent(anchorPane);
    }

    //download
    @FXML
    private Button btnDownload;

    @FXML
    void download(ActionEvent event){
        try {
            main.oos.writeObject("sendFile");
            main.oos.writeObject(fileName);

            File file = (File) main.ois.readObject();

            /*FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose the file...");*/

            DirectoryChooser fileChooser = new DirectoryChooser();
            fileChooser.setTitle("Choose the directory...");
            File fileOut = fileChooser.showDialog(main.getStage());

            //File fileOut = fileChooser.showOpenDialog(main.getStage());
            fileOut = new File(fileOut.getAbsoluteFile() + "\\"+ file.getName());
            //File fileOut = new File("C:\\Users\\Muktadir\\Desktop\\stucture.cpp");
            FileOutputStream fos = new FileOutputStream(fileOut);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            long fileSize = file.length();
            long current = 0;
            byte[] buff = new byte[10000];
            int bytesRead = 0;

            while (current < fileSize){
                bytesRead = main.is.read(buff);
                bos.write(buff, 0,  bytesRead);
                System.out.println("File: '" + new String(buff, 0, bytesRead) + "'");
                current += bytesRead;
            }

            bos.close();
            main.showMsg(Alert.AlertType.CONFIRMATION, "Successfully Downloaded");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

