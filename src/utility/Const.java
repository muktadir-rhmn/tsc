package utility;

/**
 * Created by MUKTADIR on 11/21/2015.
 */
public interface Const {
    /*Networking*/
    int portNo = 10000;
    String host = "localhost";

    /*database*/
    String database = "jdbc:mysql://localhost:3306/project";
    String dbUserName = "root";
    String dbPassword = "";

    /*sms server*/
    int smsPort = 9500;
    String smsUserName = "project";
    String smsPassword = "150250";

    /*File*/
    long maxFileSize = 5000000;

}
