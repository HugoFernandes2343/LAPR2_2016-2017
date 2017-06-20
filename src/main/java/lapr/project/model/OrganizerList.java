/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Hugo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizerList {

    @XmlElement
    private List<Organizer> organizerList;

    public OrganizerList() {
        organizerList = new ArrayList<>();

    }

    public void addOrganizer(User user) {
        this.organizerList.add(new Organizer(user));
    }

    public List<Organizer> getList() {
        return organizerList;
    }

}
