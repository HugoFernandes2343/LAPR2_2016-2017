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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lapr.project.controller.UC47Controller;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
public class UC47UI extends JDialog {

    private static final long serialVersionUID = 1L;
    private final int width = 1000;
    private final int height = 600;
    private final UC47Controller uc47Controller;

    public UC47UI(FairCenter fc, User u, JFrame menuWindow) {
        uc47Controller = new UC47Controller(fc);
        this.setName("UC47 - Show submission global mean rate.");
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
                    UC47UI.this.dispose();
                    UC47UI.this.setVisible(false);
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
        JPanel infoScreen = new JPanel(new GridBagLayout());
        createInfoScreen(infoScreen, menuWindow);
        allScreens.add(infoScreen, "1");
        add(allScreens, BorderLayout.CENTER);
    }

    private void createInfoScreen(JPanel infoScreen, JFrame menuWindow) {//Format not good
        GridBagConstraints pos = new GridBagConstraints();
        JLabel textLabel = new JLabel("Information regarding the global submission mean rate");
        JLabel meanRateLabel = new JLabel("Global submission mean rate: " + uc47Controller.getSubmissionGlobalMeanRate());
        JButton finishButton = new JButton("Terminate analysis");

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC47UI.this.dispose();
                UC47UI.this.setVisible(false);
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
        infoScreen.add(finishButton, pos);
    }
}
