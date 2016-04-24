package cn.wlzy.domain;
/**
 * 具体题目。与试卷多对一
 * @author Administrator
 *
 */
public class ExamOption {
	private Long id;
	private String num;//选项编号
	private String content;//选项内容
	private String A;//A选项
	private String B;//B选项
	private String C;//C选项
	private String D;//D选项
	private String answer;//答案只能从A\B\C\D中选
	private ExamTopic examTopic;//多对一
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public ExamTopic getExamTopic() {
		return examTopic;
	}
	public void setExamTopic(ExamTopic examTopic) {
		this.examTopic = examTopic;
	}
	@Override
	public String toString() {
		return "ExamOption [id=" + id + ", num=" + num + ", content=" + content
				+ ", A=" + A + ", B=" + B + ", C=" + C + ", D=" + D
				+ ", answer=" + answer + ", examTopic=" + examTopic + "]";
	}
	
}
