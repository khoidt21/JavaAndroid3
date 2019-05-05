package model;

import java.io.Serializable;

public class Alarm implements Serializable {
    private int hour;
    private int minute;
    private String event;
    private String title;
    private boolean toggleOnOff = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
                ", title='" + title + '\'' +
                ", toggleOnOff=" + toggleOnOff +
                '}';
    }
}