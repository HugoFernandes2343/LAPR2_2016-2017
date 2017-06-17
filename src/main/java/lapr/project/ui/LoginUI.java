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
import javax.swing.*;
import lapr.project.controller.LoginController;
import lapr.project.model.FairCenter;

/**
 *
 * @author PC
 */
@SuppressWarnings("serial")
public class LoginUI extends JFrame {
    
    private final JButton loginButton = new JButton("Login");
    private final JButton cancelButton = new JButton("Cancel");
    private final int WIDTH = 250;
    private final int HEIGHT = 400;
    
    private JFrame window = new JFrame("Login Window");

    protected LoginController loginController;
    protected FairCenter fc;

    public LoginUI(FairCenter fc) {
        this.fc=fc;
        loginController = new LoginController(fc);
//        JFrame window = new JFrame("Login Window");
        createFrame(window);
    }

    private JFrame createFrame(JFrame window) {
        window.setSize(HEIGHT, WIDTH);
        BorderLayout layout = new BorderLayout();
        window.setLayout(layout);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createElements(window);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        return window;
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
                String userIDTemp = userIdentification.getText();
                String userPasswordTemp = new String(userPasswordField.getPassword());
                if (loginController.authenticate(userIDTemp, userPasswordTemp) == true) {
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                    MainMenu mainMenu = new MainMenu(fc,loginController.getUser(),window);
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
