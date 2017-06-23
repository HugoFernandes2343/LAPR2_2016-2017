/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LAPR2-G054
 */
@XmlRootElement(name = "assignment")
public class Assignment {

    @XmlElement(name = "fae")
    private FAE FAE;
/**
     * default constructor
     *
     * @param FAE user that has been assigned an application
     */
    public Assignment(FAE FAE) {
        this.FAE = FAE;
    }
 /**
     * constructor to avoid xml conflicts
     */
    private Assignment() {
        //Avoid xml conflicts
    }
/**
     * hashcode for the equals method
     *
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return FAE.hashCode();
    }
/**
     * override of equals method
     *
     * @param o object to compare too
     * @return true if equals false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Assignment)) {
            return false;
        }

        Assignment that = (Assignment) o;

        return FAE.equals(that.FAE);

    }
/**
     * get method of the attribute FAE
     *
     * @return FAE
     */
    public FAE getFAE() {
        return FAE;
    }
}
