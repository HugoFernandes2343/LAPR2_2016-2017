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
 * @author LAPR2-G054
 */
@XmlRootElement
public class ApplicationEvaluatedState implements ApplicationState {

    @XmlTransient
    public Application a;

    /**
     * default constructor
     *
     * @param a application to set as this application
     */
    public ApplicationEvaluatedState(Application a) {
        this.a = a;
    }

    /**
     * constructor to avoid xml conflicts
     */
    public ApplicationEvaluatedState() {
        //Avoiding xml conflicts
    }

    /**
     * validate method that validates the transition into the next state
     *
     * @return true if able to go to next state false if not able
     */
    @Override
    public boolean validateState() {
        if (a.getStand() == null) {
            return true;
        }
        return false;
    }

    /**
     * override method of the abstract class
     *
     * @return false this transition can not happen
     */
    @Override
    public boolean setApplicationCreatedState() {
        return false;
    }

    /**
     * override method of the abstract class
     *
     * @return false this transition can not happen
     */
    @Override
    public boolean setApplicationAssignedState() {
        return false;
    }

    /**
     * override method of the abstract class
     *
     * @return false this transition can not happen
     */
    @Override
    public boolean setApplicationEvaluatedState() {
        return false;
    }

    /**
     * override method of the abstract class
     *
     * @return false this transition can not happen
     */
    @Override
    public boolean setApplicationGivenStandState() {
        if (validateState()) {
            a.setState(new ApplicationGivenStandState(a));
            return true;
        } else {
            return false;
        }
    }

    /**
     * override method of the abstract class
     *
     * @return true if validate is verified false if validate is not verified
     */
    @Override
    public boolean setApplicationDecidedState() {
        return false;
    }

}
