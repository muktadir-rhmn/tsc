package Client.Student;

/**
 * Created by MUKTADIR on 12/2/2015.
 */

import Client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utility.Student.NotificationTableItem;
import utility.notification.Notification;
import utility.notification.NotificationToSend;
import java.io.IOException;
import java.util.ArrayList;

public class StudentController {

    /*FXML Code*/
    @FXML
    private Tab tabNotification;

    @FXML
    private AnchorPane notificationAchorPane;

    @FXML
    private TableView<NotificationTableItem> tableNotifications;

    @FXML
    private TableColumn<NotificationTableItem, Integer> columnNo;

    @FXML
    private TableColumn<NotificationTableItem, String> columnTitle;

    @FXML
    private TableColumn<NotificationTableItem, String> columnFrom;

    @FXML
    private TableColumn<NotificationTableItem, String> columnTime;

    @FXML
    private Button btnRefresh;

    @FXML
    private Label lblNotification;

    @FXML
    private Tab tabCourses;

    private AnchorPane anchorPane;

    @FXML
    void refresh(ActionEvent event){
        getNotification();
    }
    /*end of FXML code*/

    /* Utility */
    private ObservableList<NotificationTableItem> notificationList;
    private int sizeOfNotiTable = 0;
    private Main main;

    public void setMain(Main main) { //this method will be called whenever Student Scene is displayed
        this.main = main;
    }

    public void initializeStudent(){
        //this method will be called automatically by the FXMLLoader
        notificationList = FXCollections.observableArrayList();

        tableNotifications.setItems(notificationList);
        columnNo.setCellValueFactory(new PropertyValueFactory<NotificationTableItem, Integer>("no"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<NotificationTableItem, String>("title"));
        columnFrom.setCellValueFactory(new PropertyValueFactory<NotificationTableItem, String>("from"));
        columnTime.setCellValueFactory(new PropertyValueFactory<NotificationTableItem, String>("time"));



        tableNotifications.setRowFactory(tv -> {
            TableRow<NotificationTableItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    NotificationTableItem rowData = row.getItem();
                    //here I have to change the scene
                    try {
                        showNotifictionViewer(rowData.notification);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        getNotification();
    }

    private void getNotification(){
        ArrayList<NotificationToSend> notifications = null;

        try {
            main.oos.writeObject("sendNotification");
            notifications = (ArrayList<NotificationToSend>) main.ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        int size = notifications.size();
        for (int i = 0; i < size; i++) {
            NotificationTableItem item = new NotificationTableItem();
            item.no = ++sizeOfNotiTable;
            item.title = notifications.get(i).title;
            item.from =  notifications.get(i).from;
            item.time =  notifications.get(i).time;
            item.body = notifications.get(i).body;
            item.notification = notifications.get(i);

            notificationList.add(item);
        }

        size = notificationList.size();
        if(size == 0){
            lblNotification.setText("You have no new notification");
        }
        else if (size == 1){
            lblNotification.setText("You have " + size + " new notification");
        }
        else {
            lblNotification.setText("You have " + size + " new notifications");
        }

    }
    private NotificationViewerController notificationViewerController = null;
    private Parent notificationViewer;
    private void showNotifictionViewer(NotificationToSend noti) throws IOException {
        if(notificationViewerController == null){
            FXMLLoader loader= new FXMLLoader(getClass().getResource("NotificationViewer.fxml"));
            notificationViewer = loader.load();

            notificationViewerController = loader.getController();
            notificationViewerController.initializeNotificationViewer(notificationAchorPane, tabNotification, main);
        }

        notificationViewerController.setNotification(noti);
        tabNotification.setContent(notificationViewer);
    }

}
