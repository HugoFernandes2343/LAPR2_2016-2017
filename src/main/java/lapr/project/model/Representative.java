/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author hugod
 */
public class Representative {
    
    private User u;
    
    public Representative(User u){
        this.u=u;
    }
    
    public User getUser(){
        return this.u;
    }
    
}
