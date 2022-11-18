import java.util.ArrayList;

public class semester {
    String year;
    String season;
    ArrayList<course> courses;
    gpa term;
    gpa transfer;
    gpa combined;
    gpa cum;
    gpa transCum;
    gpa combinedCum;

    public semester(String y, String s, ArrayList<course> c, gpa ter, gpa tran, gpa comb, gpa cu, gpa tc, gpa cc){
        year = y;
        season = s;
        courses = c;
        term = ter;
        transfer = tran;
        combined = comb;
        cum = cu;
        transCum = tc;
        combinedCum = cc;
    }
}
