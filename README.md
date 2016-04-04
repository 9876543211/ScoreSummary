# ScoreSummary
以下の4メソッドを持つExamSummaryクラスを作成せよ。  

/**  * 学籍番号,科目,得点を登録する  */  
void register(String 学籍番号 , Subject 科目 , int 得点);   

/**  * 引数で指定された学籍番号・科目の得点を返す  
* @return 得点  */  
int getScore(String 学籍番号, Subject 科目);   

/**  * 引数で指定された科目の最高得点の学籍番号を返す  
* @return 学籍番号のリスト  */  
List<String> getTopScoreIdsBySubject(Subject 科目);   
/**  
* 全科目の平均得点の高い順に学籍番号を返す  
* @return 学籍番号のリスト  */  
List&lt;String> getIdsByAverage();   
なおSubjectは以下のような定義でよい。  
enum Subject { MATH, SCIENCE, ENGLISH }   
使用例)  
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
int score = es.getScore("00kc002",Subject.MATH);  
List<String> topScoreIds = es.getTopScoreIdsBySubject(Subject.ENGLISH);  
List<String> ids = es.getIdsByAverage();
