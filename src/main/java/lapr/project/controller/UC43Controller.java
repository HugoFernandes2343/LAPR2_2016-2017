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

/**
 *
 * @author LAPR2-G054
 */
public class UC43Controller {

    private final Calculations calculations;
    private final FairCenter fc;

    /**
     * constructor of UC43 controllers
     *
     * @param fc faircenter in which the controller operates
     */
    public UC43Controller(FairCenter fc) {
        this.fc = fc;
        calculations = new Calculations();

    }

    /**
     * returns the global acceptance rate
     *
     * @return global acceptance rate (double)
     */
    public double getGlobalAcceptanceRate() {
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventList = er.getAllEvents();
        List<String> acceptanceList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            ApplicationList applicationList = eventList.get(i).getApplicationList();
            List<Application> applications = applicationList.getApplications();
            for (int j = 0; j < applications.size(); j++) {
                if (applications.get(j).getAcceptance()) {
                    acceptanceList.add("accepted");
                } else {
                    acceptanceList.add("not accepted");
                }
            }
        }
        return calculations.getFrequency("accepted", acceptanceList);
    }
}
