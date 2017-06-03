/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class UserRegistry {

    /**
     *
     */
    private ArrayList<User> usersList;
    
        /**
     * 
     */
    public UserRegistry() {
        usersList = new ArrayList();
    }

    /**
     * 
     * @return 
     */
    public ArrayList<User> getUsersList() {
        return usersList;
    }

}
