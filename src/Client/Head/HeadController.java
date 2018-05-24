package Client.Head;

import Client.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utility.Head.RequestTableItem;
import utility.notification.Notification;
import utility.notification.NotificationToSend;
import utility.notification.NotificationToReceive;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class HeadController {

    @FXML
    private Tab tabSendNotification;

    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> cboLevel;

    @FXML
    private ComboBox<String> cboTerm;

    @FXML
    private ComboBox<String> cboSection;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtBody;

    @FXML
    private Button btnSend;

    @FXML
    private Button btnReset;

    @FXML
    private Tab tabRequests;

    @FXML
    private TableView<RequestTableItem> tableRequests;

    @FXML
    private TableColumn<RequestTableItem, String> columnFrom;

    @FXML
    private TableColumn<RequestTableItem, String> colulmnTitle;

    @FXML
    private TableColumn<RequestTableItem, Button> columnAction;

    @FXML
    void reset(ActionEvent event) {
        resetForm();
    }

    @FXML
    void send(ActionEvent event) throws IOException {
        /*checking validity*/
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(cboLevel.getValue() == null){
            alert.setContentText("Please select level.");
            alert.showAndWait();
            return;
        }
        else if(!cboLevel.getValue().equals("All")){
            if( cboTerm.getValue() == null){
                alert.setContentText("Please select term...");
                alert.showAndWait();
                return;
            }
            else if (!cboTerm.getValue().equals("All") && cboSection.getValue() == null){
                alert.setContentText("Please select section...");
                alert.showAndWait();
                return;
            }
        }
        /*end of checking validity*/

        NotificationToReceive notification = new NotificationToReceive();
        notification.level = cboLevel.getValue();
        notification.term = cboTerm.getValue() + "";
        notification.section = cboSection.getValue() + "";
        notification.title = txtTitle.getText();
        notification.body = txtBody.getText();
        notification.from = "head";

        main.oos.writeObject("setNotification");
        main.oos.writeObject(notification);

        resetForm();
    }

    ObservableList<RequestTableItem> request;
    ArrayList<NotificationToSend> notifications;
    private void getNotification() throws IOException, ClassNotFoundException {
        try {
            main.oos.writeObject("sendNotification");
            notifications = (ArrayList<NotificationToSend>) main.ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int size = notifications.size();
        System.out.println("The no of notification: " + size);
        for (int i = 0; i < size; i++){
            RequestTableItem item = new RequestTableItem(main);
            item.teacherName = notifications.get(i).from;
            item.title = notifications.get(i).title;
            item.notification = notifications.get(i);

            request.add(item);
        }
    }

    /* Utility */
    private Main main;
    private void resetForm(){
        cboLevel.setPromptText("Level");
        cboTerm.setPromptText("Term");
        cboSection.setPromptText("Section");
        cboSection.setDisable(true);
        txtTitle.setText("");
        txtBody.setText("");
    }
    public void setMain(Main main) {
        this.main = main;
    }

    public void initializeHead() throws IOException, ClassNotFoundException {
        cboLevel.getItems().addAll("1", "2", "3", "4", "All");
        cboTerm.getItems().addAll("1", "2", "All");
        cboSection.getItems().addAll("A", "B", "A1", "A2", "B1", "B2", "All");

        cboLevel.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cboLevel.getValue().equals("All")){
                    cboTerm.setDisable(true);
                    cboSection.setDisable(true);
                }
                else{
                    cboTerm.setDisable(false);
                    cboSection.setDisable(false);
                }
            }
        });

        cboTerm.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(cboTerm.getValue().equals("All")){
                    cboSection.setDisable(true);
                }
                else{
                    cboSection.setDisable(false);
                }
            }
        });

        /*table*/
        request = FXCollections.observableArrayList();
        tableRequests.setItems(request);
        columnFrom.setCellValueFactory(new PropertyValueFactory<RequestTableItem, String>("teacherName"));
        colulmnTitle.setCellValueFactory(new PropertyValueFactory<RequestTableItem, String>("title"));
        columnAction.setCellValueFactory(new PropertyValueFactory<RequestTableItem, Button>("approve"));

        getNotification();

        tableRequests.setRowFactory( tv -> {
            TableRow<RequestTableItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    RequestTableItem rowData = row.getItem();
                    //here I have to change the scene
                    showNotifictionViewer(rowData.notification);
                }
            });
            return row ;
        });

        notificationViewer = null;

        //set the anchor pane. this anchor pane will be used to change the request tab
        anchorPane = new AnchorPane();
        anchorPane.getChildren().add(tableRequests);
        tabRequests.setContent(anchorPane);
    }

    private Parent notificationViewer;

    private NotificationViewerController notificationViewerController;
    private void showNotifictionViewer(NotificationToSend noti){
        if(notificationViewer == null){
            try {
                //URL location = getClass().getResource("/NotificationViewer.fxml");
                URL location = getClass().getResource("NotificationViewer.fxml");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(location);
                location  = loader.getLocation();
                notificationViewer = loader.load();
                notificationViewerController = loader.getController();
                notificationViewerController.initializeNotificationViewer(anchorPane,tabRequests, main);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        notificationViewerController.setNotification(noti);
        tabRequests.setContent(notificationViewer);
    }
}
