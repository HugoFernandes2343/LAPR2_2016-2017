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

    public ApplicationList() {
        this.applications = new ArrayList<>();
    }

    /**
     *
     * @param tradeName
     * @param address
     * @param phone
     * @param boothArea
     * @param productsToBeDisplayed
     * @param numberOfInvitations
     * @param keywords
     * @return
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

    public boolean registerApplication(Application application) {
        applications.add(application);
        return true;
    }

    public void addApplication(Application application) {
        applications.add(application);
    }

    public List<Application> getApplicationsReadyForFAEEvaluation() {
        List<Application> applicationsReadyForFAEEvaluation = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getApplicationState() instanceof ApplicationAssignedState) {
                applicationsReadyForFAEEvaluation.add(applications.get(i));
            }
        }
        return applicationsReadyForFAEEvaluation;
    }

    public List<Application> getApplicationsReadyForOrganizerDecision() {
        List<Application> applicationsReadyForOrganizerDecision = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getApplicationState() instanceof ApplicationEvaluatedState) {
                applicationsReadyForOrganizerDecision.add(applications.get(i));
            }
        }
        return applicationsReadyForOrganizerDecision;

    }

    public List<Application> getApplicationsReadyForParticipantsRepresentativeConfirmation() {
        List<Application> applicationsReadyForParticipantsRepresentativeConfirmation = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getApplicationState() instanceof ApplicationGivenStandState) {
                applicationsReadyForParticipantsRepresentativeConfirmation.add(applications.get(i));
            }
        }
        return applicationsReadyForParticipantsRepresentativeConfirmation;
    }

    public List<Application> getDefinitiveApplications() {
        List<Application> definitiveApplications = new ArrayList<>();
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getApplicationState() instanceof ApplicationDecidedState) {
                definitiveApplications.add(applications.get(i));
            }
        }
        return definitiveApplications;
    }

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

    public List<Application> getList() {
        return this.applications;
    }

}
