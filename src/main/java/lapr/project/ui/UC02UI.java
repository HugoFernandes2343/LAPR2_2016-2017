/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.controller.UC02Controller;
import lapr.project.model.Event;

/**
 *
 * @author PC
 */
@SuppressWarnings("serial")
public class UC02UI extends JDialog {

    private final int WIDTH = 1000;
    private final int HEIGHT = 600;

    private UC02Controller uc02Controller;

    public UC02UI(FairCenter fc, User u, JFrame menuWindow) {
        uc02Controller = new UC02Controller(fc, u);
        this.setName("UC02 - Define FAE");
        this.createFrame(menuWindow);
    }

    private void createFrame(JFrame menuWindow) {
        setSize(WIDTH, HEIGHT);
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
        JPanel eventSelectionScreen = new JPanel(new BorderLayout());
        JPanel FAESelectionScreen = new JPanel(new BorderLayout());
        createEventSelectionScreen(eventSelectionScreen, allScreens, menuWindow, FAESelectionScreen);
        allScreens.add(eventSelectionScreen, "1");
        allScreens.add(FAESelectionScreen, "2");
        add(allScreens, BorderLayout.CENTER);
    }

    private void createEventSelectionScreen(JPanel screen, JPanel allScreens, JFrame menuWindow, JPanel FAESelectionScreen) {
        //Selector
        JPanel eventSelectionBox = new JPanel(new BorderLayout());
        JLabel selectEvent = new JLabel("Select one event:");
        eventSelectionBox.add(selectEvent, BorderLayout.PAGE_START);

        ArrayList<Event> allEventsList = uc02Controller.getEventsByOrganizer();
        String[] listPresentableEvents = new String[allEventsList.size()];
        for (int i = 0; i < allEventsList.size(); i++) {
            listPresentableEvents[i] = allEventsList.get(i).toString();
        }
        JComboBox<String> eventList = new JComboBox<>(listPresentableEvents);
        eventList.setEditable(false);
        eventList.setSelectedIndex(-1);

        eventList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = eventList.getSelectedIndex();
                uc02Controller.selectedEvent = uc02Controller.getEventsByOrganizer().get(i);
                createFAESelectionScreen(FAESelectionScreen, menuWindow);
            }
        });
        eventSelectionBox.add(eventList, BorderLayout.CENTER);

        //Buttons
        JPanel eventConfirmationButtons = new JPanel();
        JButton confirmButton = new JButton("Confirm Selection");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (uc02Controller.selectedEvent != null) {
                    CardLayout cl = (CardLayout) (allScreens.getLayout());
                    cl.show(allScreens, "2");//USE a string with a number and its solved (TAG)                
                } else {
                    JOptionPane.showMessageDialog(UC02UI.this,
                            "Select one Event",
                            "Event Selection",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                setVisible(false);
                menuWindow.setVisible(true);
            }
        });
        eventConfirmationButtons.add(confirmButton);
        eventConfirmationButtons.add(cancelButton);
        screen.add(eventConfirmationButtons, BorderLayout.CENTER);
        screen.add(eventSelectionBox, BorderLayout.PAGE_START);
    }

    private void createFAESelectionScreen(JPanel FAESelectionScreen, JFrame menuWindow) {//Format not good
        JPanel FAESelectionBox = new JPanel(new BorderLayout());
        FAESelectionBox.add(new JLabel("Select the desired FAEs (allows several):"), BorderLayout.PAGE_START);

        ArrayList<User> allUsers = uc02Controller.getUsersList();
        String[] listPresentableFAE = new String[allUsers.size()];
        for (int i = 0; i < allUsers.size(); i++) {
            listPresentableFAE[i] = allUsers.get(i).toInfoString();
        }
        JList<String> FAEList = new JList<>(listPresentableFAE);
        FAEList.setSize(new Dimension(100, 150));
        JScrollPane listScroller = new JScrollPane(FAEList);
        listScroller.setPreferredSize(new Dimension(100, 150));

        JPanel FAESelectionButtonPanel = new JPanel();
        JButton selectFAEButton = new JButton("Add Selection(s)");
        JButton cancelFAESelection = new JButton("Cancel");

        selectFAEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] selection = FAEList.getSelectedIndices();
                for (int i : selection) {
                    uc02Controller.selectedEvent.createFAE(allUsers.get(i));
                }
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure ?",
                        "Selection Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    uc02Controller.selectedEvent.registerFAEs();
                    dispose();
                    setVisible(false);
                    menuWindow.setVisible(true);
                } else {
                    uc02Controller.selectedEvent.discardFAEs();
                    dispose();
                    setVisible(false);
                    menuWindow.setVisible(true);
                }
            }
        });

        cancelFAESelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                uc02Controller.selectedEvent.discardFAEs();
                dispose();
                setVisible(false);
                menuWindow.setVisible(true);
            }

        });

        FAESelectionButtonPanel.add(selectFAEButton);
        FAESelectionButtonPanel.add(cancelFAESelection);
        FAESelectionBox.add(FAEList, BorderLayout.CENTER);
        FAESelectionScreen.add(FAESelectionButtonPanel, BorderLayout.PAGE_END);
        FAESelectionScreen.add(FAESelectionBox, BorderLayout.CENTER);
    }
}
