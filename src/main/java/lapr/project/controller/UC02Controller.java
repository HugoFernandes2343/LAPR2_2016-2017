/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.Serializable;
import java.util.ArrayList;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.model.UserRegistry;

/**
 *
 * @author PC
 */
public class UC02Controller implements Serializable{

    private static final long serialVersionUID = 1L;

    private User user;
    private FairCenter fc;
    
    public UC02Controller(FairCenter fc,User u) {
        this.user=u;
        this.fc=fc;
    }

    public ArrayList<Event> getEventsByOrganizer(){
        ArrayList<Event> listEventsByOrganizer = new ArrayList<>();
        UserRegistry ur = fc.getUserRegistry();
        
        
        return listEventsByOrganizer;
    }
    
}
