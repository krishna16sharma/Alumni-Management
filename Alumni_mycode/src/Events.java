public class Events {
    public String Events_Name;
    public  String date;
    public String time;
    public String venue;
    int participants;
    Events(String Event,String date,String time,String venue)
    {   this.Events_Name=Event;
        this.date=date;
        this.time=time;
        this.venue=venue;
        this.participants=0;
    }
    Events(String Event,String date,String time,String venue,int participants)
    {   this.Events_Name=Event;
        this.date=date;
        this.time=time;
        this.venue=venue;
        this.participants=participants;
    }

}
