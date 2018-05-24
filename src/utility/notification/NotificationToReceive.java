package utility.notification;

/**
 * Created by MUKTADIR on 11/22/2015.
 */
public class NotificationToReceive extends Notification{
    public String from;
    public String level;
    public String term; //possible values 0 => all, 1, 2
    public String section; //possible values: A, B, A1, B1, A2, B2, AB
}
