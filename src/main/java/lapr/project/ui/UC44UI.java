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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import lapr.project.controller.UC44Controller;
import lapr.project.model.FairCenter;

/**
 *
 * @author Hugo
 */
public class UC44UI extends JDialog {

    private static final long serialVersionUID = 1L;
    private final int width = 1000;
    private final int height = 600;
    private final UC44Controller uc44Controller;

    public UC44UI(FairCenter fc, JFrame menuWindow) {
        uc44Controller = new UC44Controller(fc);
        this.setName("UC45 - Test the difference between two Events acceptance rate.");
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
                    UC44UI.this.dispose();
                    UC44UI.this.setVisible(false);
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
        infoScreen.setSize(width, height);
        createInfoScreen(infoScreen, menuWindow);
        allScreens.add(infoScreen, "1");
        add(allScreens, BorderLayout.CENTER);
    }

    private void createInfoScreen(JPanel infoScreen, JFrame menuWindow) {//Format not good
        String[] tableLabels = {"Event", "Proportion of submissions accepted in ni (ni ≥30)", "Critical value (zC)", "Significance level (1% or 5%)", "Observed value of test statistic (Zobs)", "Decision Over 50%: Yes/No"};
        DefaultTableModel model = new DefaultTableModel();
        GridBagConstraints pos = new GridBagConstraints();
        JLabel textLabel = new JLabel("Please fill the proportion(max proportion available is showed) and significance(1% or 5%)");
        JTable frequencyTable = new JTable(model);
        model.setDataVector(uc44Controller.generateInitialTable(), tableLabels);
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
                        significance[i] = Integer.parseInt(frequencyTable.getModel().getValueAt(i, 3).toString());
                    }
                    if (uc44Controller.validateInputData(sampleSize, significance)) {
                        Object[][] fullTable = uc44Controller.generateFinalDataTable(sampleSize, significance);
                        model.setDataVector(fullTable, tableLabels);
                    } else {
                        JOptionPane.showMessageDialog(UC44UI.this,
                                "Invalid data. Please check.",
                                "Invalid data error",
                                JOptionPane.ERROR_MESSAGE);
                        model.setDataVector(uc44Controller.generateInitialTable(), tableLabels);
                    }
                } catch (NumberFormatException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(UC44UI.this,
                            "Data format not supported (Number Data formats wrong)",
                            "Data formatting error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton finishButton = new JButton("Terminate analysis");

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC44UI.this.dispose();
                UC44UI.this.setVisible(false);
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
        infoScreen.add(analiseButton, pos);
        pos.gridx = 0;
        pos.gridy = 3;
        infoScreen.add(finishButton, pos);
    }
}
