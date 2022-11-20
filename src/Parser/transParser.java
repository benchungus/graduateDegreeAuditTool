package Parser;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class transParser {
    //for course parsing
    String courseName;
    String number;
    ArrayList<String> instructors = new ArrayList<>();
    String attempted;
    String earned;
    String points;
    //for external degree parsing
    String school;
    String degreeName;
    String dateFinished;
    //for semester parsing
    String year;
    String season;
    ArrayList<course> courses = new ArrayList<>();
    gpa term;
    gpa transfer;
    gpa combined;
    gpa cum;
    gpa transCum;
    gpa combinedCum;
    //for final transcript parsing
    String studentName;
    String studentId;
    ArrayList<externalDeg> exDegs = new ArrayList<>();
    ArrayList<semester> sems = new ArrayList<>();
    gpa totCumGpa;
    gpa totTransGpa;
    gpa totCombGpa;

    public String getDegreeName() {
        return degreeName;
    }

    public ArrayList<semester> getSems() {
        return sems;
    }

    //scanner
    File trans;
    String fname;

    public transParser(String f) {
        fname = f;
    }

    public void read(String fname) {
        trans = new File(fname);
        try{
            Scanner first = new Scanner(trans);
            String line;
            String[] lineArr;
            //scanning for name and ID
            first.nextLine();
            line = first.nextLine();
            lineArr = line.split(" ");
            studentName = scanEnd(lineArr, 1, 0);
            lineArr = scanTill(first, "ID:", 1);
            studentId = lineArr[2];
            //scanning for external degrees
            Scanner in = new Scanner(trans);
            line = checkPageEnd(in);
            lineArr = line.split(" ");
            lineArr = scanTill(in, "External", 0);
            line = checkPageEnd(in);
            while(!line.equals("Academic Program History")){
                school = line;
                line = checkPageEnd(in);
                lineArr = line.split(" ");
                dateFinished = lineArr[lineArr.length-1];
                degreeName = scanEnd(lineArr, 0, 1);
                line = checkPageEnd(in);
                exDegs.add(new externalDeg(school, degreeName, dateFinished));
            }
            //skip the details
            while(!line.equals("Beginning of Graduate Record")){
                line = checkPageEnd(in);
            }
            //grab all semesters
            while(!line.equals("Graduate Career Totals")){
                line = checkPageEnd(in);
                lineArr = line.split(" ");
                year = lineArr[0];
                season = lineArr[1];
                checkPageEnd(in);
                line = checkPageEnd(in);
                lineArr = line.split(" ");
                courses = new ArrayList<>();
                //grab all courses for semester
                while(!line.equals("Attempted Earned GPA Uts Points")){
                    number = lineArr[0] + " " + lineArr[1];
                    int aL = lineArr.length;
                    points = Double.parseDouble(lineArr[aL-1]);
                    if(Character.isLetter(lineArr[aL-2].charAt(0))){
                        earned = Double.parseDouble(lineArr[aL-3]);
                        attempted = Double.parseDouble(lineArr[aL-4]);
                        courseName = scanEnd(lineArr, 2, 4);
                    }
                    else{
                        earned = Double.parseDouble(lineArr[aL-2]);
                        attempted = Double.parseDouble(lineArr[aL-3]);
                        courseName = scanEnd(lineArr, 2, 3);
                    }
                    line = checkPageEnd(in);
                    lineArr = line.split(" ");
                    instructors = new ArrayList<>();
                    instructors.add(scanEnd(lineArr, 1, 0));
                    line = checkPageEnd(in);
                    lineArr = line.split(" ");
                    while(Character.isLetter(lineArr[lineArr.length-1].charAt(0)) && !line.equals("Attempted Earned GPA Uts Points")){
                        instructors.add(line);
                        line = checkPageEnd(in);
                        lineArr = line.split(" ");
                    }
                    courses.add(new course(courseName, number, instructors, attempted, earned, points));
                }
                //get all gpa for semester 
                line = checkPageEnd(in);
                lineArr = line.split(" ");
                term = new gpa("Term", Double.parseDouble(lineArr[2]), Double.parseDouble(lineArr[5]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]));
                line = checkPageEnd(in);
                lineArr = line.split(" ");
                if(Character.isLetter(lineArr[3].charAt(0))){
                    transfer = new gpa("Transfer Term", 0.0, Double.parseDouble(lineArr[5]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]));
                }
                else{
                    transfer = new gpa("Transfer Term", Double.parseDouble(lineArr[3]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]), Double.parseDouble(lineArr[9]));
                }
                line = checkPageEnd(in);
                lineArr = line.split(" ");
                combined = new gpa("Combined", Double.parseDouble(lineArr[2]), Double.parseDouble(lineArr[5]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]));
                line = checkPageEnd(in);
                lineArr = line.split(" ");
                cum = new gpa("Cum", Double.parseDouble(lineArr[2]), Double.parseDouble(lineArr[5]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]));
                line = checkPageEnd(in);
                lineArr = line.split(" ");
                if(Character.isLetter(lineArr[3].charAt(0))){
                    transCum = new gpa("Transfer Cum", 0.0, Double.parseDouble(lineArr[5]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]));
                }
                else{
                    transCum = new gpa("Transfer Cum", Double.parseDouble(lineArr[3]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]), Double.parseDouble(lineArr[9]));
                }
                line = checkPageEnd(in);
                lineArr = line.split(" ");
                combinedCum = new gpa("Combined Cum", Double.parseDouble(lineArr[3]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]), Double.parseDouble(lineArr[9]));
                line = checkPageEnd(in);
                sems.add(new semester(year, season, courses, term, transfer, combined, cum, transCum, combinedCum));
            }
            //getting all gpa totals for the end
            line = checkPageEnd(in);
            lineArr = line.split(" ");
            totCumGpa = new gpa("Tot Cum", Double.parseDouble(lineArr[2]), Double.parseDouble(lineArr[5]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]));
            line = checkPageEnd(in);
            lineArr = line.split(" ");
            if(Character.isLetter(lineArr[3].charAt(0))){
                totTransGpa = new gpa("Tot Transfer Cum", 0.0, Double.parseDouble(lineArr[5]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]));
            }
            else{
                totTransGpa = new gpa("Tot Transfer Cum", Double.parseDouble(lineArr[3]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]), Double.parseDouble(lineArr[9]));
            }
            line = checkPageEnd(in);
            lineArr = line.split(" ");
            totCombGpa = new gpa("Tot Combined Cum", Double.parseDouble(lineArr[3]), Double.parseDouble(lineArr[6]), Double.parseDouble(lineArr[7]), Double.parseDouble(lineArr[8]), Double.parseDouble(lineArr[9]));
            transcript t = new transcript(studentName, studentId, exDegs, sems, totCumGpa, totTransGpa, totCombGpa);
            //t.printAll();
        }
        catch(FileNotFoundException e){
            System.out.println("file not found");
        }

    }

    public static String scanEnd(String[] lineArr, int startPos, int endPos) {
        String s = "";
        for (int i = startPos; i < lineArr.length - endPos; i++) {
            s = s + lineArr[i];
            if (i != lineArr.length - 1) {
                s = s + " ";
            }
        }
        return s;
    }

    public static String[] scanTill(Scanner scan, String word, int pos) {
        String line = scan.nextLine();
        String[] lineArr = line.split(" ");
        while (scan.hasNextLine()) {
            if (pos >= lineArr.length || !lineArr[pos].equals(word)) {
                line = scan.nextLine();
                lineArr = line.split(" ");
                //System.out.println(lineArr[pos]);
            } else {
                break;
            }
        }
        return lineArr;
    }

    public static String checkPageEnd(Scanner scan) {
        String line = scan.nextLine();
        String[] lineArr = line.split(" ");
        if ((lineArr.length >= 2 &&
                (Character.isDigit(lineArr[0].charAt(0)) && Character.isDigit(lineArr[1].charAt(0)))) ||
                line.equals("Unofficial Transcript - UT-Dallas") ||
                lineArr[0].equals("Name:")) {
            return checkPageEnd(scan);
        } else {
            return line;
        }
    }
}