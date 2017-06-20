/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hugo
 */
@XmlRootElement(name="assignment")
public class Assignment {
    
	@XmlElement(name="fae")
	private FAE FAE;

	public Assignment(FAE FAE) {
		this.FAE = FAE;
	}

	private Assignment() {
            //Avoid xml conflicts
	}

	@Override
	public int hashCode() {
		return FAE.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Assignment)) {
			return false;
		}

		Assignment that = (Assignment) o;

		return FAE.equals(that.FAE);

	}

    public FAE getFAE() {
        return FAE;
    }
}
