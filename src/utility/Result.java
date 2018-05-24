package utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by MUKTADIR on 11/22/2015.
 */
public class Result implements Serializable{
    public String courseTeacher;
    public String course;
    public String resultType;
    public Hashtable<String, Integer> marks;

    public Result(){
        marks = new Hashtable<String, Integer>();
    }

    public void addResult(String studentId, int mark){
        marks.put(studentId, mark);
    }
}


