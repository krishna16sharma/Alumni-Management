import java.util.Random;
import java.util.Scanner;

public class Admin {
    public static  int sID=0;
    public static int job_ID;
    public static int question_ID;
    public static int e_id=0;	//index for emails
    public static int event_ID;// index for studentList
    public static Student[] studentList;
    public static Alumni[] alumni;
    public static int aID=0;//Maintains a list of student
    static int n;
    public Administration_Department D;
   // public String[] job_Offers;       // Changed the job offer to a class as per the class diagram
    public job_Offers [] jobOffers;
    public question_Pool[] questions;    //Changed the Question to a class as per the class diagram
    public Email[] emails;
    static Scanner s=new Scanner(System.in);
    public Admin(int n) {
        this.n=n;
        studentList= new Student[n];
        alumni=new Alumni[n];
        jobOffers= new job_Offers[n];  // job offers twice the number of the students (just to be safe)
        questions= new question_Pool[n];
    	emails =  new Email[50];		// Creates an array of type Email to store emails
        D=new Administration_Department ();
    }

    public void createRecord_Student(String username,String password,String yearOfGraduation) {
        try {
            studentList[sID]= new Student(username,password,yearOfGraduation);
            sID+=1;
            System.out.println("Record Created for Student");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Records are full!");
        }
    }
    public void createRecord_Alumni(String username, String password, String  yearOfGraduation,String contact_no,String job,String email)
    {
        try {
            alumni[aID]= new Alumni(username, password, yearOfGraduation, contact_no, job, email);
            aID+=1;
            System.out.println("Record Created for Alumni");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Records are full!");
        }
    }
    public void Display_Students()
    {
        for(int i=0;i<sID;i++)
        {
            System.out.println("username"+" "+ studentList[i].getUsername ()+"   "+"Password"+" "+studentList[i].getPassword ());
        }
    }


    public void update_Events(String Event_Name,String date,String time , String venue)  // To Add Events Not in Newsletter
    {
      D.update_Events ( Event_Name,date,time,venue);
    }
    public void update_Events(String Event_Name,String date,String time , String venue,int participants)  // Overloaded
    {
        D.update_Events ( Event_Name,date,time,venue,participants);
    }

    public void validate_add_question(String[] s){
        questions[question_ID]=new question_Pool ( s[1],s[0],s[2] );
        question_ID+=1;
    }
    public void storeAlumniEmail(Email e) {
    	emails[e_id]=e;
    	e_id+=1;
    }
    public void displayEmail(){
        for(int i=0;i<e_id;i++){
            System.out.println("Email No:" + i+1);
            System.out.println("Sub: "+emails[i].Subject);
            System.out.println("Body: "+emails[i].getBody());
        }
    }

    public Email reply_email(Email e,String date,String time,String venue) {
        Random r= new Random();
        int participants=r.nextInt(20);
        e.memberCount=participants;
        Scanner reply_email = new Scanner(System.in);
        if (e.Subject.equalsIgnoreCase("meetup")) {        //If the email is regarding meetups.
            if (participants < 10) {        // Assuming that 20 is the min no. of people required for such meetups
                Email cancel_email = new Email("Meetup Cancellation", "Unfortunately the meetup has been cancelled because of low participation\n" +
                        "There were only "+participants+" participants.");
                return cancel_email;
            } else {
                Email approve_email = new Email("Meetup Approval", "The meetup has been approved. You may proceed with the preparations.");
                update_Events("meetup", date, time, venue, participants);
                return approve_email;
            }
        }
        return e;
    }

    public void display_Newsletter() {      //Added join Event to this
        Scanner eScan = new Scanner(System.in);
        D.display_Newsletter ();
        System.out.println("Do you want to join any event?(Y/N)");
        char ans = eScan.next().toLowerCase().charAt(0);
        if(ans=='y') {
            System.out.println("Enter the event number if you want to join.");
            int choice = eScan.nextInt();
            D.events[choice-1].participants+=1;
        }
        else{
            System.out.println("Thats too bad. We look forward to your participation!");
        }
        //eScan.close();
    }

    public void display_replies() {// The Required Question's Reply can Be seen separately
        System.out.println("Which Question's Reply Would You Like to Look see");
        try {
        int j=s.nextInt ();
        questions[j-1].display_Replies ();
        }
        catch(NullPointerException e) {
        	System.out.println("no such Question");
        }
    }
    public void reply_To_Question(String[] reply,int i)// Reply to question is done by alumni
    {	try {
        questions[i-1].setReplies ( reply[0] );
    	}
    	catch(NullPointerException e) {
    		System.out.println("No such question exists");
    	}
    }

    public void display_questions()
    {
        for(int i=0;i<question_ID;i++)
        {
            System.out.println("Q.No:"+ i+1 +" Question Type: "+questions[i].question_Type+" Question: "+questions[i].question);
            System.out.println("Asked by "+questions[i].Name);
        }
    }

    public void update_Job_Offers(String Role , String Requirements)//Job Offers Given Will Be updated
    {
        jobOffers[job_ID]=new job_Offers (Role,Requirements);
        job_ID+=1;
    }
    public void display_JobOffers()//self explanatory
    {
        for(int i=0;i<job_ID;i++)
        {
            System.out.println("Role: "+jobOffers[i].Role+"Requirements: "+jobOffers[i].Requirements);
        }
    }


}
