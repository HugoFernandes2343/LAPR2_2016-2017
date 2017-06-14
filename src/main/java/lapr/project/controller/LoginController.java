/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 *
 * @author PC
 */
public class LoginController {

    /**
     *
     */
    private String ID;

    /**
     *
     */
    private String password;

    /**
     *
     */
    protected User user;

    /**
     *
     */
    private FairCenter fairCenter;

    public LoginController(FairCenter fc) {
        this.fairCenter = fc;
    }

    /**
     *
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param ID
     * @param password
     * @return
     */
    public boolean authenticate(String ID,String password) {
        ArrayList<User> usersList;
        usersList = fairCenter.getConfirmedUsers().getUsersList();//Still dont know how to implement encryption without hash
        for (User u : usersList) {
            if (checkID(u, ID) == true && checkPassword(u, password) == true) {
                user = u;
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param u
     * @param ID
     * @return
     */
    private boolean checkID(User u, String ID) {
        if (u.getEmail().equals(ID) || u.getUsername().equals(ID)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param u
     * @param password
     * @return
     */
    private boolean checkPassword(User u, String password) {
        return u.getPassword().equals(password);
    }

    public User getUser() {
        return this.user;
    }
}
