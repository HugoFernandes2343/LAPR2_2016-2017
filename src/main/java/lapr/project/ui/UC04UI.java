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
import javax.swing.JTextField;
import lapr.project.controller.UC04Controller;
import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
public class UC04UI extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 600;

    private final UC04Controller uc04Controller;
    private final User user;
    private boolean eventAlowed;
    private final String[] opcionalValues = {"0", "1", "2", "3", "4", "5"};

    public UC04UI(FairCenter fc, User u, JFrame menuWindow) {
        uc04Controller = new UC04Controller(fc, u);
        this.user = u;
        this.setName("UC04 - Decide Application");
        this.createFrame(menuWindow);
        eventAlowed = false;
    }

    private void createFrame(JFrame menuWindow) {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
        JPanel applicationSelectionScreen = new JPanel(new BorderLayout());
        JPanel evaluationScreen = new JPanel(new BorderLayout());
        createEventSelectionScreen(eventSelectionScreen, allScreens, menuWindow, applicationSelectionScreen, evaluationScreen);
        allScreens.add(eventSelectionScreen, "1");
        allScreens.add(applicationSelectionScreen, "2");
        allScreens.add(evaluationScreen, "3");
        add(allScreens, BorderLayout.CENTER);
    }

    private void createEventSelectionScreen(JPanel eventSelectionScreen, JPanel allScreens, JFrame menuWindow, JPanel applicationSelectionScreen, JPanel evaluationScreen) {
        //Selector
        JPanel eventSelectionBox = new JPanel(new BorderLayout());
        JLabel selectEvent = new JLabel("Select one event:");
        eventSelectionBox.add(selectEvent, BorderLayout.PAGE_START);

        List<Event> allEventsList = uc04Controller.getEventsByFAE();
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
                uc04Controller.setSelectedEvent(uc04Controller.getEventsByFAE().get(i));
                if (uc04Controller.validateEvent()) {
                    eventAlowed = true;
                } else {
                    eventAlowed = false;
                    JOptionPane.showMessageDialog(UC04UI.this,
                            "This event has no applications for you to review. Please chose another event.",
                            "Event Selection",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        eventSelectionBox.add(eventList, BorderLayout.CENTER);

        //Buttons
        JPanel eventConfirmationButtons = new JPanel();
        JButton confirmButton = new JButton("Confirm Selection");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (eventAlowed) {
                    applicationSelectionScreen(allScreens, applicationSelectionScreen, evaluationScreen, menuWindow);
                    CardLayout cl = (CardLayout) (allScreens.getLayout());
                    cl.show(allScreens, "2");//USE a string with a number and its solved (TAG)                 
                } else {
                    JOptionPane.showMessageDialog(UC04UI.this,
                            "Select a valid Event",
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
        eventSelectionScreen.add(eventConfirmationButtons, BorderLayout.CENTER);
        eventSelectionScreen.add(eventSelectionBox, BorderLayout.PAGE_START);
    }

    private void applicationSelectionScreen(JPanel allScreens, JPanel applicationSelectionScreen, JPanel evaluationScreen, JFrame menuWindow) {
        JPanel applicationSelectionBox = new JPanel(new BorderLayout());
        JLabel selectApplication = new JLabel("Select one application:");
        applicationSelectionBox.add(selectApplication, BorderLayout.PAGE_START);

        List<Application> allApplicationsList = uc04Controller.getFAEApplications();
        String[] listPresentableApplications = new String[allApplicationsList.size()];
        for (int i = 0; i < allApplicationsList.size(); i++) {
            listPresentableApplications[i] = allApplicationsList.get(i).toString();
        }
        JComboBox<String> applicationList = new JComboBox<>(listPresentableApplications);
        applicationList.setEditable(false);
        applicationList.setSelectedIndex(-1);

        applicationList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = applicationList.getSelectedIndex();
                uc04Controller.setSelectedApplication(uc04Controller.getFAEApplications().get(i));
            }
        });
        applicationSelectionBox.add(applicationList, BorderLayout.CENTER);

        //Buttons
        JPanel applicationConfirmationButtons = new JPanel();
        JButton confirmButton = new JButton("Confirm Selection");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (uc04Controller.getSelectedApplication() != null) {
                    applicationEvaluationScreen(evaluationScreen, menuWindow);
                    CardLayout cl = (CardLayout) (allScreens.getLayout());
                    cl.show(allScreens, "3");//USE a string with a number and its solved (TAG)             
                } else {
                    JOptionPane.showMessageDialog(UC04UI.this,
                            "Please chose an aplication.",
                            "Application Selection",
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
        applicationConfirmationButtons.add(confirmButton);
        applicationConfirmationButtons.add(cancelButton);
        applicationSelectionScreen.add(applicationConfirmationButtons, BorderLayout.CENTER);
        applicationSelectionScreen.add(applicationSelectionBox, BorderLayout.PAGE_START);
    }

    private void applicationEvaluationScreen(JPanel evaluationScreen, JFrame menuWindow) {

        JPanel evaluationBox = new JPanel(new GridBagLayout());
        GridBagConstraints pos = new GridBagConstraints();

        JLabel justificationLabel = new JLabel("Please write a small justification:");
        pos.gridx = 0;
        pos.gridy = 0;
        evaluationBox.add(justificationLabel, pos);

        JTextField justificationField = new JTextField();
        pos.gridx = 1;
        pos.gridy = 0;
        evaluationBox.add(justificationField, pos);

        JLabel faeTopicKnowledgeLabel = new JLabel("FAE Topic Knowledge:");
        pos.gridx = 0;
        pos.gridy = 1;
        evaluationBox.add(faeTopicKnowledgeLabel, pos);

        JComboBox<String> faeTopicKnowledgeList = new JComboBox<>(opcionalValues);
        faeTopicKnowledgeList.setEditable(false);
        faeTopicKnowledgeList.setSelectedIndex(-1);
        pos.gridx = 1;
        pos.gridy = 1;
        evaluationBox.add(faeTopicKnowledgeList, pos);

        JLabel eventAdequacyLabel = new JLabel("Event Adequacy:");
        pos.gridx = 0;
        pos.gridy = 2;
        evaluationBox.add(eventAdequacyLabel, pos);

        JComboBox<String> eventAdequacyList = new JComboBox<>(opcionalValues);
        faeTopicKnowledgeList.setEditable(false);
        faeTopicKnowledgeList.setSelectedIndex(-1);
        pos.gridx = 1;
        pos.gridy = 2;
        evaluationBox.add(eventAdequacyList, pos);

        JLabel inviteAdequacyLabel = new JLabel("Invite Adequacy:");
        pos.gridx = 0;
        pos.gridy = 2;
        evaluationBox.add(inviteAdequacyLabel, pos);

        JComboBox<String> inviteAdequacyList = new JComboBox<>(opcionalValues);
        faeTopicKnowledgeList.setEditable(false);
        faeTopicKnowledgeList.setSelectedIndex(-1);
        pos.gridx = 1;
        pos.gridy = 2;
        evaluationBox.add(inviteAdequacyList, pos);

        JLabel recommendationLabel = new JLabel("Recommendation:");
        pos.gridx = 0;
        pos.gridy = 3;
        evaluationBox.add(recommendationLabel, pos);

        JComboBox<String> recomendationList = new JComboBox<>(opcionalValues);
        faeTopicKnowledgeList.setEditable(false);
        faeTopicKnowledgeList.setSelectedIndex(-1);
        pos.gridx = 1;
        pos.gridy = 3;
        recomendationList.add(recomendationList, pos);

        JPanel evaluationConfirmationButtons = new JPanel();
        JButton confirmButton = new JButton("Confirm Selection");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String appEvaluation = "Application Evaluation";
                if (faeTopicKnowledgeList.getSelectedIndex() != -1
                        && eventAdequacyList.getSelectedIndex() != -1
                        && inviteAdequacyList.getSelectedIndex() != -1
                        && recomendationList.getSelectedIndex() != -1
                        && !"".equals(justificationField.getText())) {
                    Integer faeTopicKnowledge = Integer.getInteger(opcionalValues[faeTopicKnowledgeList.getSelectedIndex()]);
                    Integer eventAdequacy = Integer.getInteger(opcionalValues[eventAdequacyList.getSelectedIndex()]);
                    Integer inviteAdequacy = Integer.getInteger(opcionalValues[inviteAdequacyList.getSelectedIndex()]);
                    Integer recomendation = Integer.getInteger(opcionalValues[recomendationList.getSelectedIndex()]);
                    String justification = justificationField.getText();
                    uc04Controller.evaluateApplication(faeTopicKnowledge, eventAdequacy, inviteAdequacy, recomendation, justification);
                    int confirm = JOptionPane.showOptionDialog(
                            null, "Are You Sure ?",
                            "Selection Confirmation", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (confirm == 0) {
                        if (uc04Controller.registerEvaluation()) {
                            JOptionPane.showMessageDialog(UC04UI.this,
                                    "Application evaluation sucessfull.",
                                    appEvaluation,
                                    JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            setVisible(false);
                            menuWindow.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(UC04UI.this,
                                    "Application evaluation was not registered. Please try again later.",
                                    appEvaluation,
                                    JOptionPane.ERROR_MESSAGE);
                            dispose();
                            setVisible(false);
                            menuWindow.setVisible(true);
                        }
                    } else {
                        dispose();
                        setVisible(false);
                        menuWindow.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(UC04UI.this,
                            "Please evaluate all requested fields.",
                            appEvaluation,
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
        evaluationConfirmationButtons.add(confirmButton);
        evaluationConfirmationButtons.add(cancelButton);
        evaluationScreen.add(evaluationConfirmationButtons, BorderLayout.CENTER);
        evaluationScreen.add(evaluationBox, BorderLayout.PAGE_START);

    }
}
