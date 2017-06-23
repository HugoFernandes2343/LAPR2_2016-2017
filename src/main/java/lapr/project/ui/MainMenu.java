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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
    /**
     * Window de login
     */
    private JFrame window;

    private static final int windowHeight = 600;
    private static final int windowWidth = 700;

    private ClearanceCheck check;

    public MainMenu(FairCenter fc, User u, JFrame window) {
        this.fc = fc;
        this.setActiveUser(u);
        this.window = window;
        createFrame();
        this.pack();
        check = new ClearanceCheck(fc, user);
    }

//    public MainMenu(User u){
//        this.opened=true;
//        this.user=u;
//
//    }
    private JFrame createFrame() {
        this.setName("Event Center");
        this.setSize(windowWidth, windowHeight);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Save Changes?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    try {
                        XMLExporter<?> exporter = new XMLExporter<>();
                        exporter.exportAllData(fc);
                        JOptionPane.showMessageDialog(MainMenu.this,
                                "Saved",
                                "Saving",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (JAXBException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dispose();
                    setVisible(false);
                    window.setVisible(true);
                } else {
                    window.setVisible(true);
                    MainMenu.this.dispose();
                    MainMenu.this.setVisible(false);
                }
            }
        };
        addWindowListener(exitListener);
        createElements();
        this.setLocationRelativeTo(null);
        this.pack();
        return this;
    }

    private void createElements() {
        JPanel infoUser = new JPanel(new BorderLayout());
        JLabel userLabel = new JLabel("<html>Logged in as : " + this.user.getName() + " <br> Username : " + this.user.getUsername() + " <br> Email : " + this.user.getEmail());
        infoUser.add(userLabel);
        JPanel buttonPanel = new JPanel(new GridLayout(0, 4, 8, 8));
        addAllButtons(buttonPanel);
        addActions();
        int width = 10, height = 25;
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
        buttonPanel.add(UC22Button);
        buttonPanel.add(UC31Button);
        buttonPanel.add(UC32Button);
        buttonPanel.add(UC40Button);
        buttonPanel.add(UC41Button);
        buttonPanel.add(UC42Button);
        buttonPanel.add(UC43Button);
        buttonPanel.add(UC44Button);
        buttonPanel.add(UC45Button);
        buttonPanel.add(UC46Button);
        buttonPanel.add(UC47Button);
        buttonPanel.add(UC48Button);
        buttonPanel.add(UC49Button);
        buttonPanel.add(UC50Button);
    }

    private void addActions() {
        String notImplemented = "Not Implemented";
        LogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setActiveUser(null);
                MainMenu.this.check.clear();
                MainMenu.this.dispose();
                MainMenu.this.setVisible(false);
                window.setVisible(true);
            }
        });

        UC01Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC01UI uc01ui = new UC01UI(fc, user, MainMenu.this);
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

        UC04Button.addActionListener(new ActionListener() {
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
                if (!check.isOrg() && !check.isEventManager() && !check.isFAE()) {
                    MainMenu.this.setVisible(false);
                    UC05UI uc05ui = new UC05UI(fc, user, MainMenu.this);
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

        UC11Button.addActionListener(//representative
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (check.isRepresentative()) {
                    MainMenu.this.setVisible(false);
                    UC11UI uc11ui = new UC11UI(fc, user, MainMenu.this);
                    uc11ui.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Representatives with applications are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        );

        UC22Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (check.isOrg()) {
                    MainMenu.this.setVisible(false);
                    UC22UI uc22ui = new UC22UI(fc, user, MainMenu.this);
                    uc22ui.setVisible(true);
                }
            }
        });

        UC32Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
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

        UC40Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isOrg()) {
                    MainMenu.this.setVisible(false);
                    UC40UI uc40ui = new UC40UI(fc, user, MainMenu.this);
                    uc40ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Organizers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC41Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isOrg()) {
                    MainMenu.this.setVisible(false);
                    UC41UI uc41ui = new UC41UI(fc, user, MainMenu.this);
                    uc41ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Organizers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC42Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC42UI uc42ui = new UC42UI(fc, user, MainMenu.this);
                    uc42ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Event Managers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC43Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC43UI uc43ui = new UC43UI(fc, user, MainMenu.this);
                    uc43ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Event Managers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC44Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC44UI uc44ui = new UC44UI(fc, MainMenu.this);
                    uc44ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Organizers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC45Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC45UI uc45ui = new UC45UI(fc, MainMenu.this);
                    uc45ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Event Managers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC46Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC46UI uc46ui = new UC46UI(fc, user, MainMenu.this);
                    uc46ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Event Managers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC47Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC47UI uc47ui = new UC47UI(fc, user, MainMenu.this);
                    uc47ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Event Managers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC48Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC48UI uc48ui = new UC48UI(fc, user, MainMenu.this);
                    uc48ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Event Managers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC49Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC49UI uc49ui = new UC49UI(fc, MainMenu.this);
                    uc49ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Event Managers are allowed",
                            "Not allowed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        UC50Button.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check.isEventManager()) {
                    MainMenu.this.setVisible(false);
                    UC50UI uc50ui = new UC50UI(fc, MainMenu.this);
                    uc50ui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainMenu.this,
                            "Only Event Managers are allowed",
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

    public void setActiveFairCenter(FairCenter fc) {
        this.fc = fc;
    }

    public void setLoginWindow(JFrame window) {
        this.window = window;
    }

    public ClearanceCheck getCheck() {
        return this.check;
    }
}
