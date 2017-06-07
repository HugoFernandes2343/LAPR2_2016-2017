/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public class OrganizerList {

    private ArrayList<Organizer> organizerList;

    public OrganizerList() {
        organizerList = new ArrayList<>();

    }

    public void addOrganizer(User user) {
        organizerList.add(new Organizer(user));
    }

    public ArrayList<Organizer> getList() {
        return organizerList;
    }
    
 
}
