/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PC
 */
public abstract class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private Place place;
    private Date startDate;
    private Date endDate;
    private Date applicationBegin;
    private Date applicationEnd;
    private OrganizerList organizerList;

    public Event(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd) {

        this.title = title;
        this.description = description;
        this.place = new Place(place);
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicationBegin = applicationBegin;
        this.applicationEnd = applicationEnd;

    }

    public boolean validateEventStateFAEDefined() {

        return false;
    }

    public void addOrganizer(User user) {
        organizerList.addOrganizer(user);
    }

    public String toString() {
        return String.format("Title: %s%nDescription: %s%nPlace : %s%nStart Date: %s%nEnd Date: %s%n Application Begin: %s%n, Application End: %s%n", title, description, place.toString(), startDate.toString(), endDate.toString(), applicationBegin.toString(), applicationEnd.toString());

    }


}
