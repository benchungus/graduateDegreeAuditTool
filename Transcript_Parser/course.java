import java.util.ArrayList;
public class course {
    String name;
    String number;
    ArrayList<String> instructors;
    String attempted;
    String earned;
    String points;

    public course(String nam, String num, ArrayList<String> ins, String a, String e, String p){
        name = nam;
        number = num;
        instructors = ins;
        attempted = a;
        earned = e;
        points = p;
    }
}
