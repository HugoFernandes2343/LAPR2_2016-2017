/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lapr.project.utils.ApplicationState;

/**
 *
 * @author Hugo
 */
@XmlRootElement
public class ApplicationGivenStandState implements ApplicationState {

    @XmlTransient
    public Application a;

    public ApplicationGivenStandState(Application a) {
        this.a = a;
    }

    public ApplicationGivenStandState() {
        //Avoiding xml conflicts
    }

    @Override
    public boolean validateState() {
        //Specific
        return false;
    }

    @Override
    public boolean setApplicationCreatedState() {
        return false;
    }

    @Override
    public boolean setApplicationAssignedState() {
        return false;
    }

    @Override
    public boolean setApplicationEvaluatedState() {
        return false;
    }

    @Override
    public boolean setApplicationGivenStandState() {
        return false;
    }

    @Override
    public boolean setApplicationDecidedState() {
        if (validateState()) {
            a.setState(new ApplicationGivenStandState(a));
            return true;
        } else {
            return false;
        }
    }

}
