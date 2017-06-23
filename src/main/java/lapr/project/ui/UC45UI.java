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
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import lapr.project.controller.UC45Controller;
import lapr.project.model.FairCenter;

/**
 *
 * @author Hugo
 */
public class UC45UI extends JDialog {

    private static final long serialVersionUID = 1L;
    private final int width = 1000;
    private final int height = 600;
    private final UC45Controller uc45Controller;

    public UC45UI(FairCenter fc, JFrame menuWindow) {
        uc45Controller = new UC45Controller(fc);
        this.setName("UC44 - Test if event acceptance rate is over 50%");
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
                    UC45UI.this.dispose();
                    UC45UI.this.setVisible(false);
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
        add(infoScreen);
    }

    private void createInfoScreen(JPanel infoScreen, JFrame menuWindow) {//Format not good
        JPanel buttonBox = new JPanel(new GridBagLayout());
        String[] tableLabels = {"Event Ei", "Event Ej", "Proportion of submissions accepted in ni (ni≥30)", "Proportion of submissions accepted in nj (nj≥30)", "Significance level (1% or 5%)", "Critical value (zc)", "Observed value of test statistic (zobs)", "There are differences: Yes/No"};
        DefaultTableModel model = new DefaultTableModel();
        GridBagConstraints pos = new GridBagConstraints();
        JLabel textLabel = new JLabel("Please choose the event and fill the proportion(max proportion available is showed for each one and significance(1% or 5%)");
        JTable frequencyTable = new JTable(model);
        model.setDataVector(uc45Controller.generateInitialTable(), tableLabels);
        TableColumn event1Column = frequencyTable.getColumnModel().getColumn(0);
        TableColumn event2Column = frequencyTable.getColumnModel().getColumn(1);
        String[] validEvents = uc45Controller.getValidEvents();
        JComboBox<String> event1 = new JComboBox<>(validEvents);
        event1.setEditable(false);
        event1Column.setCellEditor(new DefaultCellEditor(event1));
        JComboBox<String> event2 = new JComboBox<>(validEvents);
        event2Column.setCellEditor(new DefaultCellEditor(event2));
        event2.setEditable(false);
        event2.setSelectedIndex(-1);
        JScrollPane scrollPane = new JScrollPane(frequencyTable);
        frequencyTable.setFillsViewportHeight(true);
        JButton analiseButton = new JButton("Analise");
        analiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    int significance = Integer.parseInt(frequencyTable.getValueAt(0, 4).toString());
                    int sample1 = Integer.parseInt(frequencyTable.getValueAt(0, 2).toString());
                    int sample2 = Integer.parseInt(frequencyTable.getValueAt(0, 3).toString());

                    if (uc45Controller.validateInputData(sample1, sample2, significance)) {
                        Object[][] fullTable = uc45Controller.generateFinalDataTable(sample1, sample2, significance);
                        model.setValueAt(fullTable[0][2], 0, 2);
                        model.setValueAt(fullTable[0][3], 0, 3);
                        model.setValueAt(fullTable[0][4], 0, 4);
                        model.setValueAt(fullTable[0][5], 0, 5);
                        model.setValueAt(fullTable[0][6], 0, 6);
                        model.setValueAt(fullTable[0][7], 0, 7);

                    } else {
                        JOptionPane.showMessageDialog(UC45UI.this,
                                "Invalid data. Please try.",
                                "Invalid data error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(UC45UI.this,
                            "Data format not supported (Number Data formats wrong or nonexistent)",
                            "Data formatting error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton getHintButton = new JButton("Data Hint");
        getHintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selection1 = event1.getSelectedIndex();
                int selection2 = event2.getSelectedIndex();
                if (selection1 > -1 && selection2 > -1) {
                    uc45Controller.setEvent1(selection1);
                    frequencyTable.setValueAt(uc45Controller.getEvent1MaxSample(), 0, 2);
                    uc45Controller.setEvent2(selection2);
                    frequencyTable.setValueAt(uc45Controller.getEvent2MaxSample(), 0, 3);
                        model.setValueAt(null, 0, 4);
                        model.setValueAt(null, 0, 5);
                        model.setValueAt(null, 0, 6);
                        model.setValueAt(null, 0, 7);
                }
            }

        });
        JButton finishButton = new JButton("Terminate analysis");

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UC45UI.this.dispose();
                UC45UI.this.setVisible(false);
                menuWindow.setVisible(true);
            }

        });
        pos.gridx = 0;
        pos.gridy = 0;
        buttonBox.add(getHintButton, pos);
        pos.gridx = 0;
        pos.gridy = 1;
        buttonBox.add(analiseButton, pos);
        pos.gridx = 0;
        pos.gridy = 2;
        buttonBox.add(finishButton, pos);
        infoScreen.add(textLabel, BorderLayout.PAGE_START);
        infoScreen.add(scrollPane, BorderLayout.CENTER);
        infoScreen.add(buttonBox, BorderLayout.PAGE_END);
    }
}
