package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAccessorType;
import lapr.project.utils.ApplicationState;

/**
 *
 * @author LAPR2-G054
 */
@XmlRootElement(name = "application")
@XmlAccessorType(XmlAccessType.FIELD)
public class Application {

    @XmlElementWrapper(name = "keywords")
    @XmlElement(name = "keyword")
    private List<String> keywordList;

    @XmlElement
    private String description;

    @XmlElement
    private Boolean accepted;

    @XmlElement
    private String tradeName;

    @XmlElement
    private String address;

    @XmlElement
    private long phone;

    @XmlElement
    private double boothArea;

    @XmlElement
    private String[] productsToBeDisplayed;

    @XmlElement
    private ApplicationState state;

    @XmlElement(name = "invitesQuantity")
    private int numberOfInvitations;

    @XmlElementWrapper(name = "reviews")
    @XmlElement(name = "review")
    private List<Review> reviews;

    @XmlElement
    private Stand stand;

    @XmlElement
    private Representative representative;

    /**
     * full constructor of the application class
     *
     * @param tradeName trade name to give to the application
     * @param description description to give to the application
     * @param address address to give to the application
     * @param phone phone number to give to the application
     * @param boothArea intended booth area to give to the application
     * @param productsToBeDisplayed products to be displayed to give to the
     * application
     * @param numberOfInvitations number of invitatios to give to the
     * application
     * @param keywords keywords to give to the application
     */
    public Application(String tradeName, String description, String address, long phone, double boothArea, String[] productsToBeDisplayed, int numberOfInvitations, String[] keywords) {
        this.keywordList = new ArrayList<>();
        this.tradeName = tradeName;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.boothArea = boothArea;
        this.productsToBeDisplayed = productsToBeDisplayed;
        this.numberOfInvitations = numberOfInvitations;
        this.reviews = new ArrayList<>();
        for (int i = 0; i < keywords.length; i++) {
            String keyword = keywords[i];
            keywordList.add(keyword);
        }
    }

    /**
     * constructor for testing
     *
     * @param tradeName trade name to give to the application
     * @param description description to give to the application
     * @param phone phone number to give to the application
     */
    public Application(String tradeName, String description, long phone) {
        this.description = description;
        this.tradeName = tradeName;
        this.phone = phone;
    }

    /**
     * Default public constructor.
     */
    public Application() {
        this.keywordList = new ArrayList<>();

    }

    /**
     * get method of the tradeName attribute
     *
     * @return tradeName
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * get method of the address attribute
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * get method of the phone attribute
     *
     * @return phone
     */
    public long getPhone() {
        return phone;
    }

    /**
     * get method of the boothArea attribute
     *
     * @return boothArea
     */
    public double getBoothArea() {
        return boothArea;
    }

    /**
     * get method of the productsToBeDisplayed attribute
     *
     * @return productsToBeDisplayed
     */
    public String[] getProductsToBeDisplayed() {
        return productsToBeDisplayed;
    }

    /**
     * get method of the numberOfInvitations attribute
     *
     * @return numberOfInvitations
     */
    public int getNumberOfInvitations() {
        return numberOfInvitations;
    }

    /**
     * get method of the description attribute
     *
     * @return description
     */
    public String getDescrition() {
        return description;
    }

    /**
     * get method of the stand attribute
     *
     * @return stand
     */
    public Stand getStand() {
        return stand;
    }

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
    public List<String> getKeywordList() {
        return keywordList;

    }

    /**
     * get method of the representative attribute
     *
     * @return representative
     */
    public Representative getRepresentative() {
        return representative;
    }

    /**
     * toString override of the class application
     *
     * @return string with the information of the application
     */
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
        return "Trade name - " + tradeName + "; Description - " + description + "; Address - " + address + "; Phone - " + phone + "; Intended booth area - " + boothArea + "; Products to be displayed - " + string1 + "; Keywords - " + string2;
    }

    /**
     * compares to see if there are already applications with those products
     * registerd
     *
     * @param application application from which the products will be compared
     * to al the others
     * @return true if the products are viable false the products are not viable
     */
    public boolean compareProducts(Application application) {
        String[] toCompare = application.getProductsToBeDisplayed();
        for (int i = 0; i < productsToBeDisplayed.length; i++) {
            if (!productsToBeDisplayed[i].equals(toCompare[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * compares keyword list of an application to all existing ones to see if
     * the keywords can be those
     *
     * @param application application from which to compare the keywords
     * @return true if teh keywords are viable false if they are not
     */
    public boolean compareKeywordList(Application application) {
        List<String> toCompare = application.getKeywordList();
        for (int i = 0; i < keywordList.size(); i++) {
            if (!keywordList.get(i).equals(toCompare.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * equals method overrid of the application class
     *
     * @param o object to which compare
     * @return true if the objects are the same false if they are diferent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Application)) {
            return false;
        }

        Application that = (Application) o;

        return (getRepresentative().getUser().equals(that.getRepresentative().getUser()));
    }

    /**
     * hashCode of the equals from class application
     *
     * @return int hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.representative);
        return hash;
    }

    /**
     * get method of the state attribute
     *
     * @return state
     */
    public ApplicationState getApplicationState() {
        return state;
    }

    /**
     * get method of the reviews attribute
     *
     * @return list of reviews
     */
    public List<Review> getReviews() {
        return this.reviews;
    }

    /**
     * checks if the given FAE is reviewing this application
     *
     * @param FAE user that is using the funcionality
     * @return true if he has can review it false if the FAE cant review the
     * application
     */
    public boolean isFAEReviewing(FAE FAE) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getAssignment().getFAE().equals(FAE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * registers the FAEs review of the application
     *
     * @param FAE user that reviews the application
     * @param faeTopicKnowledge classification inputed by the FAE on a 0-5 scale
     * based on his knowledge of the topic
     * @param eventAdequacy classification inputed by the FAE on a 0-5 scale
     * based on the adequacy of the application to this event
     * @param inviteAdequacy classification inputed by the FAE on a 0-5 scale on
     * his based on the adequacy of the number of invites
     * @param recomendation classification inputed by the FAE on a 0-5 scale on
     * his based on his recomendation
     * @param justification text justifiying his decision
     */
    public void setFAEReview(FAE FAE, Integer faeTopicKnowledge, Integer eventAdequacy, Integer inviteAdequacy, Integer recomendation, String justification) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getAssignment().getFAE().equals(FAE)) {
                reviews.get(i).setFaeTopicKnowledge(faeTopicKnowledge);
                reviews.get(i).setEventAdequacy(eventAdequacy);
                reviews.get(i).setInviteAdequacy(inviteAdequacy);
                reviews.get(i).setRecommendation(recomendation);
                reviews.get(i).setJustification(justification);
            }
        }
    }

    /**
     * set method of the state attribute
     *
     * @param state to set as state
     */
    public void setState(ApplicationState state) {
        this.state = state;
    }

    /**
     * get method of the accepted attribute
     *
     * @return accepted
     */
    public boolean getAcceptance() {
        return accepted;
    }

    /**
     * get method of the state attribute
     *
     * @return state
     */
    public ApplicationState getState() {
        return state;
    }

    /**
     * set method of the stand attribute
     *
     * @param stand
     */
    public void setStand(Stand stand) {
        this.stand = stand;
    }
    /**
     * set method of the representative attribute
     *
     * @param representative
     */
    

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }
}
