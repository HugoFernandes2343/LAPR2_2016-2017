/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class FAEList {

    private ArrayList<FAE> FaeList;
    private ArrayList<FAE> tempList;
    private FAE temp;

    public FAEList() {
        this.FaeList = new ArrayList<>();
        this.tempList = new ArrayList<>();
    }

    public ArrayList<FAE> getList() {
        return this.FaeList;
    }

    public void createFAE(User u) {
        temp = new FAE(u);
        this.addFAEs();
    }

    public void validateFAE() {
        temp.valida();
    }

    public void addFAEs() {
        this.tempList.add(temp);
    }

    public void discardFAE() {
        this.temp = null;
    }

    public void registerFAEs() {
        FaeList.addAll(tempList);
    }

    /**
     * For test reasons only
     *
     * @param fae
     */
    public void addFAE(FAE fae) {
        this.FaeList.add(fae);
    }
}
