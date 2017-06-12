/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import javax.xml.bind.annotation.*;
import lapr.project.model.FAECreatedState;
import lapr.project.model.FAEDefinedState;


/**
 *
 * @author PC
 */
@XmlTransient
@XmlSeeAlso({FAECreatedState.class,FAEDefinedState.class})
public interface FAEState {
    
    public boolean validate();
    public boolean setFAECreatedState();
    public boolean setFAEDefinedState();
    
}
