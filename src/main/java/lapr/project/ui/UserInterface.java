/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import lapr.project.model.FairCenter;

/**
 *
 * @author PC
 */
public class UserInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    
    public UserInterface(FairCenter fc){
        //SetLookAndFeel
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        //CreateUI
        LoginUI loginUI = new LoginUI(fc);
        loginUI.setVisible(true);
    }
}
