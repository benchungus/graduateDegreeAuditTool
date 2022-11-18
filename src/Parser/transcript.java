package Parser;

import java.util.ArrayList;

public class transcript {
    String name;
    String studentId;
    ArrayList<externalDeg> exDegs;
    ArrayList<semester> sems;
    gpa totCumGpa;
    gpa totTransGpa;
    gpa totCombGpa;

    public transcript(String n, String si, ArrayList<externalDeg> ed, ArrayList<semester> s, gpa tcug, gpa ttg, gpa tcog){
        name = n;
        studentId = si;
        exDegs = ed;
        sems = s;
        totCumGpa = tcug;
        totTransGpa = ttg;
        totCombGpa = tcog;
    }

    public void printAll(){
        System.out.println(name);
        System.out.println(studentId);
        for(int i = 0; i < exDegs.size(); i++){
            exDegs.get(i).printAll();
        }
        for(int i = 0; i < sems.size(); i++){
            sems.get(i).printAll();
        }
        totCumGpa.printAll();
        totTransGpa.printAll();
        totCombGpa.printAll();
    }
}
