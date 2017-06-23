/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 * Class that controls the login data. Checks if the data inserted matches a
 * User in the System.
 *
 * @author PC
 */
public class LoginController {

    /**
     * Object that saves the User found in the system (if found)
     */
    protected User user;

    /**
     * The fair center object that holds all user data that is used to check the
     * user existence
     */
    private FairCenter fairCenter;

    public LoginController(FairCenter fc) {
        this.fairCenter = fc;
    }

    /**
     * Authenticate method used to check each user in the system for its data.
     * Recieves the username or email and the password inserted in the UI. Then
     * checks each user and tries to find a match
     *
     * @param id ID inserted
     * @param password password inserted
     * @return boolean that confirms if the data inserted is accepted (true =
     * accepted // false = not accepted)
     */
    public boolean authenticate(String id, String password) {
        List<User> usersList;
        usersList = fairCenter.getConfirmedUsers().getUsersList();//Still dont know how to implement encryption without hash
        for (User u : usersList) {
            if (checkID(u, id) && checkPassword(u, password)) {
                user = u;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for a match for the inserted ID
     *
     * @param u current user being compared (from list)
     * @param id ID inserted
     * @return boolean confirmation
     */
    private static boolean checkID(User u, String id) {
        return u.getEmail().equals(id) || u.getUsername().equals(id);
    }

    /**
     * Checks for a match for the inserted password
     *
     * @param u current user being compared (from list)
     * @param password password inserted
     * @return boolean confirmation
     */
    private static boolean checkPassword(User u, String password) {
        return u.getPassword().equals(password);
    }

    /**
     * get method of the attribute user
     *
     * @return the user that is saved in this object
     */
    public User getUser() {
        return this.user;
    }
}
