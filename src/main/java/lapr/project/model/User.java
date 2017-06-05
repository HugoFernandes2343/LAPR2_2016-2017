/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author PC
 */
public class User {

    private String username;
    private String email;
    private char[] password;
    private String name;
    private String language;
    private String timeZone;

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
     */
    public User(String username, String email, char[] password, String name, String language, String timeZone) {
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
     */
    public User(String ID, char[] password, String name) {
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

    /**
     *
     * @param nome
     */
    private void setNome(String nome) {
        this.name = nome;
    }

    /**
     *
     * @return
     */
    public String getNome() {
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

}
