/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

/**
 *
 * @author hugod
 */
public interface ApplicationState {
    public abstract boolean validateState();
    public abstract void setNextState(); 
    public boolean setApplicationCreatedState();
    public boolean setApplicationAssignedState();
    public boolean setApplicationEvaluatedState();
    public boolean setApplicationGivenStandState();
    public boolean setApplicationDecidedState();
}
