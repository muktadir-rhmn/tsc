package utility.Teacher;

/**
 * Created by Muktadir on 12/22/2015.
 */
public class ResultTableItem {
    public String studentId;
    public int mark;

    public ResultTableItem(String studentId, int mark){
        this.studentId = studentId;
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public String getStudentId() {
        return studentId;
    }
}
