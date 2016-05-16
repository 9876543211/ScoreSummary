package JavaTest.main;

import java.util.Comparator;
import java.io.Serializable;

public class Student implements Comparable<Student>, Cloneable, Serializable { 

    private static final long serialVersionUID = 1L;
    
    private String stuId;
    private Subject subj;
    private int score;
    private int avgScore;

    public Student(String stuId, Subject subj, int score, int avgScore) {
        
       this.stuId = stuId;
       this.subj = subj;
       this.score= score;
       this.avgScore= avgScore;
    }

    //getter and setter methods
    public String getStuId() {
        return this.stuId;
    }
 
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Subject getSubject() {
        return this.subj;
    }       

    public void setSubject(Subject subj) {
        this.subj = subj;
    }
 
    public int getScore() {
        return this.score;
    }
 
    public void setScore(int score) {
        this.score = score;
    }
 
    public int getAvgScore() {
        return this.avgScore;
    }
 
    public void setAvgScore(int avgScore) {
        this.avgScore = avgScore;
    }
    
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Student compareStudent) {
    
        int compareScore = ((Student) compareStudent).getScore(); 
        
        //ascending order
        // return this.score - compareScore;
        
        //descending order
        return compareScore - this.score;
    }

    public static Comparator<Student> StuIdComparator 
        = new Comparator<Student>() {

        public int compare(Student stu1, Student stu2) {
        
        String stuId1 = stu1.getStuId().toUpperCase();
        String stuId2 = stu2.getStuId().toUpperCase();
        
        //ascending order
        return stuId1.compareTo(stuId2);
        
        //descending order
        //return stuId2.compareTo(stuId1);
        }
    };

    public static Comparator<Student> AvgScoreComparator 
        = new Comparator<Student>() {

        public int compare(Student stu1, Student stu2) {
        
        Integer score1 = stu1.getAvgScore();
        Integer score2 = stu2.getAvgScore();
        
        //ascending order
        // return score1.compareTo(score2);
        
        //descending order
        return score1.compareTo(score2);
        }
    };
}