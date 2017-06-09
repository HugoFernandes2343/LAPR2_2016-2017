/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;
import lapr.project.utils.Encryption;

/**
 *
 * @author PC
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private char[] password;
    private String name;
    private String language;
    private String timeZone;
    private Encryption encryption;
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
    public User(String username, String email, char[] password, String name, String language, String timeZone, String keyword) {
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
    public User(String ID, char[] password, String name,String keyword) {
        this.encryption=new Encryption(keyword);
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

//    /**
//     *
//     * @param nome
//     */
//    private void setNome(String nome) {
//        this.name = nome;
//    }

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
    private void setPassword(char[] pwd) {
        this.password = pwd;
    }

    /**
     *
     * @return
     */
    public char[] getPassword() {
        return this.password;
    }

    /**
     *
     * @param lang
     */
    public void setLanguage(String lang) {
        this.language = lang;
    }

    /**
     *
     * @param timeZone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    
    public String getLanguage(){
        return this.language;
    }
    
    public String getTimeZone(){
        return this.timeZone;
    }
    
    public String toInfoString(){
        return String.format("Name: %s%nEmail: %s%n", name, email);
    }
    
    @Override
    public String toString() {
        return String.format("Name: %s%nEmail: %s%n Password: %s%n", name, email,String.valueOf(password));
    }
}
