/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Hugo
 */
public class Organizer {

    private User user;

    public Organizer(User user) {
        this.user = user;
    }

    public String toString() {
        return user.toString();
    }
}