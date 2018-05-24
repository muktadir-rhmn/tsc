package utility.Head;

import Client.Main;
import javafx.scene.control.Button;
import Client.Head.HeadController;
import utility.notification.NotificationToSend;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Ranakrc on 21-Dec-15.
 */
public class RequestTableItem implements Serializable {
    public String teacherName;
    public String title;
    public Button approve;
    private Main main;
    public NotificationToSend notification;

    public RequestTableItem(Main m){
        main = m;
        approve = new Button("Approve");

        approve.setOnAction(event-> {
                try {
                    main.oos.writeObject("approveResult");
                    main.oos.writeObject(teacherName);
                    main.oos.writeObject(title);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        );
    }

    public Button getApprove() {
        return approve;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTitle() {
        return title;
    }
}