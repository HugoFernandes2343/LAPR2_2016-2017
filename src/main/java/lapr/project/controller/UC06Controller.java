/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;

/**
 *
 * @author Hugo
 */
public class UC06Controller {

    private FairCenter fc;
    private User user;
    private UserRegistry userRegistry;
    private ArrayList<User> userList;

    public UC06Controller(FairCenter fc) {
        this.fc = fc;
    }

    public boolean validateUser(String username, String email, String password, String name, String language, String timeZone, String keyword) {
        this.user = new User(username, email, password, name, language, timeZone, keyword);
        this.userRegistry = fc.getConfirmedUsers();
        this.userList = userRegistry.getUsersList();
        for (int i = 0; i < userList.size(); i++) {
            User userCheck=userList.get(i);
            if (userCheck.equals(user)) {
                return false;
            }
        }
        return true;
    }

    public boolean registerUser() {
        return fc.registerUser(user);
    }

}
