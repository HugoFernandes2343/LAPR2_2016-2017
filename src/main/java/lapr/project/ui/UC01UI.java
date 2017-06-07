/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.controller.UC01Controller;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
public class UC01UI extends JFrame {

    private final JButton createButton = new JButton("Create");
    private final JButton cancelButton = new JButton("Cancel");
    private String eventType;

    private UC01Controller controller;

    public UC01UI(FairCenter fc, User user) {
        CreateEventWindow Window = new CreateEventWindow();

        controller = new UC01Controller(fc, user);
    }

    class CreateEventWindow extends JFrame {

        private int HEIGHT = 600;
        private int WIDTH = 250;

        private CreateEventWindow() {
            JFrame CreateEventFrame = new JFrame("UC01");
            CreateEventFrame.setSize(HEIGHT, WIDTH);
            BorderLayout layout = new BorderLayout();
            CreateEventFrame.setLayout(layout);
            CreateEventFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            createElements(CreateEventFrame);
            CreateEventFrame.setResizable(true);
            CreateEventFrame.setLocationRelativeTo(null);
            CreateEventFrame.setVisible(true);

        }

    }

    private void createElements(JFrame createEventFrame) {

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
                eventType = "Exposition";
            }

        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        createEventFrame.dispose();
                        createAddOrganiserGUI();
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(UC01UI.this,
                                "Error detected in the dates. Please check.",
                                "Event creation error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(UC01UI.this,
                            "Missing data. Please check.",
                            "Event creation error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        }
        );
        pos.gridx = 1;
        pos.gridy = 4;

        centralPanel.add(createButton, pos);

        cancelButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                createEventFrame.dispose();
            }
        }
        );
        pos.gridx = 2;
        pos.gridy = 4;

        centralPanel.add(cancelButton, pos);

        createEventFrame.add(centralPanel);
    }

    private void createAddOrganiserGUI() {

        JFrame addOrganisersFrame = new JFrame("UC01");
        addOrganisersFrame.setSize(HEIGHT, WIDTH);
        BorderLayout layout = new BorderLayout();
        addOrganisersFrame.setLayout(layout);
        addOrganisersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addOrganisersFrame.add(new ADDorganisers(addOrganisersFrame, controller));
        addOrganisersFrame.pack();
        addOrganisersFrame.setResizable(true);
        addOrganisersFrame.setLocationRelativeTo(null);
        addOrganisersFrame.setVisible(true);

    }

    private void createFinalConfirmationGUI() {

        JFrame finalConfirmationFrame = new JFrame("UC01");
        finalConfirmationFrame.setSize(HEIGHT, WIDTH);
        BorderLayout layout = new BorderLayout();
        finalConfirmationFrame.setLayout(layout);
        finalConfirmationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finalConfirmationFrame.add(createFinalConfirmationComponents(finalConfirmationFrame));
        finalConfirmationFrame.pack();
        finalConfirmationFrame.setResizable(true);
        finalConfirmationFrame.setLocationRelativeTo(null);
        finalConfirmationFrame.setVisible(true);

    }

    private JPanel createFinalConfirmationComponents(JFrame finalConfirmationFrame) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints pos = new GridBagConstraints();
        JLabel eventInfoLabel = new JLabel(controller.getEventInfo());
        pos.gridx = 0;
        pos.gridy = 0;
        panel.add(eventInfoLabel, pos);
        JLabel organiserInfoLabel = new JLabel(controller.getOrganizerInfo());
        pos.gridx = 1;
        pos.gridy = 0;
        panel.add(organiserInfoLabel, pos);
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (controller.registerEvent()) {
                    JOptionPane.showMessageDialog(UC01UI.this,
                            "Operation successfull.",
                            "Event creation status",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(UC01UI.this,
                            "Operation unsuccessfull.",
                            "Event creation status",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
        pos.gridx = 0;
        pos.gridy = 1;
        panel.add(confirmButton, pos);
        JButton cancelButton = new JButton("Cancel");
        confirmButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                finalConfirmationFrame.dispose();
            }
        }
        );
        pos.gridx = 1;
        pos.gridy = 1;
        panel.add(cancelButton, pos);
        return panel;
    }

    private class ADDorganisers extends JPanel implements ListSelectionListener {

        private JList list;
        private DefaultListModel listModel;
        private ArrayList<User> users;
        private JButton addButton;

        private int counter;

        public ADDorganisers(JFrame addOrganisersFrame, UC01Controller controller) {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints pos = new GridBagConstraints();
            counter = 0;
            listModel = new DefaultListModel();
            users = controller.getUsers();
            for (int i = 0; i < users.size(); i++) {
                listModel.addElement(users.get(i).toString());
            }

            list = new JList(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);
            list.addListSelectionListener(this);
            list.setVisibleRowCount(5);
            JScrollPane listScrollPane = new JScrollPane(list);
            pos.gridx = 0;
            pos.gridy = 0;
            add(listScrollPane, pos);
            JLabel nOrganisers = new JLabel("Number of Organisers: " + counter);
            pos.gridx = 3;
            pos.gridy = 1;
            panel.add(nOrganisers, pos);
            JButton finishButton = new JButton("Finish");
            finishButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (counter >= 2) {
                        addOrganisersFrame.dispose();
                        createFinalConfirmationGUI();
                    } else {
                        JOptionPane.showMessageDialog(UC01UI.this,
                                "Need more organizers.",
                                "Organizers error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            pos.gridx = 1;
            pos.gridy = 1;
            add(finishButton, pos);
            addButton = new JButton("ADD");
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int index = list.getSelectedIndex();
                    listModel.remove(index);
                    System.out.println(index);
                    controller.newOrganizer(users.get(index));
                    counter++;
                    nOrganisers.setText("Number of Organisers: " + counter);
                    int size = listModel.getSize();

                    if (size == 0) { //Nobody's left, disable adding organisers.
                        addButton.setEnabled(false);

                    } else { //Select an index.
                        if (index == listModel.getSize()) {
                            //removed item in last position
                            index--;
                        }

                        list.setSelectedIndex(index);
                        list.ensureIndexIsVisible(index);
                    }
                }
            });
            pos.gridx = 0;
            pos.gridy = 1;
            add(addButton, pos);
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e
                ) {
                    addOrganisersFrame.dispose();
                }
            }
            );

            pos.gridx = 2;
            pos.gridy = 1;
            add(cancelButton, pos);

        }

        //This method is required by ListSelectionListener.
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {

                if (list.getSelectedIndex() == -1) {
                    //No selection, disable fire button.
                    addButton.setEnabled(false);

                } else {
                    //Selection, enable the fire button.
                    addButton.setEnabled(true);
                }
            }
        }

    }

}
