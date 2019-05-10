package model;

import java.io.Serializable;

public class Alarm implements Serializable {
    private int id;
    private int hour;
    private int minute;
    private String ampm;
    private String event;
    private boolean status;

   // private int idsong;

    public Alarm(){}
    public Alarm(int id,int hour,int minute,String ampm,String event,boolean status){
        super();

        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.ampm = ampm;
        this.event = event;
        this.status = status;

      //  this.idsong = idsong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    private boolean toggleOnOff = false;


//    public int getIdsong() {
//        return idsong;
//    }
//
//    public void setIdsong(int idsong) {
//        this.idsong = idsong;
//    }



    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public boolean isToggleOnOff() {
        return toggleOnOff;
    }

    public void setToggleOnOff(boolean toggleOnOff) {
        this.toggleOnOff = toggleOnOff;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", event='" + event + '\'' +
                ", toggleOnOff=" + toggleOnOff +
                '}';
    }
}