/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.bind.JAXBException;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.utils.ClearanceCheck;
import lapr.project.utils.MainMenuElements;
import lapr.project.utils.XMLExporter;

/**
 *
 * @author PC
 */
public class MainMenu extends JFrame implements MainMenuElements {

    private static final long serialVersionUID = 1L;
    
    private User user;
    private FairCenter fc;

    private final int windowHeight = 500;
    private final int windowWidth = 800;

    private ClearanceCheck check;

//    private final JFrame this = new JFrame("[PH]FairCenter_Name");

    public MainMenu(FairCenter fc, User u, JFrame window) {
        this.fc = fc;
        this.setActiveUser(u);
        createFrame(window);
        this.pack();
        check = new ClearanceCheck(fc, user);
    }

    private JFrame createFrame(JFrame window) {
        this.setName("[PH]FairCenter_Name");
        this.setSize(windowWidth, windowHeight);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createElements(window);
        this.setLocationRelativeTo(null);
        this.pack();
        return this;
    }

    private void createElements(JFrame window) {
        JPanel infoUser = new JPanel(new BorderLayout());
        JLabel userLabel = new JLabel("<html>Logged in as : " + this.user.getName() + " <br> Username : " + this.user.getUsername() + " <br> Email : " + this.user.getEmail());
        infoUser.add(userLabel);
        JPanel buttonPanel = new JPanel(new GridLayout(0, 5, 8, 8));
        addAllButtons(buttonPanel);
        addActions(window);
        int width = 10,height=25;
        LogoutButton.setSize(new Dimension(width, height));
        infoUser.add(LogoutButton, BorderLayout.EAST);
        this.add(infoUser, BorderLayout.PAGE_START);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(addImportExportAllDataButtons(this), BorderLayout.PAGE_END);

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
        buttonPanel.add(UC21Button);
        //~
        buttonPanel.add(UC31Button);
        buttonPanel.add(UC32Button);
    }

    private void addActions(JFrame window) {
        String notImplemented = "Not Implemented";
        LogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setActiveUser(null);
                MainMenu.this.setVisible(false);
                MainMenu.this.dispose();
                window.setVisible(true);
            }
        });

        UC01Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC01UI uc01ui = new UC01UI(fc, MainMenu.this);
                    uc01ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Event Managers are permitted",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        UC02Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isOrg()) {
                    MainMenu.this.setVisible(false);
                    UC02UI uc02ui = new UC02UI(fc, user, MainMenu.this);
                    uc02ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Organizers are permitted",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC03Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                JOptionPane.showMessageDialog(MainMenu.this,
                        notImplemented,
                        "UC03",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        );

        UC04Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (check.isFAE()) {
                    MainMenu.this.setVisible(false);
                    UC04UI uc04ui = new UC04UI(fc, user, MainMenu.this);
                    uc04ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only FAE can access",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        
        UC05Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (check.isRepresentative()) {
                    MainMenu.this.setVisible(false);
                    UC05UI uc05ui = new UC05UI(fc, MainMenu.this);
                    uc05ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only representatives can access",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC06Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                MainMenu.this.setVisible(false);
                UC06UI uc06ui = new UC06UI(fc, user, MainMenu.this);
                uc06ui.setVisible(true);
            }
        }
        );

        UC07Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                JOptionPane.showMessageDialog(MainMenu.this,
                        notImplemented,
                        "UC07",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        );

        UC08Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                JOptionPane.showMessageDialog(MainMenu.this,
                        notImplemented,
                        "UC08",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        );

        UC09Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                JOptionPane.showMessageDialog(MainMenu.this,
                        notImplemented,
                        "UC09",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        );

        UC10Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                JOptionPane.showMessageDialog(MainMenu.this,
                        notImplemented,
                        "UC10",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        );

        UC21Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        UC32Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isOrg()) {
                    try {
                        MainMenu.this.setVisible(false);
                        UC32UI uc32ui = new UC32UI(fc, MainMenu.this);
                        uc32ui.setVisible(true);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Organizers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
    }

    private JPanel addImportExportAllDataButtons(JFrame menuWindow) {
        JPanel confirmationDataPanel = new JPanel();
        UC30Button.addActionListener(new ActionListener() {
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
                }
            }
        });

        UC31Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UC31UI uc31ui = new UC31UI(menuWindow, fc);
            }
        });
        confirmationDataPanel.add(UC30Button);
        confirmationDataPanel.add(UC31Button);
        return confirmationDataPanel;
    }

    public void setActiveUser(User user) {
        this.user = user;
    }
}
