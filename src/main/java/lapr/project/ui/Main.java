package lapr.project.ui;

import java.io.FileNotFoundException;
import lapr.project.model.EventManager;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        FairCenter fc = new FairCenter();
//        //Import All Data From File
//        XMLImporter importer = new XMLImporter();
//        fc = importer.importAllData();
//        if (fc == null) {
//            throw new FileNotFoundException();
//        }
        User eventManagerDefault = new User("default","default","default","default","English","GMT+00","delta");
        fc.registerUser(eventManagerDefault);
        fc.getEventManagerRegistry().addEventManager(new EventManager(eventManagerDefault));
        
        User guestUser = new User("guest","guest","-","guest");
        fc.registerUser(guestUser);
        //Program
        UserInterface UI = new UserInterface(fc);
    }
}
