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
import lapr.project.model.Review;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
public class UC49Controller {

    private final Calculations calculations;
    private final FairCenter fc;
    private Object[][] dataTable;
    private List<User> validFae;

    public UC49Controller(FairCenter fc) {
        this.fc = fc;
        validFae = new ArrayList<>();
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
        double globalSubmissionMeanRate = getSubmissionGlobalMeanRate();
        for (int i = 0; i < validFae.size(); i++) {
            List<Review> faeReviews = getRandomSample(getFaeReview(validFae.get(i)), sampleSize[i]);
            double critValue = calculations.getCriticalValueUnilateralTest(significance[i]);
            dataTable[i][1] = sampleSize[i];
            dataTable[i][2] = significance[i];
            dataTable[i][3] = calculations.getFaeMeanRate(faeReviews);
            dataTable[i][4] = calculations.getFaeMeanStandardDeviationRate(globalSubmissionMeanRate, faeReviews);
            dataTable[i][5] = calculations.getFaeMeanStandardDeviation(globalSubmissionMeanRate, faeReviews);
            dataTable[i][6] = calculations.getTestStatisticFae(calculations.getFaeMeanStandardDeviationRate(globalSubmissionMeanRate, faeReviews), calculations.getFaeMeanStandardDeviation(globalSubmissionMeanRate, faeReviews), sampleSize[i]);

            if ((double) dataTable[i][6] > critValue) {
                dataTable[i][7] = "YES";
            } else {
                dataTable[i][7] = "NO";
            }
        }
        return dataTable;
    }

    public Object[][] generateInitialTable() {
        getValidFae();
        dataTable = new Object[validFae.size()][8];
        for (int i = 0; i < validFae.size(); i++) {
            dataTable[i][0] = validFae.get(i).getName();
            dataTable[i][1] = getFaeReview(validFae.get(i)).size();
        }
        return dataTable;
    }

    private List<Review> getRandomSample(List<Review> reviews, int sampleSize) {
        List<Review> reviewSample = new ArrayList<>();
        Random rn = new Random();
        int[] rnList = new int[sampleSize];
        int counter = 0;
        do {
            int number = rn.nextInt(reviews.size() - 1);
            if (validateIndex(number, rnList, counter)) {
                rnList[counter] = number;
                counter++;
            }
        } while (counter < sampleSize);
        for (int i = 0; i < rnList.length; i++) {
            reviewSample.add(reviews.get(rnList[i]));
        }
        return reviewSample;
    }

    private boolean validateIndex(int number, int[] rnList, int counter) {
        for (int i = 0; i < counter - 1; i++) {
            if (number == rnList[i]) {
                return false;
            }
        }
        return true;
    }

    private double getSubmissionGlobalMeanRate() {
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

        return calculations.getFaeMeanRate(allFaeReviews);
    }

    private List<User> getValidFae() {
        List<User> allFae = getAllFae();
        for (int i = 0; i < allFae.size(); i++) {
            List<Review> faeReview = getFaeReview(allFae.get(i));
            if (faeReview.size() >= 30) {
                validFae.add(allFae.get(i));
            }
        }
        return validFae;
    }

    private List<Review> getFaeReview(User user) {
        List<Review> faeReviews = new ArrayList<>();
        EventRegistry er = fc.getEventRegistry();
        List<Event> eventList = er.getEventsByFAE(user);
        for (int i = 0; i < eventList.size(); i++) {
            ApplicationList applicationList = eventList.get(i).getApplicationList();
            List<Application> applications = applicationList.getApplicationsEvaluatedByFae(user);
            for (int j = 0; j < applications.size(); j++) {
                List<Review> reviews = applications.get(j).getReviews();
                for (int a = 0; a < reviews.size(); a++) {
                    if (reviews.get(a).getAssignment().getFAE().getUser().equals(user)) {
                        if (reviews.get(a).hasFaeEvaluated()) {
                            faeReviews.add(reviews.get(a));
                        }
                    }
                }
            }
        }
        return faeReviews;
    }

    private List<User> getAllFae() {
        EventRegistry er = fc.getEventRegistry();
        List<User> faeUserReferenceList = er.getAllFaeUserReference();
        return faeUserReferenceList;
    }
}
