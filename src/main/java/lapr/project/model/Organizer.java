/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Arrays;
import java.util.Objects;
import javax.xml.bind.annotation.*;

/**
 *
 * @author PC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Organizer {

    @XmlElement
    private User user;

    public Organizer(User user) {
        this.user = user;
    }

    public Organizer() {
        //Avoiding xml conflicts
    }

    public String toString() {
        return user.toString();
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organizer)) {
            return false;
        }

        Organizer that = (Organizer) o;

        return this.user.equals(that.user);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.user);
        return hash;
    }
}
