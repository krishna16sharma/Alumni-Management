import java.util.Scanner;

public class Student {
    public static int unique_id;
    private String username;
    private String password;
    private String yearOfGraduation;
    public boolean graduated;

    public Student(String username,String password,String yearOfGraduation) {
        unique_id+=1;
        this.username=username;
        this.password=password;
        this.yearOfGraduation=yearOfGraduation;
        graduated=false;
    }
    public String[] ask_Questions() {
        String question[] = new String[3];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your question");
        question[0] = sc.nextLine();//Enter question
        System.out.println("Type of question?");
        question[1] = sc.nextLine();//enter type
        question[2] = this.getUsername();
        return question;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username=username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }
    public String  getyearOfGraduation() {
        return yearOfGraduation;
    }
    public void setyearOfGraduation(String yearOfGraduation) {
        this.yearOfGraduation=yearOfGraduation;
    }
    public boolean getGraduated() {
        return graduated;
    }
    public void setGraduated() {
        graduated=true;
    }
}


