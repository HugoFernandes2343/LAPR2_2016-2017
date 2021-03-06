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
import lapr.project.controller.UC48Controller;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
public class UC48UI extends JDialog {

    private static final long serialVersionUID = 1L;
    private final int width = 1000;
    private final int height = 600;
    private final UC48Controller uc48Controller;

    public UC48UI(FairCenter fc, User u, JFrame menuWindow) {
        uc48Controller = new UC48Controller(fc);
        this.setName("UC48 - Show the mean deviation between FAEs’ average ratings and a global mean rating.");
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
                    UC48UI.this.dispose();
                    UC48UI.this.setVisible(false);
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
        JPanel faeSelectionScreen = new JPanel(new BorderLayout());
        JPanel infoScreen = new JPanel(new GridBagLayout());
        createFaeSelectionScreen(faeSelectionScreen, allScreens, menuWindow, infoScreen);
        allScreens.add(faeSelectionScreen, "1");
        allScreens.add(infoScreen, "2");
        add(allScreens, BorderLayout.CENTER);
    }

    private void createFaeSelectionScreen(JPanel screen, JPanel allScreens, JFrame menuWindow, JPanel infoScreen) {
        //Selector
        JPanel eventSelectionBox = new JPanel(new BorderLayout());
        JLabel selectEvent = new JLabel("Select one FAE:");
        eventSelectionBox.add(selectEvent, BorderLayout.PAGE_START);

        List<User> allFaeList = uc48Controller.getAllFae();
        String[] listPresentableEvents = new String[allFaeList.size()];
        for (int i = 0; i < allFaeList.size(); i++) {
            listPresentableEvents[i] = allFaeList.get(i).toString();
        }
        JComboBox<String> eventList = new JComboBox<>(listPresentableEvents);
        eventList.setEditable(false);
        eventList.setSelectedIndex(-1);

        eventList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = eventList.getSelectedIndex();
                uc48Controller.setSelectedFae(uc48Controller.getAllFae().get(i));
            }
        });
        eventSelectionBox.add(eventList, BorderLayout.CENTER);

        //Buttons
        JPanel eventConfirmationButtons = new JPanel();
        JButton confirmButton = new JButton("Confirm Selection");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (uc48Controller.getSelectedFae() != null) {
                    createInfoScreen(infoScreen, menuWindow);
                    CardLayout cl = (CardLayout) (allScreens.getLayout());
                    cl.show(allScreens, "2");//USE a string with a number and its solved (TAG)                
                } else {
                    JOptionPane.showMessageDialog(UC48UI.this,
                            "Select one FAE",
                            "FAE Selection",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC48UI.this.dispose();
                UC48UI.this.setVisible(false);
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
        JLabel textLabel = new JLabel("Information regarding the mean deviation between FAEs’ average ratings and a global mean rating");
        JLabel meanRateLabel = new JLabel("Mean: " + uc48Controller.getFaeDeviationMeanRate());
        JLabel standardDeviationLabel = new JLabel("Standard deviation: " + uc48Controller.getFaeStandardDeviation());
        JButton finishButton = new JButton("Terminate analysis");

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC48UI.this.dispose();
                UC48UI.this.setVisible(false);
                menuWindow.setVisible(true);
            }

        });
        pos.gridx = 0;
        pos.gridy = 0;
        infoScreen.add(textLabel, pos);
        pos.gridx = 0;
        pos.gridy = 1;
        infoScreen.add(meanRateLabel, pos);
        pos.gridx = 0;
        pos.gridy = 2;
        infoScreen.add(standardDeviationLabel, pos);
        pos.gridx = 0;
        pos.gridy = 3;
        infoScreen.add(finishButton, pos);
    }
}
