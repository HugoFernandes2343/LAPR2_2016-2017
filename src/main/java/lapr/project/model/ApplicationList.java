/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationList {

    @XmlElement(name = "application")
    private List<Application> applications;
/**
     * Constructor for applicationList
     */
    public ApplicationList() {
        this.applications = new ArrayList<>();
    }

    /**
     creates an object Application
     *
     * @param tradeName trade name to give to the application
     * @param description description to give to the application
     * @param address address to give to the application
     * @param phone phone number to give to the application
     * @param boothArea intended booth area to give to the application
     * @param productsToBeDisplayed products to be displayed to give to the
     * application
     * @param numberOfInvitations number of invitatios to give to the
     * application
     * @param keywords keywords to give to the application
     * @return application
     */
    public Application createApplication(String tradeName, String description, String address, int phone, double boothArea, String[] productsToBeDisplayed, int numberOfInvitations, String[] keywords) {
        Application application = new Application(tradeName, description, address, phone, boothArea, productsToBeDisplayed, numberOfInvitations, keywords);
        return application;
    }
/**
     * registers the application object in the list
     * @param application object to register
     * @return true indicating it has been registered
     */
    public boolean registerApplication(Application application) {
        applications.add(application);
        return true;
    }
/**
     * registers the application object in the list
     * @param application object to register
     */
    public void addApplication(Application application) {
        applications.add(application);
    }
/**
     * returns applications ready for the FAE evaluation
     * @return list of applicaitons
     */
    public List<Application> getApplicationsReadyForFAEEvaluation() {
        List<Application> applicationsReadyForFAEEvaluation = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getApplicationState() instanceof ApplicationAssignedState) {
                applicationsReadyForFAEEvaluation.add(applications.get(i));
            }
        }
        return applicationsReadyForFAEEvaluation;
    }
/**
     * returns listo of applicaitons ready for organizer decision
     * @return list of applications
     */
    public List<Application> getApplicationsReadyForOrganizerDecision() {
        List<Application> applicationsReadyForOrganizerDecision = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getApplicationState() instanceof ApplicationEvaluatedState) {
                applicationsReadyForOrganizerDecision.add(applications.get(i));
            }
        }
        return applicationsReadyForOrganizerDecision;

    }
/**
     * returns list of applicaitons ready for the participants representatives confirmation
     * @return list of applications
     */
    public List<Application> getApplicationsReadyForParticipantsRepresentativeConfirmation() {
        List<Application> applicationsReadyForParticipantsRepresentativeConfirmation = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getApplicationState() instanceof ApplicationGivenStandState) {
                applicationsReadyForParticipantsRepresentativeConfirmation.add(applications.get(i));
            }
        }
        return applicationsReadyForParticipantsRepresentativeConfirmation;
    }
 /**
     * returns decided applications
     * @return list of applications
     */
    public List<Application> getDecidedApplications() {
        List<Application> definitiveApplications = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getApplicationState() instanceof ApplicationDecidedState) {
                definitiveApplications.add(applications.get(i));
            }
        }
        return definitiveApplications;
    }

    /**
     * saves the changes to an application
     * @param application to save
     * @return true if it saves false if it doesnt
     */
    protected boolean saveApplicationChanges(Application application) {
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).equals(application)) {
                applications.remove(i);
                applications.add(application);
                return true;
            }
        }
        return false;
    }

    /**
     * get method of the atribute applications
     * @return list applications (applicaitons)
     */
    public List<Application> getList() {
        return this.applications;
    }

    /**
     * get method of the atribute applications
     * @return list applications (applicaitons)
     */
    public List<Application> getApplications() {
        return applications;
    }

    /**
     * returns applications evaluated by the FAE 
     * @param faeUserReference fae tho serch by
     * @return list of applications
     */
    public List<Application> getApplicationsEvaluatedByFae(User faeUserReference) {
        List<Application> faeApplications = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).isFAEReviewing(new FAE(faeUserReference))) {
                faeApplications.add(applications.get(i));
            }
        }
        return faeApplications;
    }

    /**
     * returns applications evaluated 
     * @return list of applications
     */
    public List<Application> getApplicationsEvaluatedState() {
        List<Application> evaluatedApplications = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getState() instanceof ApplicationEvaluatedState) {
                evaluatedApplications.set(i, applications.get(i));
            }
        }
        return evaluatedApplications;
    }

    /**
     * check if there is an application from the user 
     * @param user used to search applications by
     * @return true if there is one false if there isnt
     */
    public boolean CheckForApplicationFromUser(User user) {
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getRepresentative().getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return application of representative
     * @param user representative
     * @return application of representative
     */
    public Application getApplicationOfRepresentative(User user) {
        Application application = null;
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getRepresentative().getUser().equals(user)) {
                application = applications.get(i);
            }
        }
        return application;
    }

    /**
     * check if the stand is alread assinged
     * @param stand to compare
     * @return true if he is assigned  false if it isnt
     */
    public boolean checkStandAssigned(Stand stand) {
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getStand().equals(stand)) {
                return true;
            }
        }
        return false;
    }
}
