/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.xml.bind.annotation.*;
import lapr.project.utils.Encryption;

/**
 *
 * @author PC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)//Usar isto para aceder a private atributtes senao tenho de dar @XmlElement a todos os getters
public class User {

    @XmlElement
    private String username;
    @XmlElement
    private String email;
    @XmlElement
    private String password;
    @XmlElement
    private String name;
    @XmlElement
    private String language;
    @XmlElement
    private String timeZone;
    @XmlElement
    private Encryption encryption;
    @XmlElement
    private String keyword;

    private static String USERNAME_BY_OMISSION = "-";
    private static String EMAIL_BY_OMISSION = "-";
    private static String NAME_BY_OMISSION = "-";

    /**
     *
     * @param username
     * @param email
     * @param password
     * @param name
     * @param language
     * @param timeZone
     * @param keyword
     */
    public User(String username, String email, String password, String name, String language, String timeZone, String keyword) {
        this.encryption = new Encryption(keyword);
        this.email = email;
        this.language = language;
        this.name = name;
        this.password = password;
        this.timeZone = timeZone;
        this.username = username;
    }

    /**
     *
     * @param ID
     * @param password
     * @param name
     * @param keyword
     */
    public User(String ID, String password, String name, String keyword) {
        this.encryption = new Encryption(keyword);
        if (ID.contains("@")) {
            this.email = ID;
            this.username = USERNAME_BY_OMISSION;
        } else {
            this.username = ID;
            this.email = EMAIL_BY_OMISSION;
        }
        this.password = password;
        this.name = name;
    }

    public User() {
        //to avoid xml conflicts
    }

    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param username
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @param email
     */
    private void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     *
     * @param pwd
     */
    private void setPassword(String pwd) {
        this.password = pwd;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @param lang
     */
    public void setCurrentLanguage(String lang) {
        this.language = lang;
    }

    /**
     *
     * @param timezone
     */
    public void setTimezone(String timezone) {
        this.timeZone = timezone;
    }

    public String getCurrentLanguage() {
        return language;
    }

    public String getTimezone() {
        return timeZone;
    }

    public String toInfoString() {
        return String.format("Name: %s%nEmail: %s%n", name, email);
    }

    @Override
    public String toString() {
        return String.format("Name: %s%nEmail: %s%n Password: %s%n", name, email, String.valueOf(password));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User that = (User) o;

        if (!name.equals(that.name)) {
            return false;
        }
        if (!email.equals(that.email)) {
            return false;
        }
        if (!username.equals(that.username)) {
            return false;
        }
        return this.password.equals(that.getPassword());

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
