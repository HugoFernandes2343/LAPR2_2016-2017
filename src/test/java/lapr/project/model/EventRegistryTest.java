/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class EventRegistryTest {

    public EventRegistryTest() {
    }

    /**
     * Test of getEventsByOrganizer method, of class EventRegistry.
     */
    @Test
    public void testGetEventsByOrganizer() {
        System.out.println("getEventsByOrganizer");
        User u = new User("ID", "password", "name", "keys");
        EventRegistry instance = new EventRegistry();
        String title = "title", description = "description", place = "local", eventType = "Congress";
        Date date = new Date();
        Event ev = instance.createEvent(title, description, place, date, date, date, date, eventType);
        Event ev1 = instance.createEvent(title, description, place, date, date, date, date, "Exhibition");
        ev.addOrganizer(u);
        instance.registerEvent(ev1);
        instance.registerEvent(ev);
        List<Event> expResult = new ArrayList<>();
        expResult.add(ev);
        List<Event> result = instance.getEventsByOrganizer(u);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventsNotFAEDefined method, of class EventRegistry.
     */
    @Test
    public void testGetEventsNotFAEDefined() {
        System.out.println("getEventsNotFAEDefined");
        EventRegistry instance = new EventRegistry();
        String title = "title", description = "description", place = "local", eventType = "Congress";
        Date date = new Date();
        Event ev = instance.createEvent(title, description, place, date, date, date, date, eventType);
        Event ev1 = instance.createEvent(title, description, place, date, date, date, date, "Exhibition");
        ev.state = new EventDefinedFAEState();
        ev1.state = new EventCreatedState();
        instance.registerEvent(ev);
        instance.registerEvent(ev1);
        List<Event> expResult = new ArrayList<>();
        expResult.add(ev1);
        List<Event> result = instance.getEventsNotFAEDefined();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllEvents method, of class EventRegistry.
     */
    @Test
    public void testGetAllEvents() {
        System.out.println("getAllEvents");
        EventRegistry instance = new EventRegistry();
        String title = "title", description = "description", place = "local", eventType = "Congress";
        Date date = new Date();
        Event ev = instance.createEvent(title, description, place, date, date, date, date, eventType);
        Event ev1 = instance.createEvent(title, description, place, date, date, date, date, "Exhibition");
        instance.registerEvent(ev);
        instance.registerEvent(ev1);
        List<Event> expResult = new ArrayList<>();
        expResult.add(ev);
        expResult.add(ev1);
        List<Event> result = instance.getAllEvents();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of createEvent method, of class EventRegistry.
//     */
//    @Test
//    public void testCreateEvent() {
//        System.out.println("createEvent");
//        String title = "titleTest";
//        String description = "descriptionTest";
//        String place = "placeTest";
//        Date startDate = new Date();
//        Date endDate = new Date();
//        Date applicationBegin = new Date();
//        Date applicationEnd = new Date();
////        String eventType = null;
//        EventRegistry instance = new EventRegistry();
//        Event expResult = new Event("titleTest","descriptionTest","placeTest",new Date(),new Date(),new Date(),new Date());//Add the eventType
////        Exhibition expResult = (Exhibition) expResultEvent;
//        Event result = instance.createEvent(title, description, place, startDate, endDate, applicationBegin, applicationEnd, eventType);
//        assertEquals(expResult, result);
//    }
    /**
     * Test of registerEvent method, of class EventRegistry.
     */
    @Test
    public void testRegisterEvent() {
        System.out.println("registerEvent");
        String title = "titleTest";
        String description = "descriptionTest";
        String place = "placeTest";
        Date date = new Date();
        Exhibition event = new Exhibition(title, description, place, date, date, date, date);
        EventRegistry instance = new EventRegistry();
        boolean expResult = true;
        boolean result = instance.registerEvent(event);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateEvent method, of class EventRegistry.
     */
    @Test
    public void testValidateEvent() {
        System.out.println("validateEvent");
        String title = "titleTest";
        String description = "descriptionTest";
        String place = "placeTest";
        Date date = new Date();
        Exhibition event = new Exhibition(title, description, place, date, date, date, date);
        EventRegistry instance = new EventRegistry();
        boolean expResult = true;
        boolean result = instance.validateEvent(event);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventsReadyForSubmit method, of class EventRegistry.
     */
    @Test
    public void testGetEventsReadyForSubmit() {
        System.out.println("getEventsReadyForSubmit");
        EventRegistry instance = new EventRegistry();
        String title = "title", description = "description", place = "local", eventType = "Congress";
        Date date = new Date();
        Event congress = instance.createEvent(title, description, place, date, date, date, date, eventType);
        Event exhibition = instance.createEvent(title, description, place, date, date, date, date, "Exhibition");
        congress.state = new EventApplicationsOpenState(congress);
        exhibition.state = new EventCreatedState(exhibition);
        instance.registerEvent(congress);
        instance.registerEvent(exhibition);
        List<Event> expResult = new ArrayList<>();
        expResult.add(congress);
        List<Event> result = instance.getEventsReadyForSubmit();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getEventTitles method, of class EventRegistry.
//     */
//    @Test
//    public void testGetTitlesForSubmit() {
//        System.out.println("getTitlesForSubmit");
//        EventRegistry instance = new EventRegistry();        
//        List<Event> eventsReadyForSubmit = null;
//    
//        String[] expResult = null;
//        String[] result = instance.getEventTitles(eventsReadyForSubmit);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    /**
     * Test of getEventsByFAE method, of class EventRegistry.
     */
    @Test
    public void testGetEventsByFAE() {
        System.out.println("getEventsByFAE");
        User u = new User("ID", "password", "name", "keys");
        EventRegistry instance = new EventRegistry();
        String title = "title", description = "description", place = "local", eventType = "Congress";
        Date date = new Date();
        Event ev = instance.createEvent(title, description, place, date, date, date, date, eventType);
        Event ev1 = instance.createEvent(title, description, place, date, date, date, date, "Exhibition");
        ev.getFAEList().getFaeList().add(new FAE(u));
        ev1.getFAEList().getFaeList().add(new FAE(u));
        instance.registerEvent(ev);
        instance.registerEvent(ev1);
        List<Event> expResult = new ArrayList<>();
        expResult.add(ev);
        expResult.add(ev1);
        List<Event> result = instance.getEventsByFAE(u);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of registerApplicationChanges method, of class EventRegistry.
//     */
//    @Test
//    public void testRegisterApplicationChanges() {
//        System.out.println("registerApplicationChanges");
//        EventRegistry instance = new EventRegistry();
//        String title = "title", description = "description", place = "local", eventType = "Congress";
//        Date date = new Date();
//        Event event = instance.createEvent(title, description, place, date, date, date, date, eventType);
//        instance.registerEvent(event);
//        Application application = new Application("trade", "description", 919919919); 
//        
//        boolean expResult = false;
//        boolean result = instance.registerApplicationChanges(event, application);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getAllFaeUserReference method, of class EventRegistry.
     */
//    @Test
//    public void testGetAllFaeUserReference() {
//        System.out.println("getAllFaeUserReference");
//        EventRegistry instance = new EventRegistry();
//        List<User> expResult = null;
//        List<User> result = instance.getAllFaeUserReference();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkForRepresentativeApplications method, of class
//     * EventRegistry.
//     */
//    @Test
//    public void testCheckForRepresentativeApplications() {
//        System.out.println("checkForRepresentativeApplications");
//        User user = null;
//        EventRegistry instance = new EventRegistry();
//        boolean expResult = false;
//        boolean result = instance.checkForRepresentativeApplications(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEventsWithApplicationFromUser method, of class EventRegistry.
//     */
//    @Test
//    public void testGetEventsWithApplicationFromUser() {
//        System.out.println("getEventsWithApplicationFromUser");
//        User user = null;
//        EventRegistry instance = new EventRegistry();
//        List<Event> expResult = null;
//        List<Event> result = instance.getEventsWithApplicationFromUser(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
