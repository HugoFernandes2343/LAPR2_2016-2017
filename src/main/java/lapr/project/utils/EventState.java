/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import javax.xml.bind.annotation.*;
import lapr.project.model.*;


/**
 *
 * @author PC
 */
@XmlTransient
@XmlSeeAlso({EventCreatedState.class,EventDefinedFAEState.class})//Add the rest
public interface EventState {
    
    public boolean validate();
    public boolean setEventCreatedState();
    public boolean setEventDefinedFAEState();
    public boolean setEventApplicationsOpenState();
   // public boolean setEventApplicationsEvaluatingState();  THIS IS TO BE CHANGED TO IMPLEMENT UC04 
    //Rest as needed
}

