/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;

/**
 *
 * @author Hugo
 */
public class Place implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String localName;

    public Place(String localName) {
        this.localName = localName;
    }
    
    public String toString(){
    
    return String.format("%s",localName);
    }
}
