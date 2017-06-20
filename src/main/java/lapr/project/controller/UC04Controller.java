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
 * @author Hugo
 */
public class UC04Controller {

    protected Event selectedEvent;
    private final FairCenter fc;
    private final User user;
    private final EventRegistry eventRegistry;
    private List<Event> eventList;
    private List<Application> faeApplicationList;
    protected Application selectedApplication;

    public UC04Controller(FairCenter fc, User u) {
        this.fc = fc;
        this.user = u;
        this.eventRegistry = this.fc.getEventRegistry();

    }

    public List<Event> getEventsByFAE() {
        eventList = eventRegistry.getEventsByFAE(user);
        return eventList;
    }

    public boolean validateEvent() {
        ApplicationList applicationList = selectedEvent.getApplicationList();
        List<Application> applications = applicationList.getApplicationsReadyForFAEEvaluation();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).isFAEReviewing(new FAE(user))) {
                faeApplicationList.add(applications.get(i));
            }
        }
        return faeApplicationList.isEmpty();
    }

    public List<Application> getFAEApplications() {
        return faeApplicationList;
    }

    public void evaluateApplication(Integer faeTopicKnowledge, Integer eventAdequacy, Integer inviteAdequacy, Integer recomendation, String justification) {
        selectedApplication.setFAEReview(new FAE(user), faeTopicKnowledge, eventAdequacy, inviteAdequacy, recomendation, justification);
    }

    public boolean registerEvaluation() {
        return fc.registerApplicationChanges(selectedEvent, selectedApplication);
    }

    public void setSelectedEvent(Event e){
        this.selectedEvent=e;
    }
    
    public void setSelectedApplication(Application app){
        this.selectedApplication=app;
    }
    
    public Application getSelectedApplication(){
        return this.selectedApplication;
    }
    
    
}
