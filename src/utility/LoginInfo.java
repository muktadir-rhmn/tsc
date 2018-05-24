package utility;

import java.io.Serializable;

/**
 * Created by MUKTADIR on 12/3/2015.
 */
public class LoginInfo implements Serializable {
    public String name;
    public String type;

    public LoginInfo(String type, String name){
        this.type = type;
        this.name = name;
    }
}
