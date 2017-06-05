/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.utils.MainMenuElements;

/**
 *
 * @author PC
 */
public class MainMenu implements MainMenuElements{

    private User user;

    private final int HEIGHT = 500;
    private final int WIDTH = 800;

    
    
    public MainMenu(FairCenter fc, User u) {
        JFrame menuWindow = new JFrame("[PH]FairCenter_Name");
        this.setActiveUser(u);
        createFrame(menuWindow);
        createElements(menuWindow);
//        menuWindow.pack();
    }

    private JFrame createFrame(JFrame menuWindow) {
        menuWindow.setSize(WIDTH, HEIGHT);
        BorderLayout layout = new BorderLayout();
        menuWindow.setLayout(layout);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createElements(menuWindow);
        menuWindow.setResizable(false);
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setVisible(true);
        return menuWindow;
    }

    private void createElements(JFrame menuWindow) {
        JPanel panel = new JPanel(new GridLayout(0,5,2,2));
        panel.add(UC01Button);
        panel.add(UC02Button);
        panel.add(UC03Button);
        panel.add(UC04Button);
        panel.add(UC05Button);
        panel.add(UC06Button);
        //Add thr rest + actionListener
        menuWindow.add(panel);
        
    }

    public void setActiveUser(User user) {
        this.user = user;
    }

    private User getActiveUser() {
        return this.user;
    }

}
