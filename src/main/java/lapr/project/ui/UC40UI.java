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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import lapr.project.controller.UC40Controller;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
public class UC40UI extends JDialog {

    private static final long serialVersionUID = 1L;
    private final int width = 1000;
    private final int height = 600;
    private final UC40Controller uc40Controller;

    public UC40UI(FairCenter fc, User u, JFrame menuWindow) {
        uc40Controller = new UC40Controller(fc, u);
        this.setName("UC40 - Show Event applications keywords frequency");
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
                    UC40UI.this.dispose();
                    UC40UI.this.setVisible(false);
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

        List<Event> allEventsList = uc40Controller.getEventsByOrganizer();
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
                uc40Controller.setSelectedEvent(uc40Controller.getEventsByOrganizer().get(i));

            }
        });
        eventSelectionBox.add(eventList, BorderLayout.CENTER);

        //Buttons
        JPanel eventConfirmationButtons = new JPanel();
        JButton confirmButton = new JButton("Confirm Selection");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (uc40Controller.getSelectedEvent() != null) {
                    createInfoScreen(infoScreen, menuWindow);
                    CardLayout cl = (CardLayout) (allScreens.getLayout());
                    cl.show(allScreens, "2");//USE a string with a number and its solved (TAG)                
                } else {
                    JOptionPane.showMessageDialog(UC40UI.this,
                            "Select one Event",
                            "Event Selection",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC40UI.this.dispose();
                UC40UI.this.setVisible(false);
                menuWindow.setVisible(true);
            }
        });
        eventConfirmationButtons.add(confirmButton);
        eventConfirmationButtons.add(cancelButton);
        screen.add(eventConfirmationButtons, BorderLayout.CENTER);
        screen.add(eventSelectionBox, BorderLayout.PAGE_START);
    }

    private void createInfoScreen(JPanel infoScreen, JFrame menuWindow) {//Format not good
        Object[] tableLabels = {"Keywords", "Frequency(%)"};
        GridBagConstraints pos = new GridBagConstraints();

        JLabel textLabel = new JLabel("Information regarding this events Keywords");
        Object[][] data = uc40Controller.getEventKeywordFrequencyTable();
        JTable frequencyTable = new JTable(data, tableLabels);
        JScrollPane scrollPane = new JScrollPane(frequencyTable);
        frequencyTable.setFillsViewportHeight(true);
        frequencyTable.setEnabled(false);
        JButton finishButton = new JButton("Terminate analysis");

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC40UI.this.dispose();
                UC40UI.this.setVisible(false);
                menuWindow.setVisible(true);
            }

        });
        pos.gridx = 0;
        pos.gridy = 0;
        infoScreen.add(textLabel, pos);
        pos.gridx = 0;
        pos.gridy = 1;
        infoScreen.add(scrollPane, pos);
        pos.gridx = 0;
        pos.gridy = 2;
        infoScreen.add(finishButton, pos);
    }
}
