public class Email {
	public String Subject;
	public int memberCount;	//Number of people needed for event
	private String Body;
	public Email(String Subject,int memberCount, String Body) {
		this.Subject=Subject;
		this.memberCount=memberCount;
		this.Body=Body;
	}
	public Email(String Subject, String Body) {
		this.Subject=Subject;
		this.Body=Body;
		memberCount=4;
	}
	public void setBody(String Body) {
		this.Body=Body;
	}
	public String getBody() {
		return Body;
	}
}
