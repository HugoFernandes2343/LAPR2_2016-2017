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
public class FAECreatedState implements FAEState {

    @XmlTransient
    public FAE fae;

    public FAECreatedState(FAE fae) {
        this.fae = fae;
    }

    public FAECreatedState() {
        //Avoiding xml conflicts
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public boolean setFAECreatedState() {
        return false;
    }

    @Override
    public boolean setFAEDefinedState() {
        if (validate()) {
            fae.setState(new FAEDefinedState(fae));
            return true;
        } else {
            return false;
        }
    }
}
