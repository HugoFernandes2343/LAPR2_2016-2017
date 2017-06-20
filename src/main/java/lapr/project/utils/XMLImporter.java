/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lapr.project.model.Event;
import lapr.project.model.FairCenter;
import lapr.project.ui.UserInterface;

/**
 *
 * @author PC
 */
public class XMLImporter {

    private File fileLocation;

    public XMLImporter() {
        //Empty Object to access methods
    }

    public FairCenter importAllData() throws FileNotFoundException {
        setLookAndFeel();
        JFileChooser fileChooser = new JFileChooser();
        int input = fileChooser.showOpenDialog(fileChooser);

        if (checkOption(input) == true) {
            System.exit(0);
        }

        File file = fileChooser.getSelectedFile();
        this.fileLocation = file;

        FairCenter fc;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FairCenter.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            fc = (FairCenter) jaxbUnmarshaller.unmarshal(fileLocation);
//            System.out.println(jaxbUnmarshaller.);
        } catch (JAXBException ex) {
            Logger.getLogger(XMLImporter.class.getName()).log(Level.SEVERE, null, ex);
            fc = new FairCenter();
            System.out.println(ex.getMessage());
        }
        fc.decryptUsers();
        return fc;
    }

    /**
     *
     * @return @throws FileNotFoundException
     */
    public Event importEventData() throws FileNotFoundException {
        setLookAndFeel();
        JFileChooser fileChooser = new JFileChooser();
        int input = fileChooser.showOpenDialog(fileChooser);
        if (checkOption(input) == true) {
            System.exit(0);
        }
        File file = fileChooser.getSelectedFile();
        this.fileLocation = file;
        Event event;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            event = (Event) jaxbUnmarshaller.unmarshal(fileLocation);
        } catch (JAXBException ex) {
            Logger.getLogger(XMLImporter.class.getName()).log(Level.SEVERE, null, ex);
            event = null;
            System.out.println(ex.getMessage());
        }
        return event;
    }

//    public Congress importCongressData() throws FileNotFoundException {
//        setLookAndFeel();
//        JFileChooser fileChooser = new JFileChooser();
//        int input = fileChooser.showOpenDialog(fileChooser);
//        if (checkOption(input) == true) {
//            System.exit(0);
//        }
//        File file = fileChooser.getSelectedFile();
//        this.fileLocation = file;
//        Congress event;
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Congress.class);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            event = (Congress) jaxbUnmarshaller.unmarshal(fileLocation);
//        } catch (JAXBException ex) {
//            Logger.getLogger(XMLImporter.class.getName()).log(Level.SEVERE, null, ex);
//            event = null;
//            System.out.println(ex.getMessage());
//        }
//        return event;
//    }
//    
//        public Exhibition importExhibitionData() throws FileNotFoundException {
//        setLookAndFeel();
//        JFileChooser fileChooser = new JFileChooser();
//        int input = fileChooser.showOpenDialog(fileChooser);
//        if (checkOption(input) == true) {
//            System.exit(0);        
//        }
//        File file = fileChooser.getSelectedFile();
//        this.fileLocation = file;
//        Exhibition event;
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Event.class);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            event = (Exhibition) jaxbUnmarshaller.unmarshal(fileLocation);
//        } catch (JAXBException ex) {
//            Logger.getLogger(XMLImporter.class.getName()).log(Level.SEVERE, null, ex);
//            event = null;
//            System.out.println(ex.getMessage());
//        }
//        return event;
//    }

    private void setLookAndFeel() {
        //SetLookAndFeel
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checkOption(int input) {
        if (input == JFileChooser.CANCEL_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}
