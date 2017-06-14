/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author PC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRegistry {

    /**
     *
     */
    @XmlElementWrapper
    @XmlElement(name = "user")
    private ArrayList<User> usersList;

    /**
     *
     */
    public UserRegistry() {
        usersList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public boolean registerUser(User user) {
        usersList.add(user);
        return true;
    }

    void decryptAll() {
        for (User u : usersList) {
            u.decryptData();
        }
    }

    void encryptAll() {
        for (User u : usersList) {
            u.encryptData();
        }
    }

}
