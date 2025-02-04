package example.data;

public class Events {

    public Events(String name, String desc, String type, int booked) {
        this.eventName = name;
        this.eventDesc = desc;
        this.eventType = type;
        this.eventBooked = booked;
    }
    private String eventName;
    private String eventDesc;
    private String eventType;
    private int eventBooked;
    private int eventCapacity = 100;

    public String getEventName() { return eventName; }
    public String getEventDesc() { return eventDesc; }
    public String getEventType() { return eventType; }
    public int getEventCapacity() { return eventCapacity; }

    public int getEventBooked() { return eventBooked; }
}
