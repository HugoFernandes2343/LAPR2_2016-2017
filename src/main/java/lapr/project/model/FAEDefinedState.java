/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.xml.bind.annotation.*;
import lapr.project.utils.FAEState;

/**
 *
 * @author PC
 */
@XmlRootElement
public class FAEDefinedState implements FAEState {

    @XmlTransient
    public FAE fae;

    public FAEDefinedState(FAE fae) {
        this.fae = fae;
    }

    public FAEDefinedState() {
        //Avoiding xml conflicts
    }

    @Override
    public boolean validate() {
        if (fae.getUser() != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean setFAECreatedState() {
        return false;
    }

    @Override
    public boolean setFAEDefinedState() {
        return false;
    }

}
