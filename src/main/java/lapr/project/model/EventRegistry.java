/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class EventRegistry implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Congress> congressList;
    private ArrayList<Exhibition> exhibitionList;
    
    public EventRegistry(){
        this.congressList=new ArrayList<>();
        this.exhibitionList=new ArrayList<>();
    }
    
    public ArrayList<Event> getEventsByOrganizer(){
        ArrayList<Event> allEvents=new ArrayList<>();
        allEvents.addAll(congressList);
        allEvents.addAll(exhibitionList);
        for(Event e : allEvents){
            if(e.validateEventStateFAEDefined()){
                //A implementar
            }
            
        }
        return null;
    }
}
