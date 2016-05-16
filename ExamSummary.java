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
     * * �w�Дԍ�,�Ȗ�,���_��o�^����
     */
    public void flush()  {
        try {
           this.serialize(this.regList, TEMP_FILE);
        } catch (IOException ex) {
        }
    }
       
    /** 
     * * �w�Дԍ�,�Ȗ�,���_��o�^����
     */
    public void register(String stuId, Subject subj, int score)  {
        this.regList.add(new Student(stuId, subj, score, 0));
    }
    
    /** 
    * �����Ŏw�肳�ꂽ�w�Дԍ��E�Ȗڂ̓��_��Ԃ� 
    * @return ���_ 
    */
    public int getScore(String stuId, Subject subj) {
        this.getStuList();
        return this.map.get(stuId + "|" + subj.toString());
    }
    
    /** 
    * �����Ŏw�肳�ꂽ�Ȗڂ̍ō����_�̊w�Дԍ���Ԃ� 
    * @return �w�Дԍ��̃��X�g
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
        // �_����descending order�Ń\�[�g
        Collections.sort(tmpList);
        // �ō����_�̊w�Дԍ�����������ꍇ�A�S���ԋp����
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
    * �S�Ȗڂ̕��ϓ��_�̍������Ɋw�Дԍ���Ԃ� 
    * @return �w�Дԍ��̃��X�g 
    */ 
    List<String> getIdsByAverage(){
        this.getStuList();
        List<String> list = new ArrayList<String>();
        List<Student> modStuList = new ArrayList<Student>();

        try {
            List<Student> tmpList = new ArrayList<Student>();
            tmpList.addAll(this.stuList);
            // �w�Дԍ���ascending order�Ń\�[�g
            Collections.sort(tmpList, Student.StuIdComparator);
            // �w�Дԍ����ƕ��ϓ_�����Z�o����
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
            // ���ϓ��_�̍������Ń\�[�g
            Collections.sort(modStuList, Student.AvgScoreComparator);
            for (int i = 0; i < modStuList.size(); i++) {
                list.add(modStuList.get(i).getStuId());
            }
            
        } catch (Exception ex) {
        }
        
        return list;
    }
}