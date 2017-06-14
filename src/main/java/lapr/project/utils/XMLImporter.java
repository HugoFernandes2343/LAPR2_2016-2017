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
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import lapr.project.model.FairCenter;
import lapr.project.ui.UserInterface;

/**
 *
 * @author PC
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class XMLImporter<T> {

    private File fileLocation;

    public XMLImporter() {
        
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
        return fc;
    }
    
    /**
     *
     * @return
     * @throws FileNotFoundException
     */
    public T importData() throws FileNotFoundException {
        setLookAndFeel();
        JFileChooser fileChooser = new JFileChooser();
        int input = fileChooser.showOpenDialog(fileChooser);
        if (checkOption(input) == true) {
            System.exit(0);        
        }
        File file = fileChooser.getSelectedFile();
        this.fileLocation = file;
        T t;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("lapr.project.model");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            t = (T) jaxbUnmarshaller.unmarshal(fileLocation);
        } catch (JAXBException ex) {
            Logger.getLogger(XMLImporter.class.getName()).log(Level.SEVERE, null, ex);
            t = null;
            System.out.println(ex.getMessage());
        }
        return t;
    }

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
