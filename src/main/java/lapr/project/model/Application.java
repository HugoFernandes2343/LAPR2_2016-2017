package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAccessorType;
import lapr.project.utils.ApplicationState;

/**
 * Candidatura class.
 *
 * @author by Nuno Bettencourt [nmb@isep.ipp.pt] on 29/05/16.
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
     *
     * @param tradeName
     * @param description
     * @param address
     * @param phone
     * @param boothArea
     * @param productsToBeDisplayed
     * @param numberOfInvitations
     * @param keywords
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
     *
     * @return
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return
     */
    public long getPhone() {
        return phone;
    }

    /**
     *
     * @return
     */
    public double getBoothArea() {
        return boothArea;
    }

    /**
     *
     * @return
     */
    public String[] getProductsToBeDisplayed() {
        return productsToBeDisplayed;
    }

    /**
     *
     * @return
     */
    public int getNumberOfInvitations() {
        return numberOfInvitations;
    }

    public String getDescrition() {
        return description;
    }

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

    public Representative getRepresentative() {
        return representative;
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
        return "Trade name - " + tradeName + "; Description - " + description + "; Address - " + address + "; Phone - " + phone + "; Intended booth area - " + boothArea + "; Products to be displayed - " + string1 + "; Keywords - " + string2;
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
        List<String> toCompare = application.getKeywordList();
        for (int i = 0; i < keywordList.size(); i++) {
            if (!keywordList.get(i).equals(toCompare.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param o
     * @return
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.representative);
        return hash;
    }

    public ApplicationState getApplicationState() {
        return state;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public boolean isFAEReviewing(FAE FAE) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getAssignment().getFAE().equals(FAE)) {
                return true;
            }
        }
        return false;
    }

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

    public void setState(ApplicationState state) {
        this.state = state;
    }

    public boolean getAcceptance() {
        return accepted;
    }

    public ApplicationState getState() {
        return state;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }
}
