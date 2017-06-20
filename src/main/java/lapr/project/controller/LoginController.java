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
 *
 * @author PC
 */
public class LoginController {


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
     * @param id
     * @param password
     * @return
     */
    public boolean authenticate(String id,String password) {
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
     *
     * @param u
     * @param id
     * @return
     */
    private static boolean checkID(User u, String id) {
        return u.getEmail().equals(id) || u.getUsername().equals(id);
    }

    /**
     *
     * @param u
     * @param password
     * @return
     */
    private static boolean checkPassword(User u, String password) {
        return u.getPassword().equals(password);
    }

    public User getUser() {
        return this.user;
    }
}
