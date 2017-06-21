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
public class UC47Controller {

    private final FairCenter fc;
    protected User selectedFae;

    public UC47Controller(FairCenter fc) {
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

    public double getSubmissionGlobalMeanRate() {
        List<Review> allFaeReviews = new ArrayList<>();
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventList = er.getAllEvents();
        for (int i = 0; i < eventList.size(); i++) {
            ApplicationList applicationList = eventList.get(i).getApplicationList();
            List<Application> applications = applicationList.getApplications();
            for (int j = 0; j < applications.size(); j++) {
                List<Review> reviews = applications.get(j).getReviews();
                for (int a = 0; a < reviews.size(); a++) {
                    if (reviews.get(a).hasFaeEvaluated()) {
                        allFaeReviews.add(reviews.get(a));
                    }
                }
            }
        }

        return Calculations.getFaeMeanRate(allFaeReviews);
    }

}
