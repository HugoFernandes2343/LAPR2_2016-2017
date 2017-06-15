/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;

public class ApplicationList {

    private ArrayList<Application> Applications;

    public ApplicationList() {
        this.Applications = new ArrayList<>();
    }

    /**
     * creates a new application
     */
    public Application createApplication(String tradeName, String address, int phone, double boothArea, String[] productsToBeDisplayed, int numberOfInvitations, String[] keywords) {
        Application application = new Application(tradeName, address, phone, boothArea, productsToBeDisplayed, numberOfInvitations, keywords);
        return application;
    }

    /**
     * saves the application as part of the event
     *
     * @param application
     */
    public void registerNewApplication(Application application) {
        // TODO - implement ApplicationList.registerNewApplication
        throw new UnsupportedOperationException();
    }

    /**
     * checks the validity of the application
     *
     * @param application
     */
    public boolean verifyApplication(Application application) {
        // TODO - implement ApplicationList.verifyApplication
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param state
     */
    public ArrayList<Application> getApplicationsByState(ApplicationState state) {
        // TODO - implement ApplicationList.getApplicationsByState
        throw new UnsupportedOperationException();
    }

    public boolean registerApplication(Application application) {
        Applications.add(application);
        return true;
    }

    public void addApplication(Application application) {
       Applications.add(application);
    }

}
