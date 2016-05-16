package JavaTest.main;

import java.util.regex.Pattern;

public class test
{ 
    public static void main(String [] args)
    {
        ExamSummary es = new ExamSummary(); 
        es.register ("00kc001",Subject.MATH , 85); 
        es.register ("00kc001",Subject.SCIENCE , 75); 
        es.register ("00kc001",Subject.ENGLISH , 65); 
        es.register ("00kc002",Subject.MATH , 60); 
        es.register ("00kc002",Subject.SCIENCE , 80); 
        es.register ("00kc002",Subject.ENGLISH , 70); 
        es.register ("00kc003",Subject.MATH , 90); 
        es.register ("00kc003",Subject.SCIENCE , 55); 
        es.register ("00kc003",Subject.ENGLISH , 65); 
        es.flush();
        int score = es.getScore("00kc002",Subject.MATH); 
        System.out.format("Score : %d", score);
        List<String> topScoreIds = es.getTopScoreIdsBySubject(Subject.ENGLISH); 
        for (String s : topScoreIds) {
           System.out.format("TopScoreIds : %s", s);
        }
        List<String> ids = es.getIdsByAverage();
        for (String s : ids) {
               System.out.format("ids : %s", s);
        }
    }
}
