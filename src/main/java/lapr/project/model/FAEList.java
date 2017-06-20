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
    private List<FAE> FaeList;
    private List<FAE> tempList;
    private FAE temp;

    public FAEList() {
        this.FaeList = new ArrayList<>();
        this.tempList = new ArrayList<>();
    }

    public List<FAE> getList() {
        return this.FaeList;
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
        FaeList.addAll(tempList);
    }
}
