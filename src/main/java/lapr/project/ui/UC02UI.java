/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import javax.swing.JFrame;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.controller.UC02Controller;

/**
 *
 * @author PC
 */
public class UC02UI extends JFrame {

    private static final long serialVersionUID = 1L;

    public UC02UI(FairCenter fc, User u) {
        UC02Controller uc02Controller = new UC02Controller(fc,u);

    }

}
