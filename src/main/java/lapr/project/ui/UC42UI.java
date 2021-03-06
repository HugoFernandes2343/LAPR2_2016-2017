/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lapr.project.controller.UC42Controller;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
public class UC42UI extends JDialog {

    private static final long serialVersionUID = 1L;
    private final int width = 1000;
    private final int height = 600;
    private final UC42Controller uc42Controller;

    public UC42UI(FairCenter fc, User u, JFrame menuWindow) {
        uc42Controller = new UC42Controller(fc);
        this.setName("UC42 - Show Event Acceptance Rate");
        this.createFrame(menuWindow);
    }

    private void createFrame(JFrame menuWindow) {
        setSize(width, height);
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
                    UC42UI.this.dispose();
                    UC42UI.this.setVisible(false);
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
        JPanel infoScreen = new JPanel(new GridBagLayout());
        createEventSelectionScreen(eventSelectionScreen, allScreens, menuWindow, infoScreen);
        allScreens.add(eventSelectionScreen, "1");
        allScreens.add(infoScreen, "2");
        add(allScreens, BorderLayout.CENTER);
    }

    private void createEventSelectionScreen(JPanel screen, JPanel allScreens, JFrame menuWindow, JPanel infoScreen) {
        //Selector
        JPanel eventSelectionBox = new JPanel(new BorderLayout());
        JLabel selectEvent = new JLabel("Select one event:");
        eventSelectionBox.add(selectEvent, BorderLayout.PAGE_START);

        List<Event> allEventsList = uc42Controller.getEvents();
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
                uc42Controller.setSelectedEvent(uc42Controller.getEvents().get(i));
            }
        });
        eventSelectionBox.add(eventList, BorderLayout.CENTER);

        //Buttons
        JPanel eventConfirmationButtons = new JPanel();
        JButton confirmButton = new JButton("Confirm Selection");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (uc42Controller.getSelectedEvent() != null) {
                    createInfoScreen(infoScreen, menuWindow);
                    CardLayout cl = (CardLayout) (allScreens.getLayout());
                    cl.show(allScreens, "2");//USE a string with a number and its solved (TAG)                
                } else {
                    JOptionPane.showMessageDialog(UC42UI.this,
                            "Select one Event",
                            "Event Selection",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC42UI.this.dispose();
                UC42UI.this.setVisible(false);
                menuWindow.setVisible(true);
            }
        });
        eventConfirmationButtons.add(confirmButton);
        eventConfirmationButtons.add(cancelButton);
        screen.add(eventConfirmationButtons, BorderLayout.CENTER);
        screen.add(eventSelectionBox, BorderLayout.PAGE_START);
    }

    private void createInfoScreen(JPanel infoScreen, JFrame menuWindow) {//Format not good
        GridBagConstraints pos = new GridBagConstraints();
        JLabel textLabel = new JLabel("Information regarding this events acceptance rate");
        double acceptanceRate = uc42Controller.getEventAcceptanceRate();
        JLabel infoLabel = new JLabel("The acceptance rate of this Event is: " + acceptanceRate + "%");
        JButton finishButton = new JButton("Terminate analysis");
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC42UI.this.dispose();
                UC42UI.this.setVisible(false);
                menuWindow.setVisible(true);
            }

        });
        pos.gridx = 0;
        pos.gridy = 0;
        infoScreen.add(textLabel, pos);
        pos.gridx = 0;
        pos.gridy = 1;
        infoScreen.add(infoLabel, pos);
        pos.gridx = 0;
        pos.gridy = 2;
        infoScreen.add(finishButton, pos);
    }
}
