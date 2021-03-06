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
public class LoginUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JButton loginButton = new JButton("Login");
    private final JButton cancelButton = new JButton("Cancel");
    private final int windowWidth = 250;
    private final int windowHeight = 400;

    protected LoginController loginController;
    protected FairCenter fc;
    protected MainMenu mainMenu;
    protected boolean mainMenuOpened;

    public LoginUI(FairCenter fc) {
        this.mainMenuOpened = false;
        this.fc = fc;
        this.setName("Login Window");
        loginController = new LoginController(fc);
        createFrame();
    }

    private JFrame createFrame() {
        this.setSize(windowHeight, windowWidth);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createElements(this);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        return this;
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
                if (loginController.authenticate(userIDTemp, userPasswordTemp)) {
                    loginFrame.setVisible(false);
                    loginFrame.dispose();
                    if (mainMenuOpened == false) {
                        mainMenu = new MainMenu(fc, loginController.getUser(), LoginUI.this);
                        mainMenuOpened = true;
                    } else if (mainMenuOpened == true) {
                        mainMenu.setActiveFairCenter(fc);
                        mainMenu.setActiveUser(loginController.getUser());
                        mainMenu.getCheck().assignNew(fc, loginController.getUser());
                        mainMenu.setLoginWindow(LoginUI.this);
                    }
                    mainMenu.setVisible(true);
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
