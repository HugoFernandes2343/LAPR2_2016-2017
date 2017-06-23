package lapr.project.ui;

import lapr.project.model.FairCenter;

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

        //Program
        UserInterface UI = new UserInterface(fc);
    }
}