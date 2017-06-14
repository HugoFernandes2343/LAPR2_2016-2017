/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Hugo
 */
public class UC01ControllerTest {

    public UC01ControllerTest() {
    }

    /**
     * Test of createEvent method, of class UC01Controller.
     */
    @Test
    public void testCreateEvent() {
        FairCenter fc = new FairCenter();
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        User user = new User(username, email, password, name, language, timeZone, keyword);
        fc.registerUser(user);
        System.out.println("createEvent");
        String title = "Lego exposition";
        String description = "A place to buy rare Lego items";
        String place = "Exponor";
        String eventType = "Exposition";
        UC01Controller instance = new UC01Controller(fc, user);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = format.parse("10/08/2017");
            Date endDate = format.parse("15/08/2017");
            Date applicationBegin = format.parse("10/06/2017");
            Date applicationEnd = format.parse("10/07/2017");
            instance.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Test of getUsers method, of class UC01Controller.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        FairCenter fc = new FairCenter();
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        User user = new User(username, email, password, name, language, timeZone, keyword);
        fc.registerUser(user);
        UC01Controller instance = new UC01Controller(fc, user);
        ArrayList<User> expResult = new ArrayList<>();
        expResult.add(user);
        ArrayList<User> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of newOrganizer method, of class UC01Controller.
     */
    @Test
    public void testNewOrganizer() {
        System.out.println("newOrganizer");
        FairCenter fc = new FairCenter();
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        User user = new User(username, email, password, name, language, timeZone, keyword);
        String title = "Lego exposition";
        String description = "A place to buy rare Lego items";
        String place = "Exponor";
        String eventType = "Exposition";
        fc.registerUser(user);
        UC01Controller instance = new UC01Controller(fc, user);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = format.parse("10/08/2017");
            Date endDate = format.parse("15/08/2017");
            Date applicationBegin = format.parse("10/06/2017");
            Date applicationEnd = format.parse("10/07/2017");
            instance.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        instance.newOrganizer(user);
    }

    /**
     * Test of getNumberOfOrganizers method, of class UC01Controller.
     */
    @Test
    public void testGetNumberOfOrganizers() {
        System.out.println("getNumberOfOrganizers");
        FairCenter fc = new FairCenter();
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        User user = new User(username, email, password, name, language, timeZone, keyword);
        fc.registerUser(user);
        UC01Controller instance = new UC01Controller(fc, user);
        int expResult = 0;
        int result = instance.getNumberOfOrganizers();
        assertEquals(expResult, result);
    }

    /**
     * Test of registerEvent method, of class UC01Controller. Due to the design
     * of UC01UI, the validation regarding all the data of the Event is
     * validated before reaching the controller. So it only activates the
     * RegisterEvent method if the event is ready to be registered. This one
     * tests the sucess registration of the event.
     */
    @Test
    public void testRegisterEventSucess() {
        System.out.println("registerEvent");
        FairCenter fc = new FairCenter();
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        User user = new User(username, email, password, name, language, timeZone, keyword);
        String title = "Lego exposition";
        String description = "A place to buy rare Lego items";
        String place = "Exponor";
        String eventType = "Exposition";
        fc.registerUser(user);
        UC01Controller instance = new UC01Controller(fc, user);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = format.parse("10/08/2017");
            Date endDate = format.parse("15/08/2017");
            Date applicationBegin = format.parse("10/06/2017");
            Date applicationEnd = format.parse("10/07/2017");
            instance.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        boolean expResult = true;
        boolean result = instance.registerEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of registerEvent method, of class UC01Controller. Due to the design
     * of UC01UI, the validation regarding all the data of the Event is
     * validated before reaching the controller. So it only activates the
     * RegisterEvent method if the event is ready to be registered. This one
     * tests the unsucessfull registration of the event.
     */
    @Test
    public void testRegisterEventUnsucess() {
        System.out.println("registerEvent");
        FairCenter fc = new FairCenter();
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        User user = new User(username, email, password, name, language, timeZone, keyword);
        String title = "Lego exposition";
        String description = "A place to buy rare Lego items";
        String place = "Exponor";
        String eventType = "Exposition";
        fc.registerUser(user);
        UC01Controller instance = new UC01Controller(fc, user);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = format.parse("10/08/2017");
            Date endDate = format.parse("15/08/2017");
            Date applicationBegin = format.parse("10/06/2017");
            Date applicationEnd = format.parse("10/07/2017");
            instance.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        boolean expResult = false;
        boolean firstRegistration = instance.registerEvent();
        boolean result = instance.registerEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateOrganizer method, of class UC01Controller. This tests if
     * the user in not yet organizer of the event.
     */
    @Test
    public void testValidateOrganizerIsValid() {
        System.out.println("validateOrganizer");
        FairCenter fc = new FairCenter();
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        User user = new User(username, email, password, name, language, timeZone, keyword);
        String title = "Lego exposition";
        String description = "A place to buy rare Lego items";
        String place = "Exponor";
        String eventType = "Exposition";
        fc.registerUser(user);
        UC01Controller instance = new UC01Controller(fc, user);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = format.parse("10/08/2017");
            Date endDate = format.parse("15/08/2017");
            Date applicationBegin = format.parse("10/06/2017");
            Date applicationEnd = format.parse("10/07/2017");
            instance.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        boolean expResult = true;
        boolean result = instance.validateOrganizer(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of validateOrganizer method, of class UC01Controller. This tests if
     * the user is alredy organizer of the event
     */
    @Test
    public void testValidateOrganizerIsNotValid() {
        System.out.println("validateOrganizer");
        FairCenter fc = new FairCenter();
        String username = "htc";
        String email = "htc@gamil.com";
        String password = "Aa-1";
        String name = "Hugo Carvalho";
        String language = "Portuguese";
        String timeZone = "GMT+00";
        String keyword = "delta";
        User user = new User(username, email, password, name, language, timeZone, keyword);
        String title = "Lego exposition";
        String description = "A place to buy rare Lego items";
        String place = "Exponor";
        String eventType = "Exposition";
        fc.registerUser(user);
        UC01Controller instance = new UC01Controller(fc, user);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = format.parse("10/08/2017");
            Date endDate = format.parse("15/08/2017");
            Date applicationBegin = format.parse("10/06/2017");
            Date applicationEnd = format.parse("10/07/2017");
            instance.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        boolean expResult = false;
        instance.newOrganizer(user);
        boolean result = instance.validateOrganizer(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
