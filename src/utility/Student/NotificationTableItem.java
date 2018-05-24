package utility.Student;

import utility.notification.NotificationToSend;

/**
 * Created by MUKTADIR on 12/3/2015.
 */
public class NotificationTableItem {
    public int no;
    public String title;
    public String from;
    public String time;
    public String body;
    public NotificationToSend notification;

    public int getNo() {return no;}

    public String getTitle() {
        return title;
    }

    public String getFrom() {
        return from;
    }

    public String getTime() {
        return time;
    }
}
