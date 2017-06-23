/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import lapr.project.controller.UC05Controller;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import java.awt.event.*;
import java.util.List;

/**
 *
 * @author LAPR2-G054
 */
public class UC05UI extends JFrame {

    private static final long serialVersionUID = 1L;

    private UC05Controller uc05Controller;
    private List<Event> eventsList = new ArrayList<>();
    private String[] eventsTitlesList;
    private String eventTitle;
    private String description;
    private Event event;

    private final JButton createButton = new JButton("Create");
    private final JButton selectButton = new JButton("Select");
    private final JButton cancelButton = new JButton("Cancel");
    private final int windowWidth = 525;
    private final int windowHeight = 250;
    private static final String confirmationDialog = "Are You Sure You Want To Exit?";
    private static final String confirmationTitle = "Exit Confirmation";

    /**
     * contructor of UC5UI
     *
     * @param fc fc fair center that contains all data
     * @param u user that is usuing the funcionality
     * @param menuWindow JFrame of the main menu window
     */
    public UC05UI(FairCenter fc, User u, JFrame menuWindow) {
        this.uc05Controller = new UC05Controller(fc, u);
        this.setName("UC05");
        this.eventsList = uc05Controller.getEventsReadyForSubmit();
        this.eventsTitlesList = uc05Controller.getTitlesForSubmit(eventsList);
        createChooseEventFrame(menuWindow);
    }

    private JFrame createChooseEventFrame(JFrame menuWindow) {
        this.setSize(windowWidth, windowHeight);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                int confirm = JOptionPane.showOptionDialog(
                        null, confirmationDialog,
                        confirmationTitle, JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (confirm == 0) {

                    dispose();

                    setVisible(false);

                    menuWindow.setVisible(true);

                }

            }

        };
        this.addWindowListener(exitListener);
        createChooseEventElements(this, menuWindow);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        return this;
    }

    private void createChooseEventElements(JFrame window, JFrame menuWindow) {
        JPanel centralPanel = new JPanel(new GridBagLayout());
        int sideSize = 200;
        centralPanel.setSize(sideSize, sideSize);
        GridBagConstraints pos = new GridBagConstraints();

        JLabel eventLabel = new JLabel("Event:");
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(eventLabel, pos);

        JLabel descriptionLabel = new JLabel();
        pos.gridx = 2;
        pos.gridy = 0;
        centralPanel.add(descriptionLabel, pos);

        JComboBox<String> eventList = new JComboBox<>(eventsTitlesList);
        eventList.setSelectedIndex(-1);
        eventList.addActionListener((ActionEvent ae) -> {
            if (eventList.getSelectedIndex() == -1 && eventsTitlesList.length == 0) {
                JOptionPane.showMessageDialog(UC05UI.this,
                        "No event has been chosen please choose an event");
            } else {
                eventTitle = (String) eventList.getSelectedItem();
                for (int i = 0; i < eventsList.size(); i++) {
                    if (eventsList.get(i).getTitle().equalsIgnoreCase(eventTitle)) {
                        this.description = eventsList.get(i).getDescription();
                        descriptionLabel.setText(description);
                        this.event = eventsList.get(i);
                    }
                }
            }
        });
        pos.gridx = 1;
        pos.gridy = 0;
        centralPanel.add(eventList, pos);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc05Controller.setEvent(event);
                window.dispose();
                createFrame(menuWindow);
            }
        });
        pos.gridx = 1;
        pos.gridy = 1;
        centralPanel.add(selectButton, pos);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                window.dispose();
                menuWindow.setVisible(true);
            }
        });
        pos.gridx = 2;
        pos.gridy = 1;

        centralPanel.add(cancelButton, pos);

        window.add(centralPanel);
    }

    private JFrame createFrame(JFrame menuWindow) {
        JFrame window2 = new JFrame("UC05");
        window2.setSize(windowWidth, windowHeight);
        BorderLayout layout = new BorderLayout();
        window2.setLayout(layout);
        window2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {

            @Override

            public void windowClosing(WindowEvent e) {

                int confirm = JOptionPane.showOptionDialog(
                        null, confirmationDialog,
                        confirmationTitle, JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (confirm == 0) {

                    dispose();

                    setVisible(false);

                    menuWindow.setVisible(true);

                }

            }

        };
        window2.addWindowListener(exitListener);
        createElements(window2, menuWindow);
        window2.setResizable(false);
        window2.setLocationRelativeTo(null);
        window2.setVisible(true);
        return window2;
    }

    private void createElements(JFrame window, JFrame menuWindow) {
        int sideSize = 200, textSize = 20;
        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setSize(sideSize, sideSize);
        GridBagConstraints pos = new GridBagConstraints();

        JLabel tradeNameLabel = new JLabel("Trade name");
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(tradeNameLabel, pos);

        JTextField tradeNameField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 0;
        centralPanel.add(tradeNameField, pos);

        JLabel descriptionLabel = new JLabel("Trade name");
        pos.gridx = 0;
        pos.gridy = 1;
        centralPanel.add(descriptionLabel, pos);

        JTextField descriptionField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 1;
        centralPanel.add(descriptionField, pos);

        JLabel addressLabel = new JLabel("address");
        pos.gridx = 0;
        pos.gridy = 2;
        centralPanel.add(addressLabel, pos);

        JTextField addressField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 2;
        centralPanel.add(addressField, pos);

        JLabel phoneNumberLabel = new JLabel("Phone Number");
        pos.gridx = 0;
        pos.gridy = 3;
        centralPanel.add(phoneNumberLabel, pos);

        JTextField phoneNumberField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 3;
        centralPanel.add(phoneNumberField, pos);

        JLabel intendedBoothAreaLabel = new JLabel("Intended Booth Area");
        pos.gridx = 0;
        pos.gridy = 4;
        centralPanel.add(intendedBoothAreaLabel, pos);

        JTextField intendedBoothAreaField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 4;
        centralPanel.add(intendedBoothAreaField, pos);

        JLabel productsToBeDisplayedLabel = new JLabel("Products To Be Displayed (seperated by ;):");
        pos.gridx = 0;
        pos.gridy = 5;
        centralPanel.add(productsToBeDisplayedLabel, pos);

        JTextField productsToBeDisplayedField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 5;
        centralPanel.add(productsToBeDisplayedField, pos);

        JLabel numberOfInvitationsToPurchaseLabel = new JLabel("Number Of Invitations To Purchase:");
        pos.gridx = 0;
        pos.gridy = 6;
        centralPanel.add(numberOfInvitationsToPurchaseLabel, pos);

        JTextField numberOfInvitationsToPurchaseField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 6;
        centralPanel.add(numberOfInvitationsToPurchaseField, pos);

        JLabel keywordsLabel = new JLabel("Keywords(At least two seperated by ; ): ");
        pos.gridx = 0;
        pos.gridy = 7;
        centralPanel.add(keywordsLabel, pos);

        JTextField keywordsField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 7;
        centralPanel.add(keywordsField, pos);

        createButton.addActionListener((ActionEvent e) -> {
            String tradeName = tradeNameField.getText();
            String description = descriptionField.getText();
            String address = addressField.getText();
            String[] keywords = keywordsField.getText().split(";");
            String[] productsToBeDisplayed = productsToBeDisplayedField.getText().split(";");
            try {
                String phoneNumber = phoneNumberField.getText();
                String intendedBoothArea = intendedBoothAreaField.getText();
                String numberOfInvitationsToPurchase = numberOfInvitationsToPurchaseField.getText();
                if (tradeName != null && description != null && address != null && phoneNumber != null && intendedBoothArea != null && productsToBeDisplayed != null && numberOfInvitationsToPurchase != null && keywords.length <= 5 && keywords.length >= 2) {
                    long phone = Long.parseLong(phoneNumber);
                    double boothArea = Double.parseDouble(intendedBoothArea);
                    int invitations = Integer.parseInt(numberOfInvitationsToPurchase);
                    uc05Controller.createApplication(tradeName, description, address, phone, boothArea, productsToBeDisplayed, invitations, keywords);
                    window.dispose();
                    createFinalConfirmationGUI(menuWindow);
                } else {
                    JOptionPane.showMessageDialog(UC05UI.this,
                            "Missing data. Please check.",
                            "Event creation error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(UC05UI.this,
                        "Data format not supported (Number Data formats wrong)",
                        "Event creation error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        pos.gridx = 0;
        pos.gridy = 8;

        centralPanel.add(createButton, pos);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                menuWindow.setVisible(true);
            }
        }
        );
        pos.gridx = 1;
        pos.gridy = 8;

        centralPanel.add(cancelButton, pos);

        window.add(centralPanel);

    }

    private JFrame createFinalConfirmationGUI(JFrame menuWindow) {
        JFrame window3 = new JFrame("UC05");
        window3.setSize(windowWidth, windowHeight);
        BorderLayout layout = new BorderLayout();
        window3.setLayout(layout);
        window3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, confirmationDialog,
                        confirmationTitle, JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    dispose();
                    setVisible(false);
                    menuWindow.setVisible(true);
                }
            }
        };
        window3.addWindowListener(exitListener);
        createFinalConfirmationComponents(window3, menuWindow);
        window3.setResizable(true);
        window3.setLocationRelativeTo(null);
        window3.setVisible(true);
        return window3;
    }

    private JPanel createFinalConfirmationComponents(JFrame window, JFrame menuWindow) {
        int sideSize = 200;
        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setSize(sideSize, sideSize);
        GridBagConstraints pos = new GridBagConstraints();

        String[] labels = uc05Controller.getApplicationInfo().split(";");

        JLabel tradeNameLabel = new JLabel(labels[0]);
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(tradeNameLabel, pos);

        JLabel addressLabel = new JLabel(labels[1]);
        pos.gridx = 1;
        pos.gridy = 0;
        centralPanel.add(addressLabel, pos);

        JLabel phoneLabel = new JLabel(labels[2]);
        pos.gridx = 0;
        pos.gridy = 1;
        centralPanel.add(phoneLabel, pos);

        JLabel intendedBoothLabel = new JLabel(labels[3]);
        pos.gridx = 1;
        pos.gridy = 1;
        centralPanel.add(intendedBoothLabel, pos);

        JLabel productsToBeDisplayedLabel = new JLabel(labels[4]);
        pos.gridx = 0;
        pos.gridy = 2;
        centralPanel.add(productsToBeDisplayedLabel, pos);

        JLabel keywordsLabel = new JLabel(labels[5]);
        pos.gridx = 1;
        pos.gridy = 2;
        centralPanel.add(keywordsLabel, pos);

        JButton confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc05Controller.registerApplication();
                window.dispose();
                menuWindow.setVisible(true);

            }

        }
        );

        pos.gridx = 0;
        pos.gridy = 3;
        centralPanel.add(confirmButton, pos);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                window.dispose();
                menuWindow.setVisible(true);
            }
        }
        );
        pos.gridx = 1;
        pos.gridy = 3;
        centralPanel.add(cancelButton, pos);

        window.add(centralPanel);
        return centralPanel;
    }
}
