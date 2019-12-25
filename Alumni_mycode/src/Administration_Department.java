public class Administration_Department {

    public static int event_ID;
    public Events[] events;
    Administration_Department()
    {
        events=new Events[Admin.n];
    }
    public void update_Events(String Event_Name,String date,String time , String venue)  // To Add Events Not in Newsletter
    {
        events[event_ID]=new Events (Event_Name,date, time, venue );
        event_ID+=1;
    }
    public void update_Events(String Event_Name,String date,String time , String venue,int participants)  // To Add Events Not in Newsletter
    {
        events[event_ID]=new Events (Event_Name,date, time, venue ,participants);
        event_ID+=1;
    }
    public void display_Newsletter()
    {
        System.out.println("The Events are:");
        for(int i=0; i<event_ID;i++)
        {
            System.out.println(i+1+" Event: "+events[i].Events_Name+" Date: "+events[i].date+" Time: "+events[i].time+" Venue: "+events[i].venue
            +" Participants count: "+events[i].participants);
        }

    }

}
