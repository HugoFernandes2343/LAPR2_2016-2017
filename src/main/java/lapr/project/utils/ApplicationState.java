/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import javax.xml.bind.annotation.*;
import lapr.project.model.ApplicationAssignedState;
import lapr.project.model.ApplicationEvaluatedState;
import lapr.project.model.ApplicationGivenStandState;

/**
 *
 * @author hugod
 */
@XmlTransient
@XmlSeeAlso({ApplicationAssignedState.class,ApplicationEvaluatedState.class,ApplicationGivenStandState.class})
public interface ApplicationState {
    public abstract boolean validateState();
    public abstract void setNextState(); 
    public boolean setApplicationCreatedState();
    public boolean setApplicationAssignedState();
    public boolean setApplicationEvaluatedState();
    public boolean setApplicationGivenStandState();
    public boolean setApplicationDecidedState();
}
