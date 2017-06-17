package lapr.project.ui;

import java.io.FileNotFoundException;
import java.util.Date;
import lapr.project.model.Congress;
import lapr.project.model.EventManager;
import lapr.project.model.Exhibition;
import lapr.project.model.FAE;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import lapr.project.utils.XMLImporter;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    private static String defaultFileLocation = "target/FairCenterData.xml";

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        FairCenter fc = new FairCenter();
//        //Import All Data From File
//        XMLImporter importer = new XMLImporter();
//        fc = importer.importAllData();
//        if (fc == null) {
//            throw new FileNotFoundException();
//        }
        User eventManagerDefault = new User("default","default","default","default","English","GMT+00","delta");
        fc.registerUser(eventManagerDefault);
        //Program
        UserInterface UI = new UserInterface(fc);
    }

//    public static void addTestObjects(FairCenter fc) {
//        User userManager = new User("default", "default", "defaultName", "key");
//        fc.getConfirmedUsers().getUsersList().add(userManager);
//
//        EventManager eventManager = new EventManager(userManager);
//        fc.getEventManagerRegistry().getEventManagersList().add(eventManager);
//
//        User uTest = new User("defaultUser", "default", "defaultName", "key");
//        fc.getConfirmedUsers().getUsersList().add(uTest);
//
//        User uTest2 = new User("defaultUser2", "default2", "defaultName2", "key");
//        fc.getConfirmedUsers().getUsersList().add(uTest2);
//
//        User uTest3 = new User("defaultUser3", "default3", "defaultName3", "key");
//        fc.getConfirmedUsers().getUsersList().add(uTest3);
//
//        Congress eventTest1 = new Congress("EventTest", "Description", "Home", new Date(), new Date(), new Date(), new Date());
//        Exhibition eventTest2 = new Exhibition("EventTest2", "Description2", "Home", new Date(), new Date(), new Date(), new Date());
//
//        fc.getEventRegistry().registerEvent(eventTest1);
//        fc.getEventRegistry().registerEvent(eventTest2);
//
//        eventTest1.addOrganizer(uTest2);
//        FAE example = new FAE(uTest2);
//        eventTest1.getFAEList().addFAE(example);
//
//        FAE example2 = new FAE(uTest3);
//        eventTest1.getFAEList().addFAE(example2);
//
//    }

}
