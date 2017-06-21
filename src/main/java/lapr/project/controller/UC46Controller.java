/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ApplicationList;
import lapr.project.model.Calculations;
import lapr.project.model.Event;
import lapr.project.model.EventRegistry;
import lapr.project.model.FairCenter;
import lapr.project.model.Review;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
public class UC46Controller {

    private final FairCenter fc;
    protected User selectedFae;

    public UC46Controller(FairCenter fc) {
        this.fc = fc;
    }

    public List<User> getAllFae() {
        EventRegistry er = fc.getEventRegistry();
        List<User> faeUserReferenceList = er.getAllFaeUserReference();
        return faeUserReferenceList;
    }

    public Object getSelectedFae() {
        return selectedFae;
    }

    public void setSelectedFae(User faeUserReference) {
        selectedFae = faeUserReference;
    }

    public double getFaeMeanRate() {
        List<Review> faeReviews = new ArrayList<>();
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventList = er.getEventsByFAE(selectedFae);
        for (int i = 0; i < eventList.size(); i++) {
            ApplicationList applicationList = eventList.get(i).getApplicationList();
            List<Application> applications = applicationList.getApplicationsEvaluatedByFae(selectedFae);
            for (int j = 0; j < applications.size(); j++) {
                List<Review> reviews = applications.get(j).getReviews();
                for (int a = 0; a < reviews.size(); a++) {
                    if (reviews.get(a).getAssignment().getFAE().getUser().equals(selectedFae)) {
                        if (reviews.get(a).hasFaeEvaluated()) {
                            faeReviews.add(reviews.get(a));
                        }
                    }
                }
            }
        }

        return Calculations.getFaeMeanRate(faeReviews);
    }

}
