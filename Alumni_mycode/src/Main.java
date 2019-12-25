import javax.swing.*;
import java.io.FileWriter;
import java.time.Year;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Main {
        static int n;//No of Students
        public static void main(String[] args) { Boolean cho=false;
        Boolean student=false;
        Boolean Alumni=false;
        Boolean Adm=false;
        int id_Student=1;
        int id_Alumni=1;
        Main m=new Main();
        int curYear=Year.now().getValue();
        Scanner sc = new Scanner(System.in);
        System.out.println("No of Records?");
        n=sc.nextInt ();
        Admin admin = new Admin(n);
        String filename="C:\\Users\\Lenovo\\IdeaProjects\\Alumni_mycode\\src\\Book1.csv";
        File file=new File(filename);
                try {
                        Scanner inputStream = new Scanner(file);
                        while(inputStream.hasNext()) {
                                String data=inputStream.next();
                                String[] values =data.split(",");
                               if(values[5].equals("1"))
                                 {
                                        admin.createRecord_Student(values[0],values[1],(values[6]));

                                }
                                else if(values[5].equals("0"))
                                {
                                        admin.createRecord_Alumni(values[0],values[1],(values[6]), (values[2]),values[3],values[4]);
                                }

                        }
                        inputStream.close();
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }


        while(cho==false) {
                student = false;
                Alumni = false;
                Adm = false;

                System.out.println("Welcome To The Alumni Management System");
                System.out.println("Hi are you a user or a Admin");
                String s = sc.next();
                if (s.equalsIgnoreCase("user")) {
                        System.out.println("1. Student login  2.Alumni login");
                        int c = sc.nextInt();
                        if (c == 1) {
                                System.out.println("Hi Welcome TO the Student Login");
                                System.out.println("Please enter your username and password and unique id");
                                String pass[] = new String[3];
                                pass[0] = sc.next();
                                pass[1] = sc.next();
                                int ID = sc.nextInt();
                                try (
                                        Scanner inputStream = new Scanner(file)) {
                                        while (inputStream.hasNext()) {
                                                String data = inputStream.next();
                                                //System.out.println(data);
                                                String[] values = data.split(",");
                                                if (values[5].equals("1")) {
                                                        if (values[0].equals(pass[0]) && values[1].equals(pass[1])) {
                                                                student = true;
                                                                System.out.println("You Have Successfully Logged In");
                                                                System.out.println("You are now Free to Use the Entire System");
                                                                Boolean choice_Student = true;
                                                                while (choice_Student == true) {
                                                                        System.out.println("1.AskQuestion , 2. View Details of Events, 3. View Replies , 4. View Questions");
                                                                        int ch1 = sc.nextInt();
                                                                        switch (ch1) {
                                                                                case 1: {
                                                                                        String[] ques1 = new String[3];
                                                                                        ques1 = Admin.studentList[ID].ask_Questions();
                                                                                        admin.validate_add_question(ques1);
                                                                                        break;
                                                                                }
                                                                                case 2: {
                                                                                        admin.display_Newsletter();
                                                                                        break;
                                                                                }
                                                                                case 3: {
                                                                                        admin.display_replies();
                                                                                        break;
                                                                                }
                                                                                case 4: {
                                                                                        admin.display_questions();
                                                                                        break;
                                                                                }
                                                                        }
                                                                        System.out.println("Do you Want To Perform Anymore Actions\n(true or false");
                                                                        choice_Student = sc.nextBoolean();
                                                                        if (choice_Student == false) {
                                                                                break;
                                                                        }

                                                                }
                                                        } else if (student == false ) {
                                                                System.out.println("please check your login credentials");//Executes even when logged in
                                                                student = true;
                                                        }
                                                }
                                        }
                                } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                }

                        } else if (c == 2) {
                                System.out.println("Hi Welcome To the Alumni Login");
                                System.out.println("Please Enter your username and password and Unique Id");
                                String pass[] = new String[2];
                                pass[0] = sc.next();
                                pass[1] = sc.next();
                                int ID = sc.nextInt();
                                try (
                                        Scanner inputStream = new Scanner(file)) {
                                        while (inputStream.hasNext()) {
                                                String data = inputStream.next();
                                                //System.out.println(data);
                                                String[] values = data.split(",");
                                                if (values[5].equals("0")) {
                                                        if (values[0].equals(pass[0]) && values[1].equals(pass[1])) {
                                                                Alumni = true;
                                                                System.out.println("You Have Successfully Logged In");
                                                                System.out.println("You are now Free to Use the Entire System");
                                                                Boolean choice_Alumni = true;
                                                                while (choice_Alumni == true) {
                                                                        System.out.println("1.Reply To Question , 2. View Details of Events, 3. View Replies , 4. View Questions , 5. Request Meetup" +
                                                                                " \n6. Notify about jobs , 7. Browse jobs");
                                                                        int ch1 = sc.nextInt();
                                                                        switch (ch1) {
                                                                                case 1: {
                                                                                        String replies[] = new String[1];
                                                                                        System.out.println("Which Question Do you Want To Reply To");
                                                                                        int q = sc.nextInt();
                                                                                        replies = Admin.alumni[ID].Reply_to_Question();
                                                                                        admin.reply_To_Question(replies, q);
                                                                                        break;
                                                                                }
                                                                                case 2: {
                                                                                        admin.display_Newsletter();
                                                                                        break;
                                                                                }
                                                                                case 3: {
                                                                                        admin.display_replies();
                                                                                        break;
                                                                                }
                                                                                case 4: {
                                                                                        admin.display_questions();
                                                                                        break;
                                                                                }
                                                                                case 5: {
                                                                                        Scanner mScan = new Scanner(System.in);
                                                                                        System.out.println("Enter a one line description.");
                                                                                        System.out.println("Also enter Date,time,Venue (in separate lines)");
                                                                                        Email e = Admin.alumni[ID].sendEmail("meetup", mScan.next());
                                                                                        admin.storeAlumniEmail(e);
                                                                                        String date = mScan.next();
                                                                                        String time = mScan.next();
                                                                                        String venue = mScan.next();
                                                                                        //admin.update_Events("meetup", date, time, venue);
                                                                                        Email reply=admin.reply_email(e,date,time,venue);
                                                                                        System.out.println("Reply Email:\nSubject:"+reply.Subject+"\nBody:\n"+reply.getBody());
                                                                                        //mScan.close();
                                                                                        break;
                                                                                }
                                                                                case 6: {
                                                                                        Scanner jScan = new Scanner(System.in);
                                                                                        System.out.println("Enter the Role and Requirements");
                                                                                        String role = jScan.nextLine();
                                                                                        String req = jScan.nextLine();
                                                                                        admin.update_Job_Offers(role, req);
                                                                                        //jScan.close();
                                                                                        break;
                                                                                }
                                                                                case 7: {
                                                                                        admin.display_JobOffers();
                                                                                        System.out.println("That's all! To apply for a job please contact the admin or visit the company's website\nfor more details.");
                                                                                        break;
                                                                                }


                                                                        }
                                                                        System.out.println("Do you Want To Perform AnyMore Actions?\ntrue\tor\tfalse");
                                                                        choice_Alumni = sc.nextBoolean();
                                                                        if (choice_Alumni == false) {
                                                                                break;
                                                                        }
                                                                }

                                                        } else if (Alumni == false && student != true) {
                                                                System.out.println("please check your login credentials");
                                                        }
                                                }
                                        }
                                } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                }

                        }
                } else if (s.equalsIgnoreCase("Admin")) {
                        try (
                                Scanner inputStream = new Scanner(file)) {
                                while (inputStream.hasNext()) {
                                        String data = inputStream.next();
                                        //System.out.println(data);
                                        String[] values = data.split(",");
                                        if (values[3].equalsIgnoreCase("admin")) {
                                                System.out.println("Hi Welcome TO the Admin Login");
                                                System.out.println("Please enter your username and password");
                                                String pass[] = new String[2];
                                                pass[0] = sc.next();
                                                pass[1] = sc.next();
                                                if (values[0].equals(pass[0]) && values[1].equals(pass[1])) {
                                                        System.out.println("You Have Successfully Logged In");
                                                        System.out.println("You are now Free to Use the Entire System");
                                                        Boolean choice_Admin = true;
                                                        while (choice_Admin == true) {
                                                                Adm = true;
                                                                System.out.println("1.Update Events 2.Display Students 3.create_Student Record 4.create_Alumni 5.Display Newsletter \n" +
                                                                        "6.Display replies 7.Display Questions 8.View Emails");
                                                                int ch1 = sc.nextInt();
                                                                switch (ch1) {
                                                                        case 1: {
                                                                                String ch[] = new String[4];
                                                                                System.out.println("Enter event name");
                                                                                ch[0] = sc.next();
                                                                                System.out.println("Enter date");
                                                                                ch[1] = sc.next();
                                                                                System.out.println("Time");
                                                                                ch[2] = sc.next();
                                                                                System.out.println("Venue:");
                                                                                ch[3] = sc.next();
                                                                                admin.update_Events(ch[0], ch[1], ch[2], ch[3]);
                                                                                break;
                                                                        }
                                                                        case 2: {
                                                                                admin.Display_Students();
                                                                                break;
                                                                        }
                                                                        case 3: {
                                                                                FileWriter fileWriter = null;
                                                                                try {
                                                                                        Scanner b = new Scanner(System.in);
                                                                                        fileWriter = new FileWriter(filename, true);
                                                                                        System.out.println("Please Enter the New Student Record");
                                                                                        int sum = Admin.sID;
                                                                                        String w = "" + sum;
                                                                                        System.out.println("Enter username:");
                                                                                        String username = b.nextLine();
                                                                                        System.out.println("Enter password");
                                                                                        String password = b.nextLine();
                                                                                        System.out.println("Contact No:");
                                                                                        String contact = b.nextLine();
                                                                                        System.out.println("Job:");
                                                                                        String job = b.nextLine();
                                                                                        System.out.println("Email:");
                                                                                        String email = b.nextLine();
                                                                                        System.out.println("Year of graduation:");
                                                                                        String year = b.nextLine();
                                                                                        fileWriter.append("\n");
                                                                                        fileWriter.append(username);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(password);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(contact);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(job);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(email);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append("1");
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(year);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(w);
                                                                                        admin.createRecord_Student(username,password,year);
                                                                                        fileWriter.close();


                                                                                } catch (Exception e) {
                                                                                        e.printStackTrace();
                                                                                }
                                                                                break;
                                                                        }
                                                                        case 4: {
                                                                                FileWriter fileWriter = null;
                                                                                try {
                                                                                        Scanner b = new Scanner(System.in);
                                                                                        fileWriter = new FileWriter(filename, true);
                                                                                        System.out.println("Please Enter the New Alumni Record");
                                                                                        int sum = Admin.aID;
                                                                                        String w = "" + sum;
                                                                                        System.out.println("Username:");
                                                                                        String username = b.nextLine();
                                                                                        System.out.println("Password:");
                                                                                        String password = b.nextLine();
                                                                                        System.out.println("Contact No.:");
                                                                                        String contact = b.nextLine();
                                                                                        System.out.println("Job:");
                                                                                        String job = b.nextLine();
                                                                                        System.out.println("Email:");
                                                                                        String email = b.nextLine();
                                                                                        System.out.println("Year:");
                                                                                        String year = b.nextLine();
                                                                                        fileWriter.append("\n");
                                                                                        fileWriter.append(username);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(password);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(contact);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(job);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(email);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append("0");
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(year);
                                                                                        fileWriter.append(",");
                                                                                        fileWriter.append(w);
                                                                                        admin.createRecord_Alumni(username,password,year,contact,job,email);
                                                                                        fileWriter.close();


                                                                                } catch (Exception e) {
                                                                                        e.printStackTrace();
                                                                                }
                                                                                break;
                                                                        }
                                                                        case 5: {
                                                                                admin.display_Newsletter();
                                                                                break;
                                                                        }
                                                                        case 6: {
                                                                                admin.display_replies();
                                                                                break;
                                                                        }
                                                                        case 7: {
                                                                                admin.display_questions();
                                                                                break;
                                                                        }
                                                                        case 8: {
                                                                                System.out.println("Emails:");
                                                                                admin.displayEmail();
                                                                        }

                                                                }
                                                                sc.nextLine();
                                                                System.out.println("Do you Want To Perform AnyMore Actions?Enter 'true' to continue.\ntrue\tor\tfalse");
                                                                choice_Admin = sc.nextBoolean();
                                                                if (choice_Admin == false) {
                                                                        break;
                                                                }

                                                        }
                                                } else if (Alumni != true && Adm == false) {
                                                        System.out.println("please check your login credentials");
                                                        Adm = true;
                                                }
                                        }
                                }
                        } catch (FileNotFoundException e) {
                                e.printStackTrace();
                        }
                }

                System.out.println("Do You Want To Close This Online Portal?Enter 'true' to close.\n(Note:On Closing This Portal You Will Render The data in the portal Unusable(true/false))");
                cho = sc.nextBoolean();

        }
        
    }

}
