package lapr.project.ui;

import java.util.Date;
import lapr.project.model.CalculatorExample;
import lapr.project.model.Congress;
import lapr.project.model.Exhibition;
import lapr.project.model.FAE;
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
    public static void main(String[] args) {
        FairCenter fc = new FairCenter();

        User uTest = new User("default", "default".toCharArray(), "defaultName","key");
        fc.getUserRegistry().getUsersList().add(uTest);
        
        User uTest2 = new User("default2", "default2".toCharArray(), "defaultName2","key");
        fc.getUserRegistry().getUsersList().add(uTest2);
        
        User uTest3 = new User("default3", "default3".toCharArray(), "defaultName3","key");
        fc.getUserRegistry().getUsersList().add(uTest3);

        Congress eventTest1 = new Congress("EventTest", "Description", "Home", new Date(), new Date(), new Date(), new Date());
        Exhibition eventTest2 = new Exhibition("EventTest2", "Description2", "Home", new Date(), new Date(), new Date(), new Date());

        eventTest1.addOrganizer(uTest);
        FAE example = new FAE(uTest2);
        eventTest1.getFAEList().addFAE(example);

        FAE example2 = new FAE(uTest3);
        eventTest1.getFAEList().addFAE(example2);

        fc.getEventRegistry().registerEvent(eventTest1);
        fc.getEventRegistry().registerEvent(eventTest2);

        /**
         *          */
        UserInterface UI = new UserInterface(fc);
//        CalculatorExample calculatorExample = new CalculatorExample();
//        System.out.println(calculatorExample.sum(3, 5));
        /**
         *          */
    }

}
