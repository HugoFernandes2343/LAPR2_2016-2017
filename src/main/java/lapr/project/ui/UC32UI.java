/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lapr.project.controller.UC32Controller;
import lapr.project.model.*;

/**
 *
 * @author PC
 */
public class UC32UI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private UC32Controller uc32Controller;
    private static int windowHeight = 250;
    private static int windowWidth = 350;

    public UC32UI(FairCenter fc, JFrame menuWindow) throws FileNotFoundException {
        uc32Controller = new UC32Controller(fc);
        this.setName("UC32 - Import Event Data");
        this.createFrame(menuWindow);
    }

    private void createFrame(JFrame menuWindow) {
        setSize(windowWidth, windowHeight);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
        JPanel typeSelection = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JLabel headline = new JLabel("Select the event to recieve the data :");
        titlePanel.add(headline);

        JPanel selectionPanel = new JPanel(new BorderLayout());
        JButton continueButton = new JButton("Continue to file selection");

        List<Event> allEventsList = uc32Controller.getAllEvents();
        String[] listPresentableEvents = new String[allEventsList.size()];
        for (int i = 0; i < allEventsList.size(); i++) {
            listPresentableEvents[i] = allEventsList.get(i).toString();
        }
        JComboBox<String> eventList = new JComboBox<>(listPresentableEvents);
        eventList.setEditable(false);
        eventList.setSelectedIndex(-1);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    int i = eventList.getSelectedIndex();
                    uc32Controller.setSelectedEvent(uc32Controller.getAllEvents().get(i));
                    uc32Controller.importEventData(UC32UI.this,menuWindow);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(UC32UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        selectionPanel.add(eventList,BorderLayout.PAGE_START);//Put event comboBox
        selectionPanel.add(continueButton, BorderLayout.PAGE_END);
        typeSelection.add(titlePanel, BorderLayout.CENTER);
        this.add(typeSelection, BorderLayout.PAGE_START);
        this.add(selectionPanel, BorderLayout.CENTER);
    }
}
