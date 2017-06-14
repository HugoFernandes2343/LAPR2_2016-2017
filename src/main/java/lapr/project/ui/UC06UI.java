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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import lapr.project.controller.UC06Controller;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author Hugo
 */
@SuppressWarnings("serial")
public class UC06UI extends JFrame {

    private FairCenter fc;
    private User user;
    private String language;
    private String timeZone;
    private JFrame menuWindow;
    private final JButton createButton = new JButton("Create");
    private final JButton cancelButton = new JButton("Cancel");
    private final String[] languageList = {"CHOOSE", "English", "Portuguese", "Spanish", "Chinese", "Italian", "Russian"};
    private final String[] timezoneList = {"GMT-05", "GMT-04", "GMT-03", "GMT-02", "GMT-01", "CHOOSE", "GMT+00", "GMT+01", "GMT+02", "GMT+03", "GMT+04", "GMT+05"};
    private static final char[] UPPER_CASES = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] LOWER_CASES = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] NUMBER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char[] PUNCTUATION_MARK = {',', '.', ';', ':', '-'};
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    private UC06Controller controller;

    public UC06UI(FairCenter fc, User user, JFrame menuWindow) {
        this.fc = fc;
        this.user = user;
        this.menuWindow = menuWindow;
        controller = new UC06Controller(fc);
        this.setName(language);
        createFrame();
        pack();
    }

    private void createFrame() {
        setSize(HEIGHT, WIDTH);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createElements(menuWindow);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createElements(JFrame menuWindow) {

        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setSize(400, 400);
        GridBagConstraints pos = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Name:");
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(nameLabel, pos);

        JTextField nameField = new JTextField(20);
        pos.gridx = 1;
        pos.gridy = 0;
        centralPanel.add(nameField, pos);

        JLabel usernameLabel = new JLabel("Username:");
        pos.gridx = 0;
        pos.gridy = 1;
        centralPanel.add(usernameLabel, pos);

        JTextField usernameField = new JTextField(20);
        pos.gridx = 1;
        pos.gridy = 1;
        centralPanel.add(usernameField, pos);

        JLabel emailLabel = new JLabel("Email:");
        pos.gridx = 0;
        pos.gridy = 2;
        centralPanel.add(emailLabel, pos);

        JTextField emailField = new JTextField(20);
        pos.gridx = 1;
        pos.gridy = 2;
        centralPanel.add(emailField, pos);

        JLabel passwordLabel = new JLabel("Password(Must have: number,upper and lower case and (“,”,	“.”, “;”, “:” or “-“))): ");
        pos.gridx = 0;
        pos.gridy = 3;
        centralPanel.add(passwordLabel, pos);

        JPasswordField passwordField = new JPasswordField(20);
        pos.gridx = 1;
        pos.gridy = 3;
        centralPanel.add(passwordField, pos);

        JLabel confirmPasswordLabel = new JLabel("Confirm password:");
        pos.gridx = 0;
        pos.gridy = 4;
        centralPanel.add(confirmPasswordLabel, pos);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        pos.gridx = 1;
        pos.gridy = 4;
        centralPanel.add(confirmPasswordField, pos);

        JLabel languageLabel = new JLabel("Language:");
        pos.gridx = 0;
        pos.gridy = 5;
        centralPanel.add(languageLabel, pos);

        JComboBox<String> languagesList = new JComboBox<>(languageList);
        languagesList.setSelectedIndex(0);
        languagesList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                language = (String) languagesList.getSelectedItem();
            }
        });
        pos.gridx = 1;
        pos.gridy = 5;
        centralPanel.add(languagesList, pos);

        JLabel timezoneLabel = new JLabel("Timezone:");
        pos.gridx = 0;
        pos.gridy = 6;
        centralPanel.add(timezoneLabel, pos);

        JComboBox<String> timezonesList = new JComboBox<>(timezoneList);
        timezonesList.setSelectedIndex(5);
        timezonesList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timeZone = (String) timezonesList.getSelectedItem();
            }
        });
        pos.gridx = 1;
        pos.gridy = 6;
        centralPanel.add(timezonesList, pos);

        JLabel keywordLabel = new JLabel("Keyword(between 4 and 7 letters): ");
        pos.gridx = 0;
        pos.gridy = 7;
        centralPanel.add(keywordLabel, pos);

        JTextField keywordField = new JTextField(5);
        pos.gridx = 1;
        pos.gridy = 7;
        keywordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (keywordField.getText().length() >= 7) // limit textfield to 7 characters
                {
                    e.consume();
                }
            }
        });
        centralPanel.add(keywordField, pos);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String keyword = keywordField.getText();
                if (name.equals("") || username.equals("") || email.equals("") || password.length() <= 0 || confirmPassword.length() <= 0 || keyword.equals("")) {
                    JOptionPane.showMessageDialog(UC06UI.this,
                            "Missing data. Please check.",
                            "Missing data error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (checkPassword(password, confirmPassword)) {
                        if (controller.validateUser(username, email, password, name, language, timeZone, keyword)) {
                            int confirm = JOptionPane.showOptionDialog(
                                    null, "Are You Sure?",
                                    "User Registry Confirmation", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                            if (confirm == 0) {
                                if (controller.registerUser()) {
                                    JOptionPane.showMessageDialog(UC06UI.this,
                                            "User registry acomplished.",
                                            "User registry confirmation.",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    dispose();
                                    setVisible(false);
                                    menuWindow.setVisible(true);
                                }
                            } else {
                                JOptionPane.showMessageDialog(UC06UI.this,
                                        "User registry error. Please try again later",
                                        "User registry confirmation error.",
                                        JOptionPane.ERROR_MESSAGE);
                                dispose();
                                setVisible(false);
                                menuWindow.setVisible(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(UC06UI.this,
                                    "User already exists. Please type data again.",
                                    "User check error.",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(UC06UI.this,
                                "Bad password. Please type again both password and confirm password field.",
                                "Password error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        }
        );
        pos.gridx = 0;
        pos.gridy = 8;
        centralPanel.add(createButton, pos);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
                menuWindow.setVisible(true);
            }
        }
        );
        pos.gridx = 1;
        pos.gridy = 8;
        centralPanel.add(cancelButton, pos);

        add(centralPanel);
    }

    private boolean checkPassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            return false;
        }
        if (checkIfPasswordContaisOneOfTheCharacters(password.toCharArray(), UPPER_CASES)
                && checkIfPasswordContaisOneOfTheCharacters(password.toCharArray(), LOWER_CASES)
                && checkIfPasswordContaisOneOfTheCharacters(password.toCharArray(), NUMBER)
                && checkIfPasswordContaisOneOfTheCharacters(password.toCharArray(), PUNCTUATION_MARK)) {
            return true;
        }
        return false;
    }

    private boolean checkIfPasswordContaisOneOfTheCharacters(char[] password, char[] characterList) {
        boolean condition = false;
        for (int i = 0; i < characterList.length; i++) {
            String s1 = String.valueOf(characterList[i]);
            for (int j = 0; j < password.length; j++) {
                String s2 = String.valueOf(password[j]);
                if (s2.equals(s1)) {
                    condition = true;
                }
            }
        }
        return condition;
    }
}
