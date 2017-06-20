/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import lapr.project.controller.UC01Controller;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author PC
 */
public class UC01UI extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final int windowWidth = 1000;
    private final int windowHeight = 600;
    private String eventType;

    private UC01Controller controller;

    public UC01UI(FairCenter fc, JFrame menuWindow) {
        controller = new UC01Controller(fc);
        this.setName("UC01 - Create Event");
        this.createFrame(menuWindow);
        this.pack();
    }

    private void createFrame(JFrame menuWindow) {
        setSize(windowWidth, windowHeight);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure You Want To Exit?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    dispose();
                    setVisible(false);
                    menuWindow.setVisible(true);
                }
            }
        };
        addWindowListener(exitListener);
        createElements(menuWindow);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void createElements(JFrame menuWindow) {
        JPanel allScreens = new JPanel(new CardLayout());
        JPanel eventCreationScreen = new JPanel(new BorderLayout());
        JPanel organizerSelectionScreen = new JPanel(new BorderLayout());
        createEventScreen(eventCreationScreen, allScreens, menuWindow, organizerSelectionScreen);
        allScreens.add(eventCreationScreen, "1");
        allScreens.add(organizerSelectionScreen, "2");
        add(allScreens, BorderLayout.CENTER);
    }

    private void createEventScreen(JPanel screen, JPanel allScreens, JFrame menuWindow, JPanel FaeSelectionScreen) {
        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setSize(200, 200);
        GridBagConstraints pos = new GridBagConstraints();

        JLabel eventTitleLabel = new JLabel("Event Title:");
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(eventTitleLabel, pos);

        JTextField titleField = new JTextField(20);
        pos.gridx = 1;
        pos.gridy = 0;
        centralPanel.add(titleField, pos);

        JLabel descriptionLabel = new JLabel("Description:");
        pos.gridx = 0;
        pos.gridy = 1;
        centralPanel.add(descriptionLabel, pos);

        JTextField descriptionField = new JTextField(20);
        pos.gridx = 1;
        pos.gridy = 1;
        centralPanel.add(descriptionField, pos);

        JLabel placeLabel = new JLabel("Place:");
        pos.gridx = 0;
        pos.gridy = 2;
        centralPanel.add(placeLabel, pos);

        JTextField placeField = new JTextField(20);
        pos.gridx = 1;
        pos.gridy = 2;
        centralPanel.add(placeField, pos);

        JLabel startDateLabel = new JLabel("Start date(dd/mm/yyyy):");
        pos.gridx = 2;
        pos.gridy = 0;
        centralPanel.add(startDateLabel, pos);

        JTextField startDateField = new JTextField(8);
        pos.gridx = 3;
        pos.gridy = 0;
        startDateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (startDateField.getText().length() >= 10) // limit textfield to 10 characters
                {
                    e.consume();
                }
            }
        });
        centralPanel.add(startDateField, pos);

        JLabel endDateLabel = new JLabel("End date(dd/mm/yyyy):");
        pos.gridx = 2;
        pos.gridy = 1;
        centralPanel.add(endDateLabel, pos);

        JTextField endDateField = new JTextField(8);
        pos.gridx = 3;
        pos.gridy = 1;
        endDateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (endDateField.getText().length() >= 10) // limit textfield to 10 characters
                {
                    e.consume();
                }
            }
        });
        centralPanel.add(endDateField, pos);

        JLabel applicationBeginLabel = new JLabel("Applications Begin(dd/mm/yyyy):");
        pos.gridx = 2;
        pos.gridy = 2;
        centralPanel.add(applicationBeginLabel, pos);

        JTextField applicationBeginField = new JTextField(8);
        pos.gridx = 3;
        pos.gridy = 2;
        applicationBeginField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (applicationBeginField.getText().length() >= 10) // limit textfield to 10 characters
                {
                    e.consume();
                }
            }
        });
        centralPanel.add(applicationBeginField, pos);
        JLabel applicationEndLabel = new JLabel("Applications End(dd/mm/yyyy):");
        pos.gridx = 2;
        pos.gridy = 3;
        centralPanel.add(applicationEndLabel, pos);

        JTextField applicationEndField = new JTextField(8);
        pos.gridx = 3;
        pos.gridy = 3;
        applicationEndField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (applicationEndField.getText().length() >= 10) // limit textfield to 10 characters
                {
                    e.consume();
                }
            }
        });
        centralPanel.add(applicationEndField, pos);

        JRadioButton congressButton = new JRadioButton("Congress");
        pos.gridx = 0;
        pos.gridy = 3;
        congressButton.setMnemonic(KeyEvent.VK_R);
        centralPanel.add(congressButton, pos);
        JRadioButton expositionButton = new JRadioButton("Exposition");
        pos.gridx = 1;
        pos.gridy = 3;
        congressButton.setMnemonic(KeyEvent.VK_P);
        centralPanel.add(expositionButton, pos);
        ButtonGroup group = new ButtonGroup();
        group.add(expositionButton);
        group.add(congressButton);
        congressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventType = "Congress";
            }

        });
        expositionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventType = "Exhibition";
            }

        });
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventError="Event creation error";
                String title = titleField.getText();
                String description = descriptionField.getText();
                String place = placeField.getText();
                if (title != null && description != null && place != null && eventType != null) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date startDate = format.parse(startDateField.getText());
                        Date endDate = format.parse(endDateField.getText());
                        Date applicationBegin = format.parse(applicationBeginField.getText());
                        Date applicationEnd = format.parse(applicationEndField.getText());
                        controller.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
                        createFAESelectionScreen(FaeSelectionScreen, menuWindow);
                        CardLayout cl = (CardLayout) (allScreens.getLayout());
                        cl.show(allScreens, "2");//USE a string with a number and its solved 
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(UC01UI.this,
                                "Error detected in the dates. Please check.",
                                eventError,
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(UC01UI.this,
                            "Missing data. Please check.",
                            eventError,
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        }
        );
        pos.gridx = 1;
        pos.gridy = 4;

        centralPanel.add(createButton, pos);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                dispose();
                menuWindow.setVisible(true);
            }
        }
        );
        pos.gridx = 2;
        pos.gridy = 4;

        centralPanel.add(cancelButton, pos);
        screen.add(centralPanel, BorderLayout.PAGE_START);
    }

    private void createFAESelectionScreen(JPanel organizerSelectionScreen, JFrame menuWindow) {//Format not good
        JPanel organizerSelectionBox = new JPanel(new BorderLayout());
        organizerSelectionBox.add(new JLabel("Select the desired organizerss (allows several):"), BorderLayout.PAGE_START);

        List<User> allUsers = controller.getUsers();
        String[] listPresentableOrganizers = new String[allUsers.size()];
        for (int i = 0; i < allUsers.size(); i++) {
            listPresentableOrganizers[i] = allUsers.get(i).toInfoString();
        }
        JList<String> organizerList = new JList<>(listPresentableOrganizers);
        organizerList.setSize(new Dimension(100, 150));
        JScrollPane listScroller = new JScrollPane(organizerList);
        listScroller.setPreferredSize(new Dimension(100, 150));

        JPanel organizerSelectionButtonPanel = new JPanel();
        JButton selectOrganizerButton = new JButton("Add Selection(s)");
        JButton cancelOrganizerSelection = new JButton("Cancel");
        JButton finishOrganizerSelection = new JButton("Finish");

        finishOrganizerSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (controller.getNumberOfOrganizers() >= 2) {
                    int confirm = JOptionPane.showOptionDialog(
                            null, "Are You Sure ?",
                            "Selection Confirmation", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (confirm == 0) {
                         if(controller.registerEvent()){
                        JOptionPane.showMessageDialog(UC01UI.this,
                            "Event created.",
                            "Event creation sucessfull",
                            JOptionPane.INFORMATION_MESSAGE);
                        } else{
                        JOptionPane.showMessageDialog(UC01UI.this,
                            "Event already exists.",
                            "Event creation error",
                            JOptionPane.ERROR_MESSAGE);
                        }
                        dispose();
                        setVisible(false);
                        menuWindow.setVisible(true);
                    } else {
                        dispose();
                        setVisible(false);
                        menuWindow.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(UC01UI.this,
                            "Missing Organizers. You need at least 2 Organizers.",
                            "Event creation error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelOrganizerSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                setVisible(false);
                menuWindow.setVisible(true);
            }

        });

        selectOrganizerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] selection = organizerList.getSelectedIndices();
                for (int i : selection) {
                    if (controller.validateOrganizer(allUsers.get(i))) {
                        controller.newOrganizer(allUsers.get(i));
                    } else {
                        JOptionPane.showMessageDialog(UC01UI.this,
                                "You have already defined this organizer. Please choose another.",
                                "Event creation error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });

        organizerSelectionButtonPanel.add(selectOrganizerButton);
        organizerSelectionButtonPanel.add(finishOrganizerSelection);
        organizerSelectionButtonPanel.add(cancelOrganizerSelection);
        organizerSelectionBox.add(organizerList, BorderLayout.CENTER);
        organizerSelectionScreen.add(organizerSelectionButtonPanel, BorderLayout.PAGE_END);
        organizerSelectionScreen.add(organizerSelectionBox, BorderLayout.CENTER);
    }

}
