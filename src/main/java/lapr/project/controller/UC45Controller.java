/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lapr.project.model.Application;
import lapr.project.model.ApplicationList;
import lapr.project.model.Calculations;
import lapr.project.model.Event;
import lapr.project.model.EventRegistry;
import lapr.project.model.FairCenter;

/**
 *
 * @author Hugo
 */
public class UC45Controller {

    private final Calculations calculations;
    private final FairCenter fc;
    private Object[][] dataTable;
    private List<Event> validEvents;
    private Event event1;
    private Event event2;

    public UC45Controller(FairCenter fc) {
        this.fc = fc;
        calculations = new Calculations();
    }

    public Object[][] generateFinalDataTable(int sample1, int sample2, int significance) {

        int nAccepted1, nAccepted2;
        List<Application> applicationSampleList1;
        List<Application> applicationSampleList2;
        dataTable[0][0] = event1.getTitle();
        dataTable[0][1] = event2.getTitle();
        dataTable[0][2] = sample1;
        dataTable[0][3] = sample2;
        dataTable[0][4] = significance;
        dataTable[0][5] = calculations.getCriticalValueBylateralTest(significance);
        applicationSampleList1 = getRandomSample(event1.getApplicationList().getApplications(), sample1);
        applicationSampleList2 = getRandomSample(event1.getApplicationList().getApplications(), sample2);
        nAccepted1 = getAcceptedApplications(applicationSampleList1);
        nAccepted2 = getAcceptedApplications(applicationSampleList2);
        dataTable[0][6] = calculations.getDiferenceTestStatistic(sample1, nAccepted1, sample2, nAccepted2);
        if ((double) dataTable[0][6] < -(double) dataTable[0][5] || (double) dataTable[0][6] > (double) dataTable[0][5]) {
            dataTable[0][7] = "YES";
        } else {
            dataTable[0][7] = "NO";
        }

        return dataTable;
    }

    public String[] getValidEvents() {
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventList = er.getAllEvents();
        validEvents = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            ApplicationList applicationList = eventList.get(i).getApplicationList();
            List<Application> applications = applicationList.getApplications();
            if (applications.size() >= 30) {
                validEvents.add(eventList.get(i));
            }
        }
        String[] eventTitles = new String[validEvents.size()];
        for (int j = 0; j < validEvents.size(); j++) {
            eventTitles[j] = validEvents.get(j).getTitle();
        }
        return eventTitles;
    }

    public Object[][] generateInitialTable() {
        dataTable = new Object[1][8];
        return dataTable;
    }

    private List<Application> getRandomSample(List<Application> applications, int sampleSize) {
        List<Application> applicationSample = new ArrayList<>();
        Random rn = new Random();
        int[] rnList = new int[sampleSize];
        int counter = 0;
        do {
            int number = rn.nextInt(applications.size() - 1);
            if (validateIndex(number, rnList, counter)) {
                rnList[counter] = number;
                counter++;
            }
        } while (counter < sampleSize);
        for (int i = 0; i < rnList.length; i++) {
            applicationSample.add(applications.get(rnList[i]));
        }

        return applicationSample;
    }

    private boolean validateIndex(int number, int[] rnList, int counter) {
        for (int i = 0; i < counter - 1; i++) {
            if (number == rnList[i]) {
                return false;
            }
        }
        return true;
    }

    private int getAcceptedApplications(List<Application> applicationSampleList) {
        int counter = 0;
        for (int i = 0; i < applicationSampleList.size(); i++) {
            if (applicationSampleList.get(i).getAcceptance() != false) {
                counter++;
            }
        }
        return counter;
    }

    public void setEvent1(int selectedIndex) {
        event1 = validEvents.get(selectedIndex);
    }

    public int getEvent1MaxSample() {
        List<Application> applications = event1.getApplicationList().getApplications();
        return applications.size();
    }

    public Object getEvent2MaxSample() {
        List<Application> applications = event2.getApplicationList().getApplications();
        return applications.size();
    }

    public void setEvent2(int selectedIndex) {
        event2 = validEvents.get(selectedIndex);
    }

    public boolean validateInputData(int sample1, int sample2, int significance) {
        if (sample1 > event1.getApplicationList().getApplications().size()) {
            return false;
        }
        if (sample2 > event2.getApplicationList().getApplications().size()) {
            return false;
        }
        if (significance != 5 && significance != 1) {
            return false;
        }
        return true;
    }

}
