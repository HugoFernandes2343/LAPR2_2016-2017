/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;

/**
 *
 * @author LAPR2-G054
 */
public class UC06Controller {

    private final FairCenter fc;
    private User user;
    private UserRegistry userRegistry;
    private List<User> userList;

    /**
     * constructor of UC06
     *
     * @param fc fair center in which the controller operates
     */
    public UC06Controller(FairCenter fc) {
        this.fc = fc;
    }

    /**
     * creates an User object with the inputed variables and checks if a user
     * with the same data already exists
     *
     * @param username inputed username to create new user
     * @param email inputed email to create new user
     * @param password inputed password to create new user
     * @param name inputed name to create new user
     * @param language inputed language to create new user
     * @param timeZone inputed timeZone to create new user
     * @param keyword inputed keyword to create new user
     * @return true if the inputed user is not repeated or barred from being
     * registerd false if the user cant be registered
     */
    public boolean validateUser(String username, String email, String password, String name, String language, String timeZone, String keyword) {
        this.user = new User(username, email, password, name, language, timeZone, keyword);
        this.userRegistry = fc.getConfirmedUsers();
        this.userList = userRegistry.getUsersList();
        for (int i = 0; i < userList.size(); i++) {
            User userCheck = userList.get(i);
            if (userCheck.equals(user)) {
                return false;
            }
        }
        return true;
    }

    /**
     * registers the user created
     *
     * @return true indicating the user was created
     */
    public boolean registerUser() {
        return fc.registerUser(user);
    }

}

