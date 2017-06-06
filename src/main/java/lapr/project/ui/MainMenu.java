/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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

    private FairCenter fc;
    
    public MainMenu(FairCenter fc, User u) {
        this.fc=fc;
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
        JPanel infoUser = new JPanel(new GridLayout(1,5));
        JLabel userLabel = new JLabel("<html>Logged in as : "+this.user.getNome()+" <br> Username : "+this.user.getUsername()+" <br> Email : "+this.user.getEmail());
        infoUser.add(userLabel);
        JPanel buttonPanel = new JPanel(new GridLayout(0,5,8,8));
        addAllButtons(buttonPanel);
        addActions(buttonPanel);
        menuWindow.add(infoUser,BorderLayout.PAGE_START);
        menuWindow.add(buttonPanel,BorderLayout.CENTER);
        
    }

    private JPanel addAllButtons(JPanel buttonPanel) {
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
        buttonPanel.add(UC11Button);
        return buttonPanel;
    }
    
    private JPanel addActions(JPanel panel){
        UC01Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UC01UI uc01ui = new UC01UI(fc,user);
            }
        });
        
        UC02Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UC02UI uc02ui = new UC02UI(fc,user);
            }
        });
        return panel;
    }
    
    public void setActiveUser(User user) {
        this.user = user;
    }

    private User getActiveUser() {
        return this.user;
    }



}
