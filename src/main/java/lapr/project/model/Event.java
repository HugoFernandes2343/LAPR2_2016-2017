/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.*;
import lapr.project.utils.EventState;

/**
 *
 * @author PC
 */
@XmlRootElement
@XmlSeeAlso({Exhibition.class, Congress.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {

    @XmlElement
    private EventState state;
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private Place place;
    @XmlElement
    private Date startDate;
    @XmlElement
    private Date endDate;
    @XmlElement
    private Date applicationBegin;
    @XmlElement
    private Date applicationEnd;
    @XmlElement
    private OrganizerList organizerList;

    @XmlElement(name = "FAESet")
    private FAEList FaeList;

    @XmlElement(name = "applicationSet")
    private ApplicationList applicationList;

    @XmlElementWrapper(name = "stands")
    @XmlElement(name = "stand")
    private ArrayList<Stand> stands;

    public Event(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd) {
        this.FaeList = new FAEList();
        this.organizerList = new OrganizerList();
        this.title = title;
        this.description = description;
        this.place = new Place(place);
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicationBegin = applicationBegin;
        this.applicationEnd = applicationEnd;
        this.applicationList = new ApplicationList();
        this.stands=new ArrayList<>();
        setState(new EventCreatedState(this));
    }

    public Event() {
        //to avoid xml conflicts
    }

    /**
     * Return a user list of all the users who are organizers in the event
     *
     * @return
     */
    public ArrayList<User> getOrganizersList_UserRef() {
        ArrayList<User> orgList = new ArrayList<>();
        for (Organizer org : organizerList.getList()) {
            orgList.add(org.getUser());
        }
        return orgList;
    }

    /**
     * Return a user list of all the users who are FAE in the event
     *
     * @return
     */
    public ArrayList<User> getFAEList_UserRef() {
        ArrayList<User> FAEList = new ArrayList<>();
        for (FAE fae : FaeList.getList()) {
            FAEList.add(fae.getUser());
        }
        return FAEList;
    }

    public FAEList getFAEList() {
        return this.FaeList;
    }

    public OrganizerList getOrganizerList() {
        return this.organizerList;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public boolean validateEventStateFAEDefined() {
        return state instanceof EventDefinedFAEState;//Needs testing
    }

    public boolean validateEventStateCreated() {
        return state instanceof EventCreatedState;//Needs testing
    }

    public void addOrganizer(User user) {
        organizerList.addOrganizer(user);
    }

    @Override
    public String toString() {
        return String.format("Title: %s%n  Description: %s%n  Place : %s%n  Start Date: %s%n  End Date: %s%n", title, description, place.toString(), startDate.toString(), endDate.toString());

    }

    public void createFAE(User u) {
        FaeList.createFAE(u);
    }

    public void registerFAEs() {
        this.FaeList.registerFAEs();
    }

    public void discardFAEs() {
        this.FaeList.discardFAE();
    }

    boolean validateEventStateApplicationsOpen() {
        return true;
    }

    public ApplicationList getApplicationList() {
        return applicationList;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean registerApplication(Application application) {
        return applicationList.registerApplication(application);
    }

    public void recieveXMLData(Event xmlEvent) {
        this.FaeList = xmlEvent.getFAEList();
        this.applicationList = xmlEvent.getApplicationList();
        this.stands.addAll(xmlEvent.stands);
    }
}
