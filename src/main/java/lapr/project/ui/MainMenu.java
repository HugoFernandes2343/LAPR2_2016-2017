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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.bind.JAXBException;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.utils.MainMenuElements;
import lapr.project.utils.XMLExporter;

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
        menuWindow.add(addSaveButton(), BorderLayout.PAGE_END);

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
                uc01ui.setVisible(true);
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

        UC03Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menuWindow,
                        "Not implemented",
                        "UC03",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        UC06Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuWindow.setVisible(false);
                UC06UI uc06ui = new UC06UI(fc, user, menuWindow);
                uc06ui.setVisible(true);
            }
        });

        //Add
        UC07Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menuWindow,
                        "Not implemented",
                        "UC07",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        UC08Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menuWindow,
                        "Not implemented",
                        "UC08",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        UC09Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menuWindow,
                        "Not implemented",
                        "UC09",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        UC10Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(menuWindow,
                        "Not implemented",
                        "UC10",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JPanel addSaveButton() {
        JPanel confirmationDataPanel = new JPanel();
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XMLExporter<?> exporter = new XMLExporter<>();
                    exporter.exportAllData(fc);
                    JOptionPane.showMessageDialog(menuWindow,
                            "Saved",
                            "Saving",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (JAXBException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.getMessage());
                }
            }
        });
        confirmationDataPanel.add(SaveButton);
        return confirmationDataPanel;
    }

    public void setActiveUser(User user) {
        this.user = user;
    }
}
