package Client;

import Client.Head.HeadController;
import Client.Student.StudentController;
import Client.Teacher.TeacherController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import utility.Const;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/**
 * Created by MUKTADIR on 12/2/2015.
 */
public class Main extends Application {
    private Stage stage;
    private Scene loginScene = null;

    /*the streams to communicate with server*/
    public ObjectOutputStream oos ;
    public ObjectInputStream ois;
    public InputStream is;
    public OutputStream os;
    public String name; //to store the name of the user;
    public String username; //to store the username of the user;
    public String type;
    public static Alert alert;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        alert = new Alert(Alert.AlertType.ERROR);
        stage  = primaryStage;
        stage.show();
        showLogin();
        //showHead();

        //establish the connection
        Socket server = new Socket(Const.host, Const.portNo);
        oos = new ObjectOutputStream((os = server.getOutputStream()));
        ois = new ObjectInputStream((is = server.getInputStream()));
    }

    public void showLogin() throws IOException {
        /*
        * 1. FirstTime call of this function will load the login page
        * 2. but next time it will just show the hidden scene.
        * */
        if(loginScene == null) {
            URL location = getClass().getResource("login.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(location);
            location = loader.getLocation();
            loader.load();
            LoginController controller = loader.getController();
            controller.setMain(this);


            Parent root = loader.getRoot();

            loginScene = new Scene(root);
        }

        stage.setTitle("User Client");
        stage.setScene(loginScene);
    }

    public void showHead() throws IOException, ClassNotFoundException {
        URL location = getClass().getResource("Head/HeadGUI.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
        loader.load();

        HeadController controller = loader.getController();
        controller.setMain(this);
        controller.initializeHead();

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);

        stage.setTitle("Head of the Department - Welcome " + name + " Sir");
        stage.setScene(scene);
    }

    public void showTeacher() throws IOException, ClassNotFoundException {
        URL location = getClass().getResource("Teacher/TeacherGUI.fxml");
        FXMLLoader loader = new  FXMLLoader();
        loader.setLocation(location);
        loader.load();

        TeacherController controller = loader.getController();
        controller.setMain(this);
        controller.initializeTeacher();

        Scene scene = new Scene(loader.getRoot());

        stage.setTitle("Teacher - Welcome " + name +" Sir");
        stage.setScene(scene);
    }

    public void showStudent() throws IOException {
        URL location = getClass().getResource("Student/StudentGUI.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
        loader.load();

        StudentController controller = loader.getController();
        controller.setMain(this);
        controller.initializeStudent();

        Scene scene = new Scene(loader.getRoot());
        stage.setTitle("Student - Welcome "+ name);
        stage.setScene(scene);
    }

    public void showMsg(Alert.AlertType alertType, String msg){
        alert.setAlertType(alertType);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void showMsg( String msg){
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public Stage getStage(){return stage;}

}
