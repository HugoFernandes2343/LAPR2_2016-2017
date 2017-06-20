package lapr.project.ui;

import lapr.project.model.EventManager;
import lapr.project.model.FairCenter;
import lapr.project.model.User;

/**
 * @author PC
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

        User eventManagerDefault = new User("default","default","default","default","","GMT+00","delta");
        fc.registerUser(eventManagerDefault);
        fc.getEventManagerRegistry().addEventManager(new EventManager(eventManagerDefault));
        
        String guestInfo = "guest";
        User guestUser = new User(guestInfo,guestInfo,guestInfo,guestInfo,"","GMT+00",guestInfo);
        fc.registerUser(guestUser);
        //Program
        UserInterface UI = new UserInterface(fc);
    }
}