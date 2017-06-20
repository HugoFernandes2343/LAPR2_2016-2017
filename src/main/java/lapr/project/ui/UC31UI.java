/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import lapr.project.controller.UC31Controller;
import lapr.project.model.FairCenter;
import lapr.project.utils.XMLImporter;

/**
 *
 * @author PC
 */
public class UC31UI {

    public UC31UI(JFrame menuWindow,FairCenter fc) {
        FairCenter newFC = new FairCenter();
        JOptionPane.showMessageDialog(menuWindow,
                "All current data will be overwritten at end of operation",
                "Import",
                JOptionPane.INFORMATION_MESSAGE);
        try {
            XMLImporter importer = new XMLImporter();
            newFC = importer.importAllData();
            JOptionPane.showMessageDialog(menuWindow,
                    "Imported. Logout to apply changes",
                    "Importing All Data",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        UC31Controller uc31Controller = new UC31Controller(fc,newFC);
    }

}
