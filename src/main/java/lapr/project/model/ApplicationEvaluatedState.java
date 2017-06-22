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
public class ApplicationEvaluatedState implements ApplicationState{
    
    @XmlTransient
    public Application a;

    public ApplicationEvaluatedState(Application a) {
        this.a = a;
    }
    
    public ApplicationEvaluatedState(){
        //Avoiding xml conflicts
    }
    
    @Override
    public boolean validateState() {
       if (a.getStand() == null) {
            return true;
        }
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
       if (validateState()) {
            a.setState(new ApplicationGivenStandState(a));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean setApplicationDecidedState() {
        return false;
    }
    
}
