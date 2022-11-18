package Parser;

public class gpa {
    String type;
    String outOf4;
    String attemptedTotal;
    String earnedTotal;
    String GPAUts;
    String Points;

    public gpa(String t, String o, String a, String e, String g, String p){
        type = t;
        outOf4 = o;
        attemptedTotal = a;
        earnedTotal = e;
        GPAUts = g;
        Points = p;
    }

    public void printAll(){
        System.out.println("GPA: (");
        System.out.println(type);
        System.out.println(outOf4);
        System.out.println(attemptedTotal);
        System.out.println(earnedTotal);
        System.out.println(GPAUts);
        System.out.println(Points);
        System.out.println(")");
    }
}
