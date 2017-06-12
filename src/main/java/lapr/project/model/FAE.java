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
@XmlAccessorType(XmlAccessType.FIELD)
public class FAE {

    @XmlElement
    private FAEState state;

    @XmlElement
    private User u;

    public FAE(User u) {
        setState(new FAECreatedState(this));
        this.u = u;
    }

    public FAE() {
        //to avoid xml conflicts
    }

    public User getUser() {
        return this.u;
    }

    public void setState(FAEState state) {
        this.state = state;
    }

    public FAEState getFAEState() {
        return state;
    }

    public void valida() {
        state.setFAEDefinedState();
    }
}
