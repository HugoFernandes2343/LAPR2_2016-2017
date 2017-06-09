/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

/**
 *
 * @author PC
 */
public interface EventState {
    
    public boolean validate();
    public boolean setEventCreatedState();
    public boolean setEventDefinedFAEState();
    //Rest as needed
}
