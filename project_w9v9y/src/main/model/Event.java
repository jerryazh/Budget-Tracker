package model;

import java.util.Calendar;
import java.util.Date;

public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    /**
     * Creates an event with the given description
     * and the current date/time stamp.
     * @param description  a description of the event
     */
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }


    /**
     * Gets the description of this event.
     * @return  the description of the event
     */
    public String getDescription() {
        return description;
    }



    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
