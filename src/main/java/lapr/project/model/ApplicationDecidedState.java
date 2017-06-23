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
public class ApplicationDecidedState implements ApplicationState {

    @XmlTransient
    public Application a;

    /**
     * default constructor
     *
     * @param a application to set as this application
     */
    public ApplicationDecidedState(Application a) {
        this.a = a;
    }

    /**
     * constructor to avoid xml conflicts
     */
    public ApplicationDecidedState() {
        //Avoiding xml conflicts
    }

    /**
     * validate method that validates the transition into the next state
     *
     * @return true if able to go to next state false if not able
     */
    @Override
    public boolean validateState() {
        //Specific
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
     * override metho of the abstract class
     *
     * @return true if validate is verified false if validate is not verified
     */
    @Override
    public boolean setApplicationEvaluatedState() {
        return false;
    }

    /**
     * override metho of the abstract class
     *
     * @return true if validate is verified false if validate is not verified
     */
    @Override
    public boolean setApplicationGivenStandState() {
        return false;
    }

    /**
     * override method of the abstract class
     *
     * @return false this transition can not happen
     */
    @Override
    public boolean setApplicationDecidedState() {
        return false;
    }

}
