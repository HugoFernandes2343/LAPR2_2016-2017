/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "stand")
@XmlAccessorType(XmlAccessType.FIELD)
public class Stand {

    private double area;
    private String description;

    public Stand(double area, String description) {
        this.area = area;
        this.description = description;
    }

    public Stand() {

    }

    public boolean verifyAvailability() {
        // TODO - implement Stand.verifyAvailability
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Area:" + area + "Description:" + description;
    }

    public double getArea() {
        return area;
    }
}
