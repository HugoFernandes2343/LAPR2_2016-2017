/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lapr.project.model.FairCenter;
import lapr.project.ui.UserInterface;
import org.custommonkey.xmlunit.XMLUnit;

/**
 *
 * @author PC
 * @param <T>
 */
public class XMLExporter<T> {

    private File fileLocation;

    public XMLExporter() {
        setLookAndFeel();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(fileChooser);
        File file = fileChooser.getSelectedFile();
        this.fileLocation = file;
    }

    public void exportAllData(FairCenter fc) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(FairCenter.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// output pretty printed
        fc.encryptUsers();
        jaxbMarshaller.marshal(fc, fileLocation);
        jaxbMarshaller.marshal(fc, System.out);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }

    public void exportData(T t) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance("lapr.project.model");
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// output pretty printed

        jaxbMarshaller.marshal(t, fileLocation);
        jaxbMarshaller.marshal(t, System.out);

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
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
}
