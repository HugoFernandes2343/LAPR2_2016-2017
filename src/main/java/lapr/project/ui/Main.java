package lapr.project.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import lapr.project.model.Congress;
import lapr.project.model.Exhibition;
import lapr.project.model.FAE;
import lapr.project.model.FairCenter;
import lapr.project.model.User;
import org.custommonkey.xmlunit.XMLUnit;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    private static String fileLocation = "target/FairCenterData.xml";

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, JAXBException {
        try {
            //Import
            File fileFairCenter = new File(fileLocation);
            JAXBContext jaxbContext = JAXBContext.newInstance(FairCenter.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            FairCenter fc = (FairCenter) jaxbUnmarshaller.unmarshal(fileFairCenter);

            //Program
            UserInterface UI = new UserInterface(fc);

            //Export
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// output pretty printed

            jaxbMarshaller.marshal(fc, fileFairCenter);
            jaxbMarshaller.marshal(fc, System.out);

            XMLUnit.setIgnoreAttributeOrder(true);
            XMLUnit.setIgnoreComments(true);
            XMLUnit.setIgnoreWhitespace(true);

        } catch (JAXBException ex) {

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

            System.out.println("File or Data not found");
            File fileFairCenter = new File(fileLocation);
            JAXBContext jaxbContext = JAXBContext.newInstance(FairCenter.class);
            /**
             * 
             */
            FairCenter fc = new FairCenter();
            addTestObjects(fc);
            UserInterface UI = new UserInterface(fc);
            /**
             * 
             */
            //Export
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// output pretty printed
            jaxbMarshaller.marshal(fc, fileFairCenter);
            jaxbMarshaller.marshal(fc, System.out);
            XMLUnit.setIgnoreAttributeOrder(true);
            XMLUnit.setIgnoreComments(true);
            XMLUnit.setIgnoreWhitespace(true);
        }
    }

    public static void addTestObjects(FairCenter fc) {
        User uTest = new User("default", "default".toCharArray(), "defaultName", "key");
        fc.getConfirmedUsers().getUsersList().add(uTest);

        User uTest2 = new User("default2", "default2".toCharArray(), "defaultName2", "key");
        fc.getConfirmedUsers().getUsersList().add(uTest2);

        User uTest3 = new User("default3", "default3".toCharArray(), "defaultName3", "key");
        fc.getConfirmedUsers().getUsersList().add(uTest3);

        Congress eventTest1 = new Congress("EventTest", "Description", "Home", new Date(), new Date(), new Date(), new Date());
        Exhibition eventTest2 = new Exhibition("EventTest2", "Description2", "Home", new Date(), new Date(), new Date(), new Date());

        fc.getEventRegistry().registerEvent(eventTest1);
        fc.getEventRegistry().registerEvent(eventTest2);
        
        eventTest1.addOrganizer(uTest2);
        FAE example = new FAE(uTest2);
        eventTest1.getFAEList().addFAE(example);

        FAE example2 = new FAE(uTest3);
        eventTest1.getFAEList().addFAE(example2);

        
    }

}
