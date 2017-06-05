/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
//        menuWindow.setResizable(false);
        menuWindow.setLocationRelativeTo(null);
        menuWindow.pack();
        menuWindow.setVisible(true);
        return menuWindow;
    }

    private void createElements(JFrame menuWindow) {
        JPanel infoUser = new JPanel();
        JLabel userLabel = new JLabel("Logged in as : "+this.user.getNome()+" \n Username : "+this.user.getUsername()+" \n Email : "+this.user.getEmail());
        infoUser.add(userLabel);
        JPanel buttonPanel = new JPanel(new GridLayout(0,5,5,5));
        buttonPanel.add(UC01Button);
        buttonPanel.add(UC02Button);
        buttonPanel.add(UC03Button);
        buttonPanel.add(UC04Button);
        buttonPanel.add(UC05Button);
        buttonPanel.add(UC06Button);
        buttonPanel.add(UC07Button);
        buttonPanel.add(UC08Button);
        buttonPanel.add(UC09Button);
        buttonPanel.add(UC10Button);
        //Add thr rest + actionListener
        menuWindow.add(infoUser,BorderLayout.NORTH);
        menuWindow.add(buttonPanel,BorderLayout.CENTER);
        
    }

    public void setActiveUser(User user) {
        this.user = user;
    }

    private User getActiveUser() {
        return this.user;
    }

}
