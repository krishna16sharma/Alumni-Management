public class question_Pool {
    String question_Type;
    String question;
    String Name; // To display the asker's name
    String replies[];
    int reply;
     question_Pool(String type, String question, String Name)
     {
         this.question_Type=type;
         this.question=question;
         this.Name=Name;
         replies=new String[100];
         reply=0;
     }
     public void setReplies(String rep)
     {	
    	 try {
         replies[reply]=rep;
         reply+=1;
    	 }
    	 catch(ArrayIndexOutOfBoundsException e) {
    		 System.out.println("The question you chose doesn't exist...");
    	 }
    	 catch(NullPointerException e) {
    		 System.out.println("No such question exists");
    	 }
     }
     public void display_Replies()
     {	
    	 System.out.println("Replies for question \""+question +"\"");
         for(int i=0;i<reply;i++)
         {
             System.out.println (replies[i]);
         }
     }



}
