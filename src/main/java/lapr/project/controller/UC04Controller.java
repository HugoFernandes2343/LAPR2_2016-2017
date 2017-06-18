/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import lapr.project.model.Application;
import lapr.project.model.ApplicationList;
import lapr.project.model.Event;
import lapr.project.model.EventRegistry;
import lapr.project.model.FAE;
import lapr.project.model.FairCenter;
import lapr.project.model.Review;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
public class UC04Controller {

    public Event selectedEvent;
    private FairCenter fc;
    private User user;
    private EventRegistry eventRegistry;
    private ArrayList<Event> eventList;
    private ArrayList<Application> FAEApplicationList;
    public Application selectedApplication;

    public UC04Controller(FairCenter fc, User u) {
        this.fc = fc;
        this.user = u;
        this.eventRegistry = this.fc.getEventRegistry();

    }

    public ArrayList<Event> getEventsByFAE() {
        eventList = eventRegistry.getEventsByFAE(user);
        return eventList;
    }

    public boolean validateEvent() {
        ApplicationList applicationList = selectedEvent.getApplicationList();
        ArrayList<Application> applications = applicationList.getApplicationsReadyForFAEEvaluation();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).isFAEReviewing(new FAE(user))) {
                FAEApplicationList.add(applications.get(i));
            }
        }
        return FAEApplicationList.size() > 0;
    }

    public ArrayList<Application> getFAEApplications() {
        return FAEApplicationList;
    }

    public void evaluateApplication(Integer faeTopicKnowledge, Integer eventAdequacy, Integer inviteAdequacy, Integer recomendation, String justification) {
        selectedApplication.setFAEReview(new FAE(user), faeTopicKnowledge, eventAdequacy, inviteAdequacy, recomendation, justification);
    }

    public boolean registerEvaluation() {
        return fc.registerApplicationChanges(selectedEvent, selectedApplication);
    }

}
