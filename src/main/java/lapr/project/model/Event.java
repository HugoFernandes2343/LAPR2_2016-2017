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
 * @author PC
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

    public FAEList getFAEList() {
        return this.faeList;
    }

    public OrganizerList getOrganizerList() {
        return this.organizerList;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public boolean validateEventStateCreated() {
        return state instanceof EventCreatedState;//Needs testing
    }

    public boolean validateEventStateFAEDefined() {
        return state instanceof EventDefinedFAEState;//Needs testing
    }

    public boolean validateEventStateApplicationsOpen() {
        return state instanceof EventApplicationsOpenState;//Needs testing
    }

    public boolean validateEventStateApplicationsEvaluated() {
        return state instanceof EventApplicationEvaluatedState;//Needs testing
    }

    public boolean validateEventStateFinalState() {
        return state instanceof EventFinalState;//Needs testing
    }

    public boolean validateEventEnded() {
        return state instanceof EventEndedState;//Needs testing
    }

    public void addOrganizer(User user) {
        organizerList.addOrganizer(user);
    }

    @Override
    public String toString() {
        return String.format("Title: %s%n  Description: %s%n  Place : %s%n  Start Date: %s%n  End Date: %s%n", title, description, place.toString(), startDate.toString(), endDate.toString());

    }

    public void createFAE(User u) {
        faeList.createFAE(u);
    }

    public void registerFAEs() {
        this.faeList.registerFAEs();
    }

    public void discardFAEs() {
        this.faeList.discardFAEs();
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
        this.title = xmlEvent.title;
        this.faeList = xmlEvent.getFAEList();
        this.applicationList = xmlEvent.getApplicationList();
        this.stands.addAll(xmlEvent.stands);
    }

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

    public boolean saveApplicationChanges(Application application) {
        return applicationList.saveApplicationChanges(application);
    }

    public void registerStand(Stand stand) {
        stands.add(stand);
    }

    public boolean addStand(Stand stand) {
        stands.add(stand);
        return true;
    }

    public Date getApplicationEndDate() {
        return this.applicationEnd;
    }

    public Date getApplicationBeginDate() {
        return this.applicationBegin;
    }

    public List<Stand> getStands() {
        return stands;
    }

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

    public Application getApplicationOfRepresentative(User user) {
        return applicationList.getApplicationOfRepresentative(user);
    }
}
