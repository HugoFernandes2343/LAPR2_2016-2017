/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import lapr.project.controller.LoginController;

/**
 *
 * @author PC
 */
public class LoginUI extends JFrame {

    /**
     * User ID used in the Login
     */
    private String userID;

    /**
     * User Password used in the Login
     */
    private String userPassword;

    private final JButton loginButton = new JButton("Login");
    private final JButton cancelButton = new JButton("Cancel");

    private LoginController loginController;

    public LoginUI(/**
             * FairCenter fc
             */
            ) {
        LoginWindow loginWindow = new LoginWindow();

        //loginController = new LoginController(ce, userID, userPassword);
    }

    class LoginWindow extends JFrame {

        private int HEIGHT = 400;
        private int WIDTH = 250;

        private LoginWindow() {
            JFrame loginFrame = new JFrame("Login Window");
            loginFrame.setSize(HEIGHT, WIDTH);
            BorderLayout layout = new BorderLayout();
            loginFrame.setLayout(layout);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            createElements(loginFrame);
            loginFrame.setResizable(false);
            loginFrame.setLocationRelativeTo(null);
            loginFrame.setVisible(true);
        }

    }

    private void createElements(JFrame loginFrame) {
        JPanel centralPanel = new JPanel(new GridBagLayout());
        centralPanel.setSize(200, 200);
        GridBagConstraints pos = new GridBagConstraints();

        JLabel usernameLabel = new JLabel("Username/Email:");
        pos.gridx = 0;
        pos.gridy = 0;
        centralPanel.add(usernameLabel, pos);

        JTextField userIdentification = new JTextField(20);
        pos.gridx = 1;
        pos.gridy = 0;
        centralPanel.add(userIdentification, pos);

        JLabel passwordLabel = new JLabel("Password:");
        pos.gridx = 0;
        pos.gridy = 1;
        centralPanel.add(passwordLabel, pos);

        JPasswordField userPasswordField = new JPasswordField(20);
        pos.gridx = 1;
        pos.gridy = 1;
        centralPanel.add(userPasswordField, pos);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userIDTemp=userIdentification.getText();
                String userPasswordTemp=userPasswordField.getText();
                if (loginController.authenticate(userIDTemp,userPasswordTemp) == true) {
                    loginController.setID(userIdentification.getText());
                    loginController.setPassword(userIdentification.getText());
                    //Carry on para o menu de UC's de acordo com o que pode fazer
                } else {
                    JOptionPane.showMessageDialog(LoginUI.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pos.gridx = 0;
        pos.gridy = 3;
        centralPanel.add(loginButton, pos);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                System.exit(0);//Placeholder until I find why after getting an incorrect login the cancel button refuses to shutdown the Java process
            }
        });
        pos.gridx = 1;
        pos.gridy = 3;
        centralPanel.add(cancelButton, pos);
        loginFrame.add(centralPanel);
    }

}
