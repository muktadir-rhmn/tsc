package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utility.LoginInfo;

import java.io.IOException;

public class LoginController {

    private Main main;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnReset;

    @FXML
    void login(ActionEvent event) throws IOException, ClassNotFoundException {
        /*this will:
        * 1. request the server for login
        * 2. send username and password
        * 3. receive the information whether login successfull or not
         */
        if(txtUsername.getText().equals("") || txtPassword.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter username and password");
            alert.showAndWait();
            return;
        }

        main.oos.writeObject(txtUsername.getText());
        main.oos.writeObject(txtPassword.getText());

        LoginInfo loginInfo = (LoginInfo) main.ois.readObject();
        if(loginInfo == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong user name or password");
            alert.showAndWait();
            return;
        }

        main.username = txtUsername.getText();
        main.name = loginInfo.name;
        main.type = loginInfo.type;

        System.out.println(loginInfo.name);

        //displays the specified window
        if(loginInfo.type.equals("head")){
            main.showHead();
        }
        else if(loginInfo.type.equals("teacher")){
            main.showTeacher();
        }
        else if(loginInfo.type.equals("student")){
            main.showStudent();
        }

    }

    @FXML
    void reset(ActionEvent event) {
        txtUsername.setText("");
        txtPassword.setText("");
    }


    public void  setMain(Main main){this.main = main;}
}
