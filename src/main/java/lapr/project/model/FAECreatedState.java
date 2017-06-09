/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.FAEState;

/**
 *
 * @author PC
 */
public class FAECreatedState implements FAEState {

    public FAE fae;

    public FAECreatedState(FAE fae) {
        this.fae = fae;
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
