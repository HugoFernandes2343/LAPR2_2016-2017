/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.FairCenter;

/**
 *
 * @author LAPR2-G054
 */
public class UC31Controller {

    /**
     * changes the faircenter being used in the program to the one that comes
     * from the file
     *
     * @param fc faircenter in which the program was operating
     * @param newFC faircenter in which the program will operate
     */
    public UC31Controller(FairCenter fc, FairCenter newFC) {
        fc = new FairCenter(newFC);
    }

}
