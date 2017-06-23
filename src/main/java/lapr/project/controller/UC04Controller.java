/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ApplicationList;
import lapr.project.model.Event;
import lapr.project.model.EventRegistry;
import lapr.project.model.FAE;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author LAPR2-G054
 */
public class UC04Controller {

    protected Event selectedEvent;
    private final FairCenter fc;
    private final User user;
    private final EventRegistry eventRegistry;
    private List<Application> faeApplicationList;
    protected Application selectedApplication;

    /**
     * constructor of UC04
     *
     * @param fc fair center in which the controller operates
     * @param u user that is using the UC22 features
     */
    public UC04Controller(FairCenter fc, User u) {
        this.fc = fc;
        this.user = u;
        this.eventRegistry = this.fc.getEventRegistry();

    }

    /**
     * returns a list of events in which the user is an FAE
     * @return list of events
     */
    public List<Event> getEventsByFAE() {
        return eventRegistry.getEventsByFAE(user);
    }

    /**
     * Checks the selected event for applications of the fae
     * @return true if there are FAE applications on the event
     */
    public boolean validateEvent() {
        ApplicationList applicationList = selectedEvent.getApplicationList();
        List<Application> applications = applicationList.getApplicationsReadyForFAEEvaluation();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).isFAEReviewing(new FAE(user))) {
                faeApplicationList.add(applications.get(i));
            }
        }
        return !faeApplicationList.isEmpty();
    }

    /**
     * Returns a list of all tehe applications that the FAE has reviews in
     * @return list of applications
     */
    public List<Application> getFAEApplications() {
        return faeApplicationList;
    }

    /**
     * creates the evaluation for the application
     * @param faeTopicKnowledge classification inputed by the FAE on a 0-5 scale based on his knowledge of the topic
     * @param eventAdequacy classification inputed by the FAE on a 0-5 scale based on the adequacy of the application to this event
     * @param inviteAdequacy classification inputed by the FAE on a 0-5 scale on his based on the adequacy of the number of invites
     * @param recomendation  classification inputed by the FAE on a 0-5 scale on his based on his recomendation
     * @param justification text justifiying his decision
     */
    public void evaluateApplication(Integer faeTopicKnowledge, Integer eventAdequacy, Integer inviteAdequacy, Integer recomendation, String justification) {
        selectedApplication.setFAEReview(new FAE(user), faeTopicKnowledge, eventAdequacy, inviteAdequacy, recomendation, justification);
    }

    /**
     * saves the evaluation
     * @return true confirming the resgistration
     */
    public boolean registerEvaluation() {
        return selectedEvent.saveApplicationChanges(selectedApplication);
    }

    /**
     * set of the selectedEvent object 
     * @param e Event to set as selectedEvent
     */
    public void setSelectedEvent(Event e) {
        this.selectedEvent = e;
    }

    /**
     * set of the setSelectedApplication object 
     * @param app application to set as selectedApplication
     */
    public void setSelectedApplication(Application app) {
        this.selectedApplication = app;
    }

    /**
     * get of the selected application object
     * @return selectedApplication
     */
    public Application getSelectedApplication() {
        return this.selectedApplication;
    }

}
