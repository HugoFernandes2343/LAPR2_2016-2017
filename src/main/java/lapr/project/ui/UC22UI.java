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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import lapr.project.controller.UC22Controller;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author LAPR2-G054
 */
public class UC22UI extends JFrame {

    private static final long serialVersionUID = 1L;

    private final UC22Controller uc22Controller;
    private List<Event> eventsList = new ArrayList<>();
    private String[] eventsTitlesList;
    private String eventTitle;
    private String description;
    private Event event;

    private final JButton finishButton = new JButton("Terminate analysis");
    private final JButton selectButton = new JButton("Select");
    private final JButton cancelButton = new JButton("Cancel");
    private final int windowWidth = 525;
    private final int windowHeight = 250;
    private static final String confirmationDialog = "Are You Sure You Want To Exit?";
    private static final String confirmationTitle = "Exit Confirmation";

    /**
     *
     * @param fc fair center that contains all data
     * @param u user that is usuing the funcionality
     * @param menuWindow JFrame of the main menu window
     */
    public UC22UI(FairCenter fc, User u, JFrame menuWindow) {
        this.uc22Controller = new UC22Controller(fc, u);
        this.setName("UC22");
        this.eventsList = uc22Controller.getAllEvents();
        this.eventsTitlesList = uc22Controller.getEventTitles(eventsList);
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
        createChooseStateElements(this, menuWindow);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        return this;
    }

    private void createChooseStateElements(JFrame window, JFrame menuWindow) {
        JPanel centralPanel = new JPanel(new GridBagLayout());
        int sideSize = 200;
        centralPanel.setSize(sideSize, sideSize);
        GridBagConstraints pos = new GridBagConstraints();

        JLabel eventLabel = new JLabel("Event:");
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(eventLabel, pos);

        JLabel descriptionLabel = new JLabel();//uc05Controller.getEventByTitle(eventTitle)
        pos.gridx = 1;
        pos.gridy = 0;
        centralPanel.add(descriptionLabel, pos);

        JComboBox<String> eventList = new JComboBox<>(eventsTitlesList);
        eventList.setSelectedIndex(-1);
        eventList.addActionListener((ActionEvent ae) -> {
            if (eventList.getSelectedIndex() == -1 && eventsTitlesList.length == 0) {
                JOptionPane.showMessageDialog(UC22UI.this,
                        "No state has been chosen please choose a sate");
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
        }
        );
        pos.gridx = 0;
        pos.gridy = 1;
        centralPanel.add(eventList, pos);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc22Controller.setEvent(event);
                window.dispose();
                createFrameShowApplications(menuWindow);
            }
        });
        pos.gridx = 0;
        pos.gridy = 2;
        centralPanel.add(selectButton, pos);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                window.dispose();
                menuWindow.setVisible(true);
            }
        });
        pos.gridx = 1;
        pos.gridy = 2;

        centralPanel.add(cancelButton, pos);

        window.add(centralPanel);
    }

    private void createFrameShowApplications(JFrame menuWindow) {
        JPanel centralPanel = new JPanel(new GridBagLayout());
        Object[] labels = {"Trade Name", "Address"};
        GridBagConstraints pos = new GridBagConstraints();

        JLabel infoLabel = new JLabel("Applications of the chosen event");
        Object[][] data = uc22Controller.getApplicationInfoTable();
        JTable infoTable = new JTable(data, labels);
        JScrollPane scroll = new JScrollPane(infoTable);
        infoTable.setFillsViewportHeight(true);
        infoTable.setEnabled(false);

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC22UI.this.dispose();
                UC22UI.this.setVisible(false);
                menuWindow.setVisible(true);
            }

        });
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(infoLabel,pos);
        pos.gridx = 0;
        pos.gridy = 1;
        centralPanel.add(scroll,pos);
        pos.gridx = 0;
        pos.gridy = 2;
        centralPanel.add(finishButton,pos);
    }

}
