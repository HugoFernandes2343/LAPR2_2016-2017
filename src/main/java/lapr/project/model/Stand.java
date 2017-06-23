/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
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
        //Avoid xml conflicts
    }

    @Override
    public String toString() {
        return "Area:" + area + "Description:" + description;
    }

    public String getDescription() {
        return description;
    }

    public double getArea() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stand)) {
            return false;
        }

        Stand that = (Stand) o;

        if (!Double.toString(area).equals((Double.toString(that.area)))) {
            return false;
        }

        return this.description.equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.area) ^ (Double.doubleToLongBits(this.area) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.description);
        return hash;
    }
}
