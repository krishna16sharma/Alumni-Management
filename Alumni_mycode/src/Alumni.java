import java.util.*;
public class Alumni extends Student{
    public String contact_no;
    public String job;
    public String email;
    public Alumni(String username, String password, String yearOfGraduation) {
        super(username, password, yearOfGraduation);
    }
    public Alumni(String username, String password, String yearOfGraduation,String contact_no,String job,String email) {
        this(username,password,yearOfGraduation);
        this.contact_no=contact_no;
        this.job=job;
        this.email=email;
    }
    public String[] Reply_to_Question()
    {
        String reply[]=new String[1];
        System.out.println("Please Enter Your Reply For the Question");
        Scanner s=new Scanner(System.in);
        reply[0]=s.nextLine ();
        return reply;
    }
    public Email sendEmail(String s,String b) {
    	Email e = new Email(s,b);
    	return e;
    }
    public Events hostEvent(String Event, String date, String Time,String venue){
        Events ev=new Events(Event,date,Time,venue);
        return ev;
    }


}
