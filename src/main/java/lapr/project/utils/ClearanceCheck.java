/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.List;
import lapr.project.model.*;

/**
 *
 * @author PC
 */
public class ClearanceCheck {

    private User u;
    private FairCenter fc;

    public ClearanceCheck(FairCenter fc, User u) {
        this.u = u;
        this.fc = fc;
    }

    public boolean isFAE() throws NullPointerException {
        try {
            List<Event> eventList = fc.getEventRegistry().getAllEvents();
            for (Event event : eventList) {
                List<User> FAEList_UserRef = event.getFAEListUserRef();
                for (User user : FAEList_UserRef) {
                    if (u.equals(user)) {
                        return true;
                    }
                }
            }

        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage() + "  //  There are no FAE in the system to check");
        }
        return false;
    }

    public boolean isOrg() throws NullPointerException {
        try {
            List<Event> eventList = fc.getEventRegistry().getAllEvents();
            for (Event event : eventList) {
                List<User> OrgList_UserRef = event.getOrganizersListUserRef();
                for (User user : OrgList_UserRef) {
                    if (u.equals(user)) {
                        return true;
                    }
                }
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage() + "  //  There are no Organizers in the system to check");
        }
        return false;
    }

    public boolean isEventManager() {
        List<User> EventManager_UserRef = fc.getEventManagerRegistry().getEventManagersList_byUserRef();
        for (User user : EventManager_UserRef) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRepresentative() {
        try {
            List<Event> eventList = fc.getEventRegistry().getAllEvents();
            for (Event event : eventList) {
                List<Application> allApplications = event.getApplicationList().getApplications();
                for (Application app : allApplications) {
                    User uTemp = app.getRepresentative().getUser();
                    if (u.equals(uTemp)) {
                        return true;
                    }
                }
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage() + "  //  There are no Representative in the system to check");
        }
        return false;
    }

    public void clear() {
        this.fc = null;
        this.u = null;
    }

    public void assignNew(FairCenter fc, User u) {
        this.fc = fc;
        this.u = u;
    }
}
