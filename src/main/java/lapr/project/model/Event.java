/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;
import lapr.project.utils.EventState;

/**
 *
 * @author LAPR2-G054
 */
@XmlRootElement
@XmlSeeAlso({Exhibition.class, Congress.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {

    @XmlElement
    public EventState state;
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
    private FAEList faeList;

    @XmlElement(name = "applicationSet")
    private ApplicationList applicationList;

    @XmlElementWrapper(name = "stands")
    @XmlElement(name = "stand")
    private List<Stand> stands;
/**
 * creats event
 * @param title to assign to created event
 * @param description to assign to created event
 * @param place to assign to created event
 * @param startDate to assign to created event
 * @param endDate to assign to created event
 * @param applicationBegin to assign to created event
 * @param applicationEnd to assign to created event
 */
    public Event(String title, String description, String place, Date startDate, Date endDate, Date applicationBegin, Date applicationEnd) {
        this.faeList = new FAEList();
        this.organizerList = new OrganizerList();
        this.title = title;
        this.description = description;
        this.place = new Place(place);
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicationBegin = applicationBegin;
        this.applicationEnd = applicationEnd;
        this.applicationList = new ApplicationList();
        this.stands = new ArrayList<>();
        setState(new EventCreatedState(this));
    }
/**
 * constructor to avoid xml conflictis
 */
    public Event() {
        //to avoid xml conflicts
    }

    /**
     * Return a user list of all the users who are organizers in the event
     *
     * @return
     */
    public List<User> getOrganizersListUserRef() {
        List<User> orgList = new ArrayList<>();
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
    public List<User> getFAEListUserRef() {
        List<User> FAEList = new ArrayList<>();
        for (FAE fae : faeList.getFaeList()) {
            FAEList.add(fae.getUser());
        }
        return FAEList;
    }
/**
 * get method of attribute FAEList
 * @return faeList
 */
    public FAEList getFAEList() {
        return this.faeList;
    }
/**
 * get method of attribute FAEList
 * @return faeList
 */
    public OrganizerList getOrganizerList() {
        return this.organizerList;
    }
/**
 * get method of attribute FAEList
 * @return faeList
 */
    public void setState(EventState state) {
        this.state = state;
    }
/**
 * chceks the event state
 * @return true if it is in created state
 */
    public boolean validateEventStateCreated() {
        return state instanceof EventCreatedState;//Needs testing
    }
/**
 * chceks the event state
 * @return true if it is in created state
 */
    public boolean validateEventStateFAEDefined() {
        return state instanceof EventDefinedFAEState;//Needs testing
    }
/**
 * chceks the event state
 * @return true if it is in created state
 */
    public boolean validateEventStateApplicationsOpen() {
        return state instanceof EventApplicationsOpenState;//Needs testing
    }
/**
 * chceks the event state
 * @return true if it is in created state
 */
    public boolean validateEventStateApplicationsEvaluated() {
        return state instanceof EventApplicationEvaluatedState;//Needs testing
    }
/**
 * chceks the event state
 * @return true if it is in created state
 */
    public boolean validateEventStateFinalState() {
        return state instanceof EventFinalState;//Needs testing
    }
/**
 * chceks the event state
 * @return true if it is in created state
 */
    public boolean validateEventEnded() {
        return state instanceof EventEndedState;//Needs testing
    }
/**
 * add organizer
 * @param user user that is added as organizer 
 */
    public void addOrganizer(User user) {
        organizerList.addOrganizer(user);
    }
/**
 * override of toString method
 * @return information of congress
 */
    @Override
    public String toString() {
        return String.format("Title: %s%n  Description: %s%n  Place : %s%n  Start Date: %s%n  End Date: %s%n", title, description, place.toString(), startDate.toString(), endDate.toString());

    }
/**
 * creates FAE
 * @param u user to become FAE
 */
    public void createFAE(User u) {
        faeList.createFAE(u);
    }
/**
 * registar FAEs
 */
    public void registerFAEs() {
        this.faeList.registerFAEs();
    }
/**
 * discards FAEs 
 */
    public void discardFAEs() {
        this.faeList.discardFAEs();
    }
/**
 * get method of attribute applicationList
 * @return applicationList
 */
    public ApplicationList getApplicationList() {
        return applicationList;
    }
/**
 * get method of attribute title
 * @return title
 */
    public String getTitle() {
        return this.title;
    }
/**
 * get method of attribute description
 * @return description
 */
    public String getDescription() {
        return this.description;
    }
/**
 * recieves xml data and saves an event
 * @param xmlEvent event to save
 */
    public void recieveXMLData(Event xmlEvent) {
        this.title = xmlEvent.title;
        this.faeList = xmlEvent.getFAEList();
        this.applicationList = xmlEvent.getApplicationList();
        this.stands.addAll(xmlEvent.stands);
        setState(new EventFinalState(this));
    }
    /**
     * override of equals method
     *
     * @param o object to compare too
     * @return true if equals false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }

        Event that = (Event) o;

        if (!title.equals(that.title)) {
            return false;
        }
        if (!description.equals(that.description)) {
            return false;
        }
        if (!startDate.equals(that.startDate)) {
            return false;
        }
        if (!endDate.equals(that.endDate)) {
            return false;
        }
        if (!applicationBegin.equals(that.applicationBegin)) {
            return false;
        }
        if (!applicationEnd.equals(that.applicationEnd)) {
            return false;
        }
        if (!organizerList.equals(that.organizerList)) {
            return false;
        }
        return faeList.equals(that.faeList);

    }
/**
     * hashcode for the equals method
     *
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.startDate);
        hash = 23 * hash + Objects.hashCode(this.endDate);
        hash = 23 * hash + Objects.hashCode(this.applicationBegin);
        hash = 23 * hash + Objects.hashCode(this.applicationEnd);
        hash = 23 * hash + Objects.hashCode(this.organizerList);
        hash = 23 * hash + Objects.hashCode(this.faeList);
        return hash;
    }
/**
 * saves changes
 * @param application to save
 * @return boolean confirming save
 */
    public boolean saveApplicationChanges(Application application) {
        return applicationList.saveApplicationChanges(application);
    }
/**
 * register stand
 * @param stand to register
 */
    public void registerStand(Stand stand) {
        stands.add(stand);
    }
/**
 * add stand to list
 * @param stand to add
 * @return true to confirm being added
 */
    public boolean addStand(Stand stand) {
        stands.add(stand);
        return true;
    }
/**
 * get method of attribute applicationEnd
 * @return applicationEnd
 */
    public Date getApplicationEndDate() {
        return this.applicationEnd;
    }
/**
 * get method of attribute applicationBegin
 * @return applicationBegin
 */
    public Date getApplicationBeginDate() {
        return this.applicationBegin;
    }
/**
 * get method of attribute stands
 * @return stands
 */
    public List<Stand> getStands() {
        return stands;
    }
/**
 * returns list of available stands
 * @return list stand
 */
    public List<Stand> getAvailableStands() {
        List<Stand> availableStands = new ArrayList<>();
        for (int i = 0; i < stands.size(); i++) {
            if (!applicationList.checkStandAssigned(stands.get(i)) == true) {
                availableStands.add(stands.get(i));
            }
        }
        return availableStands;
    }

    public boolean checkForRepresentativeApplication(User user) {
        return applicationList.CheckForApplicationFromUser(user);
    }
/**
 * get method of attribute applicationList
 * @return applicationList
 */
    public Application getApplicationOfRepresentative(User user) {
        return applicationList.getApplicationOfRepresentative(user);
    }
}