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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lapr.project.controller.UC49Controller;
import lapr.project.model.FairCenter;

/**
 *
 * @author Hugo
 */
public class UC49UI extends JDialog {

    private static final long serialVersionUID = 1L;
    private final int width = 1000;
    private final int height = 600;
    private final UC49Controller uc49Controller;

    public UC49UI(FairCenter fc, JFrame menuWindow) {
        uc49Controller = new UC49Controller(fc);
        this.setName(" Test the difference between the mean deviation and a theoretical value 1 for a FAE average rating ");
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
                    UC49UI.this.dispose();
                    UC49UI.this.setVisible(false);
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
        JPanel infoScreen = new JPanel(new BorderLayout());
        infoScreen.setSize(width, height);
        createInfoScreen(infoScreen, menuWindow);
        add(infoScreen, BorderLayout.CENTER);
    }

    private void createInfoScreen(JPanel infoScreen, JFrame menuWindow) {//Format not good
        JPanel buttonBox = new JPanel(new GridBagLayout());
        String[] tableLabels = {"FAE", "Number of submissions (Ni ≥ 30)", "Significance level (1% or 5%)", "FAE Mean rating", "Deviations mean ", "Standard deviation", "Observed value of test statistic", "Decision Alert: Yes/No"};
        DefaultTableModel model = new DefaultTableModel();
        GridBagConstraints pos = new GridBagConstraints();
        JLabel textLabel = new JLabel("Please fill the proportion(max proportion available is showed) and significance(1% or 5%)");
        JTable frequencyTable = new JTable(model);
        model.setDataVector(uc49Controller.generateInitialTable(), tableLabels);
        JScrollPane scrollPane = new JScrollPane(frequencyTable);
        int[] sampleSize = new int[frequencyTable.getRowCount()];
        int[] significance = new int[frequencyTable.getRowCount()];
        frequencyTable.setFillsViewportHeight(true);
        JButton analiseButton = new JButton("Analise");
        analiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    for (int i = 0; i < frequencyTable.getRowCount(); i++) {
                        sampleSize[i] = Integer.parseInt(frequencyTable.getModel().getValueAt(i, 1).toString());
                        significance[i] = Integer.parseInt(frequencyTable.getModel().getValueAt(i, 2).toString());
                    }
                    if (uc49Controller.validateInputData(sampleSize, significance)) {
                        Object[][] fullTable = uc49Controller.generateFinalDataTable(sampleSize, significance);
                        model.setDataVector(fullTable, tableLabels);
                    } else {
                        JOptionPane.showMessageDialog(UC49UI.this,
                                "Invalid data. Please check.",
                                "Invalid data error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(UC49UI.this,
                            "Data format not supported (Number Data formats wrong or nonexistent)",
                            "Data formatting error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton finishButton = new JButton("Terminate analysis");

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC49UI.this.dispose();
                UC49UI.this.setVisible(false);
                menuWindow.setVisible(true);
            }

        });
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object[][] initialTable = uc49Controller.generateInitialTable();
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        model.setValueAt(initialTable[i][j], i, j);
                    }
                }
            }

        });

        pos.gridx = 0;
        pos.gridy = 0;
        buttonBox.add(analiseButton, pos);
        pos.gridx = 0;
        pos.gridy = 1;
        buttonBox.add(resetButton, pos);
        pos.gridx = 0;
        pos.gridy = 2;
        buttonBox.add(finishButton, pos);
        infoScreen.add(textLabel, BorderLayout.PAGE_START);
        infoScreen.add(scrollPane, BorderLayout.CENTER);
        infoScreen.add(buttonBox, BorderLayout.PAGE_END);
    }
}
