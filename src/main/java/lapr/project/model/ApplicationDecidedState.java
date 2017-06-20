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
 * @author hugod
 */
@XmlRootElement
public class ApplicationDecidedState implements ApplicationState {
    @XmlTransient
    public Application a;

    public ApplicationDecidedState(Application a) {
        this.a = a;
    }
    
    public ApplicationDecidedState(){
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
        return false;
    }

}
