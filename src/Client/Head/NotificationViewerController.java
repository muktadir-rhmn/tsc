package Client.Head;

import Client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import utility.notification.NotificationToSend;

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

    @FXML
    private Button btnApprove;


    private AnchorPane anchorPane;
    private Tab tabRequest;
    private NotificationToSend notification;
    private Main main;

    public void initializeNotificationViewer(AnchorPane anchorPane, Tab tabRequest, Main main){
        this.anchorPane = anchorPane;
        this.tabRequest = tabRequest;
        this.main = main;

        lblBody.setWrapText(true);

    }

    public void setNotification(NotificationToSend notification) {
        this.notification = notification;

        lblTitle.setText(notification.title);
        lblFrom.setText(notification.from);
        lblTime.setText(notification.time);
        lblBody.setText(notification.body);
    }

    @FXML
    void approve(ActionEvent event) throws IOException {
        main.oos.writeObject("approveResult");
        main.oos.writeObject(notification.from);
        main.oos.writeObject(notification.title);

        main.showMsg(Alert.AlertType.CONFIRMATION, "Successfully Approved the result...");
        getback();
    }

    @FXML
    void back(ActionEvent event) {
        getback();
    }

    private void getback(){
        tabRequest.setContent(anchorPane);
    }

}

