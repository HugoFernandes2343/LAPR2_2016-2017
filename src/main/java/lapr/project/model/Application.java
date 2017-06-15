package lapr.project.model;

import lapr.project.utils.Exportable;
import lapr.project.utils.Importable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Candidatura class.
 *
 * @author by Nuno Bettencourt [nmb@isep.ipp.pt] on 29/05/16.
 */
public class Application implements Importable<Application>, Exportable {

    private static final String ROOT_ELEMENT_NAME = "application";
    private static final String DESCRIPTION_ELEMENT_NAME = "description";
    private static final String KEYWORDS_ELEMENT_NAME = "keywords";
    private ArrayList<String> keywordList = new ArrayList<>();
    //private String description = "";
    private Boolean accepted;
    private String tradeName;
    private String address;
    private int phone;
    private double boothArea;
    private String[] productsToBeDisplayed;
    private int numberOfInvitations;

    /**
     * Constructor for Application
     *
     * @param description CandidaturaDescription
     * @param keywordList Keyword List
     */
    public Application(String tradeName, String address, int phone, double boothArea, String[] productsToBeDisplayed, int numberOfInvitations, String[] keywords) {
        this.tradeName = tradeName;
        this.address = address;
        this.phone = phone;
        this.boothArea = boothArea;
        this.productsToBeDisplayed = productsToBeDisplayed;
        this.numberOfInvitations = numberOfInvitations;
        for (int i = 0; i < keywords.length; i++) {
            String keyword = new String(keywords[i]);
            keywordList.add(keyword);
        }
    }

    /**
     * Default public constructor.
     */
    public Application() {

    }

    public String getTradeName() {
        return tradeName;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public double getBoothArea() {
        return boothArea;
    }

    public String[] getProductsToBeDisplayed() {
        return productsToBeDisplayed;
    }

    public int getNumberOfInvitations() {
        return numberOfInvitations;
    }

    /* private String getDescrition() {
        return description;
    }*/
    /**
     * Add a keyword to Candidatura.
     *
     * @param keyword Keyword to be added.
     */
    public void addKeyword(String keyword) {
        getKeywordList().add(keyword);
    }

    /**
     * Obtain the list of existing keywords.
     *
     * @return A list of existing keywords.
     */
    public ArrayList<String> getKeywordList() {
        return keywordList;

    }

    @Override
    public String toString() {
        String string1 = "";
        String string2 = "";
        for (int i = 0; i < productsToBeDisplayed.length; i++) {
            if (i == 0) {
                string1 = string1 + productsToBeDisplayed[0];
            } else {
                string1 = string1 + "," + productsToBeDisplayed[i];
            }
        }
        for (int i = 0; i < keywordList.size(); i++) {
            if (i == 0) {
                string2 = string2 + keywordList.get(i);
            } else {
                string2 = string2 + "," + keywordList.get(i);
            }
        }
        return "Trade name - " + tradeName + "; Address - " + address + "; Phone - " + phone + "; Intended booth area - " + boothArea + "; Products to be displayed - " + string1 + "; Keywords - " + string2;
    }

    @Override
    public Node exportContentToXMLNode() throws ParserConfigurationException {
        Node rootNode = null;

        DocumentBuilderFactory factory
                = DocumentBuilderFactory.newInstance();
        //Create document builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Obtain a new document
        Document document = builder.newDocument();

        //Create root element
        Element elementCandidatura = document.createElement(ROOT_ELEMENT_NAME);

        //Create a sub-element
        Element elementDescription = document.createElement(DESCRIPTION_ELEMENT_NAME);

        //Set the sub-element value
        // elementDescription.setTextContent(getDescription());
        //Add sub-element to root element
        elementCandidatura.appendChild(elementDescription);

        //Create a sub-element
        Element elementKeywords = document.createElement(KEYWORDS_ELEMENT_NAME);
        elementCandidatura.appendChild(elementKeywords);

        //iterate over keywords
        for (String keyword : getKeywordList()) {
            //      Node keywordNode = keyword.exportContentToXMLNode();
            //      elementKeywords.appendChild(document.importNode(keywordNode, true));
        }

        //Add root element to document
        document.appendChild(elementCandidatura);

        //It exports only the element representation to XMÇ, ommiting the XML header
        rootNode = elementCandidatura;

        return rootNode;
    }

    @Override
    public Application importContentFromXMLNode(Node node) throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Create document builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Obtain a new document
        Document document = builder.newDocument();
        document.appendChild(document.importNode(node, true));

        NodeList elementsCandidatura = document.getElementsByTagName(ROOT_ELEMENT_NAME);

        Node elementCandidatura = elementsCandidatura.item(0);

        //Get description
//        this.description = elementCandidatura.getFirstChild().getFirstChild().getNodeValue();
        NodeList elementsKeywords = document.getElementsByTagName(KEYWORDS_ELEMENT_NAME);

        NodeList keywords = elementsKeywords.item(0).getChildNodes();
        for (int position = 0; position < keywords.getLength(); position++) {
            Node keyword = keywords.item(position);
            Keyword keywordExample = new Keyword();

            keywordExample = keywordExample.importContentFromXMLNode(keyword);
            //  addKeyword(keywordExample);
        }

        return this;
    }

    public boolean compareProducts(Application application) {
        String[] toCompare = application.getProductsToBeDisplayed();
        for (int i = 0; i < productsToBeDisplayed.length; i++) {
            if (!productsToBeDisplayed[i].equals(toCompare[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean compareKeywordList(Application application) {
        ArrayList<String> toCompare = application.getKeywordList();
        for (int i = 0; i < keywordList.size(); i++) {
            if (!keywordList.get(i).equals(toCompare.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Application)) {
            return false;
        }

        Application that = (Application) o;

        if (getTradeName().equals(that.getTradeName()) && getAddress().equals(that.getAddress()) && String.valueOf(getPhone()).equals(String.valueOf(that.getPhone()))) {
            return true;
        }
        return getKeywordList().equals(that.getKeywordList());

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.tradeName);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + this.phone;
        return hash;
    }

}
