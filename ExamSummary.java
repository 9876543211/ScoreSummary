package JavaTest.main;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class ExamSummary implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final String TEMP_FILE = "c:/tmp.txt";
    private List<Student> regList;
    private List<Student> stuList;
    private Map<String, Integer> map;

    public ExamSummary() {
        this.regList = new ArrayList<Student>();
    }
    
    @SuppressWarnings("unchecked")
    private void getStuList() {
        try {
            if (this.stuList == null) {
                this.stuList = (List<Student>)this.deserialize(TEMP_FILE);
                this.map = new HashMap<String, Integer>();
                for (Student stu : this.stuList) {
                    this.map.put(stu.getStuId() + "|" + stu.getSubject().toString(), stu.getScore());
                }
            }
        } catch (Exception ex) {
        }
    }
    
    // deserialize to Object from given file
    private Object deserialize(String fileName) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
 
    // serialize the given object and save it to file
    private void serialize(Object obj, String fileName)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
 
        fos.close();
    }
   
    /** 
     * * 学籍番号,科目,得点を登録する
     */
    public void flush()  {
        try {
           this.serialize(this.regList, TEMP_FILE);
        } catch (IOException ex) {
        }
    }
       
    /** 
     * * 学籍番号,科目,得点を登録する
     */
    public void register(String stuId, Subject subj, int score)  {
        this.regList.add(new Student(stuId, subj, score, 0));
    }
    
    /** 
    * 引数で指定された学籍番号・科目の得点を返す 
    * @return 得点 
    */
    public int getScore(String stuId, Subject subj) {
        this.getStuList();
        return this.map.get(stuId + "|" + subj.toString());
    }
    
    /** 
    * 引数で指定された科目の最高得点の学籍番号を返す 
    * @return 学籍番号のリスト
    */
    public List<String> getTopScoreIdsBySubject(Subject subj) {
        this.getStuList();
        List<String> list = new ArrayList<String>();

        List<Student> tmpList = new ArrayList<Student>();
        tmpList.addAll(this.stuList);
        
        for (int i = tmpList.size() - 1; i >= 0; i--) {
            if (!subj.toString().equals(tmpList.get(i).getSubject().toString())) {
                tmpList.remove(tmpList.get(i));
            }
        }
        // 点数のdescending orderでソート
        Collections.sort(tmpList);
        // 最高得点の学籍番号が複数ある場合、全部返却する
        int maxScore = tmpList.get(0).getScore();
        for (int i = 0; i < tmpList.size(); i++) {
            if (tmpList.get(i).getScore() == maxScore) {
                list.add(tmpList.get(i).getStuId());
                continue;
            }
            break;
        }
        
        return list;
    }
    
    /**
    * 全科目の平均得点の高い順に学籍番号を返す 
    * @return 学籍番号のリスト 
    */ 
    List<String> getIdsByAverage(){
        this.getStuList();
        List<String> list = new ArrayList<String>();
        List<Student> modStuList = new ArrayList<Student>();

        try {
            List<Student> tmpList = new ArrayList<Student>();
            tmpList.addAll(this.stuList);
            // 学籍番号のascending orderでソート
            Collections.sort(tmpList, Student.StuIdComparator);
            // 学籍番号ごと平均点数を算出する
            int scores = 0;
            for (int i = 0; i < tmpList.size(); i++) {
                String currStuId = tmpList.get(i).getStuId();
                scores = scores + tmpList.get(i).getScore();
                if ( i == tmpList.size() - 1 || 
                     !currStuId.equals(tmpList.get(i + 1).getStuId())) {
                    modStuList.add(new Student(currStuId, null, scores, scores / 3));
                    scores = 0;
                }
            }
            // 平均得点の高い順でソート
            Collections.sort(modStuList, Student.AvgScoreComparator);
            for (int i = 0; i < modStuList.size(); i++) {
                list.add(modStuList.get(i).getStuId());
            }
            
        } catch (Exception ex) {
        }
        
        return list;
    }
}