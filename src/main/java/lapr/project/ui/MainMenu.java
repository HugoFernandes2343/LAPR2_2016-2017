/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
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
public class MainMenu implements MainMenuElements {

    private User user;

    private final int HEIGHT = 500;
    private final int WIDTH = 800;

    private FairCenter fc;

    private final JFrame menuWindow = new JFrame("[PH]FairCenter_Name");
//    private final JDialog window;

    public MainMenu(FairCenter fc, User u) {
        this.fc = fc;
        setActiveUser(u);
        createFrame();
        menuWindow.pack();
    }

    private JFrame createFrame() {
        menuWindow.setSize(WIDTH, HEIGHT);
        BorderLayout layout = new BorderLayout();
        menuWindow.setLayout(layout);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createElements();
        menuWindow.setLocationRelativeTo(null);
        menuWindow.pack();
        menuWindow.setVisible(true);
        return menuWindow;
    }

    private void createElements() {
        JPanel infoUser = new JPanel(new GridLayout(1, 5));
        JLabel userLabel = new JLabel("<html>Logged in as : " + this.user.getName() + " <br> Username : " + this.user.getUsername() + " <br> Email : " + this.user.getEmail());
        infoUser.add(userLabel);
        JPanel buttonPanel = new JPanel(new GridLayout(0, 5, 8, 8));
        addAllButtons(buttonPanel);
        addActions(buttonPanel);
        menuWindow.add(infoUser, BorderLayout.PAGE_START);
        menuWindow.add(buttonPanel, BorderLayout.CENTER);

    }

    private void addAllButtons(JPanel buttonPanel) {
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
    }

    private void addActions(JPanel panel) {
        UC01Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuWindow.setVisible(false);
                UC01UI uc01ui = new UC01UI(fc, user, menuWindow);
            }
        });

        UC02Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuWindow.setVisible(false);
                UC02UI uc02ui = new UC02UI(fc, user, menuWindow);
                uc02ui.setVisible(true);
            }
        });
    }

    public void setActiveUser(User user) {
        this.user = user;
    }
}
