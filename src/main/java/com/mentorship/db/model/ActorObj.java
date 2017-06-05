package com.mentorship.db.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Uliana Pizhanska on 04/06/2017.
 */
public class ActorObj implements Serializable{
    private static final long serialVersionUID = 1L;
    private int actorId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;


    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString(){
        return "Id = " + this.actorId +", "
                + "First name = " + firstName + ", "
                + "Last name = " + lastName + ", "
                + "Was updated = " + lastUpdate ;
    }
}
