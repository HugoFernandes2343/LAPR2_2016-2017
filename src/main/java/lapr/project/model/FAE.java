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
public class FAE {

    private FAEState state;

    private final User u;

    public FAE(User u) {
        setState(new FAECreatedState(this));
        this.u = u;
    }

//    public FAE(){
//        this.state= new FAECreatedState();
//    }
    public User getUser() {
        return this.u;
    }

    public void setState(FAEState state) {
        this.state = state;
    }

    public FAEState getState() {
        return this.state;
    }

    public void valida() {
        state.setFAEDefinedState();
    }
}
