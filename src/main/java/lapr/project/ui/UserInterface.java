/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import javax.swing.*;
import lapr.project.model.FairCenter;

/**
 *
 * @author PC
 */
@SuppressWarnings("serial")
public class UserInterface extends JFrame {

//    public UserInterface(FairCenter fc) {
//        MainMenu menu = new MainMenu(fc);
//        menu.setActiveUser();
//    }


    public UserInterface(FairCenter fc){
        LoginUI loginUI = new LoginUI(fc);
    }
}
