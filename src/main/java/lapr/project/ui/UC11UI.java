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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lapr.project.controller.UC11Controller;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author hugod
 */
public class UC11UI extends JFrame {

    private static final long serialVersionUID = 1L;
    private UC11Controller uc11Controller;
    private List<Event> eventsList = new ArrayList<>();
    private String[] eventsTitlesList;
    private String eventTitle;
    private String description;
    private Event event;

    private final JButton createButton = new JButton("Create");
    private final JButton selectButton = new JButton("Select");
    private final JButton cancelButton = new JButton("Cancel");
    private final int windowWidth = 550;
    private final int windowHeight = 275;
    private static final String confirmationDialog = "Are You Sure You Want To Exit?";
    private static final String confirmationTitle = "Exit Confirmation";

    public UC11UI(FairCenter fc, User u, JFrame menuWindow) {
        this.uc11Controller = new UC11Controller(fc,u);
        this.setName("UC11");
        if (uc11Controller.checkForRepresentativeApplications() == true) {
            this.eventsList = uc11Controller.getEventsWithApplicationsFromUser();
        } else {
            JOptionPane.showMessageDialog(UC11UI.this,
                    "No application has been made by this user to any event");

        }
        this.eventsTitlesList = uc11Controller.getTitlesOfEvents(eventsList);
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

        JLabel descriptionLabel = new JLabel();//uc05Controller.getEventByTitle(eventTitle)

        pos.gridx = 2;
        pos.gridy = 0;
        centralPanel.add(descriptionLabel, pos);

        JComboBox<String> eventList = new JComboBox<>(eventsTitlesList);
        eventList.setSelectedIndex(-1);
        eventList.addActionListener((ActionEvent ae) -> {
            if (eventList.getSelectedIndex() == -1 && eventsTitlesList.length == 0) {
                JOptionPane.showMessageDialog(UC11UI.this,
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
                uc11Controller.setEvent(event);
                window.dispose();
                window.setVisible(false);
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
        JFrame window2 = new JFrame("UC11");
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

        JLabel newTradeNameLabel = new JLabel("New Trade name:");
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(newTradeNameLabel, pos);

        JTextField newTradeNameField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 0;
        centralPanel.add(newTradeNameField, pos);

        JLabel newDescriptionLabel = new JLabel("New description:");
        pos.gridx = 0;
        pos.gridy = 1;
        centralPanel.add(newDescriptionLabel, pos);

        JTextField newDescriptionField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 1;
        centralPanel.add(newDescriptionField, pos);

        JLabel newAddressLabel = new JLabel("New address:");
        pos.gridx = 0;
        pos.gridy = 2;
        centralPanel.add(newAddressLabel, pos);

        JTextField newAddressField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 2;
        centralPanel.add(newAddressField, pos);

        JLabel newPhoneLabel = new JLabel("New Phone number:");
        pos.gridx = 0;
        pos.gridy = 3;
        centralPanel.add(newPhoneLabel, pos);

        JTextField newPhoneField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 3;
        centralPanel.add(newPhoneField, pos);

        JLabel newIntendedBoothAreaLabel = new JLabel("New Intended Booth Area: ");
        pos.gridx = 0;
        pos.gridy = 4;
        centralPanel.add(newIntendedBoothAreaLabel, pos);

        JTextField newIntendedBoothAreaField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 4;
        centralPanel.add(newIntendedBoothAreaField, pos);

        JLabel newProductsToBeDisplayedLabel = new JLabel("New Products To Be Displayed(seperated by ;): ");
        pos.gridx = 0;
        pos.gridy = 5;
        centralPanel.add(newProductsToBeDisplayedLabel, pos);

        JTextField newProductsToBeDisplayedField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 5;
        centralPanel.add(newProductsToBeDisplayedField, pos);

        JLabel newNumberOfInvitationsToPurchaseLabel = new JLabel("New Number Of Invitations To Purchase: ");
        pos.gridx = 0;
        pos.gridy = 6;
        centralPanel.add(newNumberOfInvitationsToPurchaseLabel, pos);

        JTextField newNumberOfInvitationsToPurchaseField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 6;
        centralPanel.add(newNumberOfInvitationsToPurchaseField, pos);

        JLabel keywordsLabel = new JLabel("New Keywords(At least two seperated by ; ): ");
        pos.gridx = 0;
        pos.gridy = 7;
        centralPanel.add(keywordsLabel, pos);

        JTextField newKeywordsField = new JTextField(textSize);
        pos.gridx = 1;
        pos.gridy = 7;
        centralPanel.add(newKeywordsField, pos);

        createButton.addActionListener((ActionEvent e) -> {
            String tradeName = newTradeNameField.getText();
            String description = newDescriptionField.getText();
            String address = newAddressField.getText();
            String[] keywords = newKeywordsField.getText().split(";");
            String[] productsToBeDisplayed = newProductsToBeDisplayedField.getText().split(";");
            try {
                String phoneNumber = newPhoneField.getText();
                String intendedBoothArea = newIntendedBoothAreaField.getText();
                String numberOfInvitationsToPurchase = newNumberOfInvitationsToPurchaseField.getText();
                if (tradeName != null && description != null && address != null && phoneNumber != null && intendedBoothArea != null && productsToBeDisplayed != null && numberOfInvitationsToPurchase != null && keywords.length <= 5 && keywords.length >= 2) {
                    long phone = Long.parseLong(phoneNumber);
                    double boothArea = Double.parseDouble(intendedBoothArea);
                    int invitations = Integer.parseInt(numberOfInvitationsToPurchase);
                    uc11Controller.createApplication(tradeName, description, address, phone, boothArea, productsToBeDisplayed, invitations, keywords);
                    window.dispose();
                    createFinalConfirmationGUI(menuWindow);
                } else {
                    JOptionPane.showMessageDialog(UC11UI.this,
                            "Missing data. Please check.",
                            "Event creation error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(UC11UI.this,
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
        JFrame window3 = new JFrame("UC11");
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

        String[] labels = uc11Controller.getApplicationInfo().split(";");

        JLabel tradeNameLabel = new JLabel(labels[0]);
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(tradeNameLabel, pos);

        JLabel descriptionLabel = new JLabel(labels[1]);
        pos.gridx = 1;
        pos.gridy = 0;
        centralPanel.add(descriptionLabel, pos);

        JLabel addressLabel = new JLabel(labels[2]);
        pos.gridx = 0;
        pos.gridy = 1;
        centralPanel.add(addressLabel, pos);

        JLabel phoneLabel = new JLabel(labels[3]);
        pos.gridx = 1;
        pos.gridy = 1;
        centralPanel.add(phoneLabel, pos);

        JLabel intendedBoothLabel = new JLabel(labels[4]);
        pos.gridx = 0;
        pos.gridy = 2;
        centralPanel.add(intendedBoothLabel, pos);

        JLabel productsToBeDisplayedLabel = new JLabel(labels[5]);
        pos.gridx = 1;
        pos.gridy = 2;
        centralPanel.add(productsToBeDisplayedLabel, pos);

        JLabel keywordsLabel = new JLabel(labels[6]);
        pos.gridx = 0;
        pos.gridy = 3;
        centralPanel.add(keywordsLabel, pos);

        JButton confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc11Controller.registerApplicationChanges();
                window.dispose();
                menuWindow.setVisible(true);

            }

        }
        );

        pos.gridx = 0;
        pos.gridy = 4;
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
        pos.gridy = 4;
        centralPanel.add(cancelButton, pos);

        window.add(centralPanel);
        return centralPanel;
    }
}
