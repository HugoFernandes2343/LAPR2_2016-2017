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
public class UC50Controller {

    private final Calculations calculations;
    private final FairCenter fc;
    private Object[][] dataTable;
    private List<User> validFaes;
    private User fae1;
    private User fae2;

    public UC50Controller(FairCenter fc) {
        this.fc = fc;
        calculations = new Calculations();
        validFaes = new ArrayList<>();
    }

    public Object[][] generateFinalDataTable(int sample1, int sample2, int significance) {
        double globalSubmissionMeanRate = getSubmissionGlobalMeanRate();
        double critValue = calculations.getCriticalValueBylateralTest(significance);
        dataTable[0][2] = sample1;
        dataTable[0][3] = sample2;
        dataTable[0][4] = significance;
        dataTable[0][5] = calculations.getFaeMeanStandardDeviationRate(globalSubmissionMeanRate, getFaeReview(fae1));
        dataTable[0][6] = calculations.getFaeMeanStandardDeviationRate(globalSubmissionMeanRate, getFaeReview(fae2));
        dataTable[0][7] = critValue;
        double standardDeviation1 = calculations.getFaeMeanStandardDeviation(globalSubmissionMeanRate, getFaeReview(fae1));
        double standardDeviation2 = calculations.getFaeMeanStandardDeviation(globalSubmissionMeanRate, getFaeReview(fae2));
        dataTable[0][8] = calculations.getDiferenceTestStatisticFae(sample1, calculations.getFaeMeanStandardDeviationRate(globalSubmissionMeanRate, getFaeReview(fae1)), standardDeviation1, sample2, calculations.getFaeMeanStandardDeviationRate(globalSubmissionMeanRate, getFaeReview(fae2)), standardDeviation2);
        if ((double) dataTable[0][8] < -critValue || (double) dataTable[0][8] > critValue) {
            dataTable[0][9] = "YES";
        } else {
            dataTable[0][9] = "NO";

        }

        return dataTable;
    }

    public Object[][] generateInitialTable() {
        dataTable = new Object[1][10];
        return dataTable;
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

        return calculations.getFaeMeanRate(allFaeReviews);
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

    public void setFae1(int selectedIndex) {
        fae1 = validFaes.get(selectedIndex);
    }

    public int getFae1MaxSample() {
        List<Review> reviews = getFaeReview(fae1);
        return reviews.size();
    }

    public Object getFae2MaxSample() {
        List<Review> reviews = getFaeReview(fae2);
        return reviews.size();
    }

    public void setFae2(int selectedIndex) {
        fae2 = validFaes.get(selectedIndex);
    }

    public boolean validateInputData(int sample1, int sample2, int significance) {
        if (sample1 > getFaeReview(fae1).size()) {
            return false;
        }
        if (sample2 > getFaeReview(fae2).size()) {
            return false;
        }
        if (significance != 5 && significance != 1) {
            return false;
        }
        return true;
    }

    public String[] getValidFaes() {
        List<User> allFae = getAllFae();
        for (int i = 0; i < allFae.size(); i++) {
            List<Review> faeReview = getFaeReview(allFae.get(i));
            if (faeReview.size() >= 30) {
                validFaes.add(allFae.get(i));
            }
        }
        String[] faeList = new String[validFaes.size()];
        for (int j = 0; j < validFaes.size(); j++) {
            faeList[j] = validFaes.get(j).getName();
        }
        return faeList;
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
