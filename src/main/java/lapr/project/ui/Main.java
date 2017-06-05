package lapr.project.ui;

import lapr.project.model.CalculatorExample;
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
        
        User uTest = new User("default","default","defaultName");
        fc.getUserRegistry().getUsersList().add(uTest);
        
        UserInterface UI = new UserInterface(fc);
//        CalculatorExample calculatorExample = new CalculatorExample();
//        System.out.println(calculatorExample.sum(3, 5));
    }

}
