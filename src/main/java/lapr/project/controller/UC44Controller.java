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
public class UC44Controller {

    private final Calculations calculations;
    private final FairCenter fc;
    private Object[][] dataTable;
    private List<Event> validEvents;

    public UC44Controller(FairCenter fc) {
        this.fc = fc;
        calculations = new Calculations();
    }

    public boolean validateInputData(int[] sampleSize, int[] significance) {
        for (int i = 0; i < sampleSize.length; i++) {
            int maxSample = (int) dataTable[i][1];
            if (sampleSize[i] > maxSample) {
                return false;
            }
            if (significance[i] != 5 && significance[i] != 1) {
                return false;
            }
        }
        return true;
    }

    public Object[][] generateFinalDataTable(int[] sampleSize, int[] significance) {
        for (int i = 0; i < validEvents.size(); i++) {
            int nAccepted;
            List<Application> applicationSampleList;
            dataTable[i][1] = sampleSize[i];
            dataTable[i][3] = significance[i];
            dataTable[i][2] = calculations.getCriticalValueUnilateralTest(significance[i]);
            applicationSampleList = getRandomSample(validEvents.get(i).getApplicationList().getApplications(), sampleSize[i]);
            nAccepted = getAcceptedApplications(applicationSampleList);
            dataTable[i][4] = calculations.getTestStatistic(sampleSize[i], nAccepted);
            if ((double) dataTable[i][4] > (double) dataTable[i][2]) {
                dataTable[i][5] = "YES";
            } else {
                dataTable[i][5] = "NO";
            }
        }
        return dataTable;
    }

    public Object[][] generateInitialTable() {
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
        dataTable = new Object[validEvents.size()][6];
        for (int j = 0; j < validEvents.size(); j++) {
            dataTable[j][0] = validEvents.get(j).getTitle();
            dataTable[j][1] = validEvents.get(j).getApplicationList().getApplications().size();
        }
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

}
