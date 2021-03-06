/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 *
 * @author PC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FAEList {

    @XmlElement(name="fae")
    private List<FAE> faeList;
    private List<FAE> tempList;
    private FAE temp;

    public FAEList() {
        this.faeList = new ArrayList<>();
        this.tempList = new ArrayList<>();
    }

    public List<FAE> getFaeList() {
        return this.faeList;
    }

    public void createFAE(User u) {
        temp = new FAE(u);
        this.addFAEs();
    }

    public void addFAEs() {
        this.tempList.add(temp);
    }

    public void discardFAEs() {
        this.tempList = new ArrayList<>();
    }

    public void registerFAEs() {
        faeList.addAll(tempList);
    }
}
