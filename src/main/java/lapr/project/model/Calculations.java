/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author LAPR2-G54
 */
public class Calculations {

    public Calculations() {
    }

    public Object[][] getEventStandsFrequencyTable(double[] areas) {
        for (int j = 0; j < areas.length; j++) {
            for (int a = j + 1; a < areas.length; a++) {
                if (areas[j] > areas[a]) {
                    double trade = areas[j];
                    areas[j] = areas[a];
                    areas[a] = trade;
                }
            }
        }
        double areaMin = areas[0];
        double areaMax = areas[areas.length - 1];
        double range = areaMax - areaMin;
        double nClass = 1.0 + Math.round(3.322 * Math.log10(areas.length));
        double intervalAmplitude = Math.round((range / nClass) * 10.00) / 10.00;
        Object[][] frequencyTableData = new Object[(int) nClass][3];
        double areaLow = Math.round((areas[0]) * 100.00) / 100.00;
        for (int d = 0; d < nClass; d++) {
            double areaHigh = Math.round((areaLow + intervalAmplitude) * 100.00) / 100.00;
            if (areaHigh >= areas[areas.length - 1]) {
                frequencyTableData[d][0] = ("[" + areaLow + " ; " + areas[areas.length - 1] + "]");
            } else {
                frequencyTableData[d][0] = ("[" + areaLow + " ; " + areaHigh + "[");
            }
            frequencyTableData[d][1] = getStandsAbsoluteFrequency(areas, areaLow, areaHigh);
            areaLow = areaHigh;
        }
        return calculateStandsRelativeFrequency(frequencyTableData, areas);
    }

    private Object getStandsAbsoluteFrequency(double[] areas, double min, double max) {
        int counter = 0;
        for (int i = 0; i < areas.length; i++) {
            if (areas[i] >= min && areas[i] <= max) {
                counter++;
            }
        }
        return counter;
    }

    private Object[][] calculateStandsRelativeFrequency(Object[][] frequencyTableData, double[] areas) {
        double total = areas.length;
        for (int i = 0; i < frequencyTableData.length; i++) {
            double absoluteFrequency = (int) frequencyTableData[i][1];
            frequencyTableData[i][2] = (double) Math.round(((absoluteFrequency / total) * 1000.00) / 10.00);
        }
        return frequencyTableData;
    }

    public double getMeanRate(double[] eventStandsAreas) {
        double totalValue = 0;
        for (int i = 0; i < eventStandsAreas.length; i++) {
            totalValue = eventStandsAreas[i] + totalValue;
        }
        double meanRate = totalValue / (double) eventStandsAreas.length;
        return Math.round(meanRate * 100.00) / 100.00;
    }

    public double getStandardDeviation(double[] eventStandsAreas) {
        double meanRate = getMeanRate(eventStandsAreas);
        double variance = getVariance(eventStandsAreas, meanRate);
        return Math.round(Math.sqrt(variance) * 100.00) / 100.00;
    }

    public double getVariance(double[] eventStandsAreas, double meanRate) {
        double variance = 0;
        for (int i = 0; i < eventStandsAreas.length; i++) {
            variance = variance + Math.pow((eventStandsAreas[i] - meanRate), 2);
        }
        variance = variance / (double) eventStandsAreas.length;
        return Math.round(variance * 100.00) / 100.00;
    }

    public Object[][] getEventKeywordsFrequencyTable(List<String> allKeywords) {
        List<String> keywordList = new ArrayList<>();
        for (int i = 0; i < allKeywords.size(); i++) {
            if (keywordNotCopied(keywordList, allKeywords.get(i))) {
                keywordList.add(allKeywords.get(i));
            }
        }
        Object[][] KeywordsFrequencyTable = new Object[keywordList.size()][2];
        for (int i = 0; i < keywordList.size(); i++) {
            KeywordsFrequencyTable[i][0] = keywordList.get(i);
            KeywordsFrequencyTable[i][1] = getFrequency(keywordList.get(i), allKeywords);
        }
        return KeywordsFrequencyTable;
    }

    private boolean keywordNotCopied(List<String> keywordList, String keyword) {
        for (int i = 0; i < keywordList.size(); i++) {
            if (keywordList.get(i).equalsIgnoreCase(keyword)) {
                return false;
            }
        }
        return true;
    }

    public double getFrequency(String element, List<String> allElements) {
        double counter = 0;
        double totalKeywords = allElements.size();
        for (int i = 0; i < allElements.size(); i++) {
            if (element.equalsIgnoreCase(allElements.get(i))) {
                counter++;
            }
        }
        return Math.round((counter / totalKeywords) * 1000.00) / 10.00;

    }

    public double getFaeMeanRate(List<Review> faeReviews) {
        double[] reviewsMean = new double[faeReviews.size()];
        for (int i = 0; i < faeReviews.size(); i++) {
            double[] meanOfAllReviewParameters = new double[4];
            meanOfAllReviewParameters[0] = faeReviews.get(i).getEventAdequacyValue();
            meanOfAllReviewParameters[1] = faeReviews.get(i).getFaeTopicKnowledgeValue();
            meanOfAllReviewParameters[2] = faeReviews.get(i).getInviteAdequacyValue();
            meanOfAllReviewParameters[3] = faeReviews.get(i).getRecomendationValue();
            reviewsMean[i] = getMeanRate(meanOfAllReviewParameters);
        }
        return getMeanRate(reviewsMean);
    }

    public double getFaeMeanStandardDeviationRate(double globalSubmissionMeanRate, List<Review> faeReviews) {
        double[] standardReviewsMean = new double[faeReviews.size()];
        for (int i = 0; i < faeReviews.size(); i++) {
            double[] meanOfAllReviewParameters = new double[4];
            meanOfAllReviewParameters[0] = faeReviews.get(i).getEventAdequacyValue();
            meanOfAllReviewParameters[1] = faeReviews.get(i).getFaeTopicKnowledgeValue();
            meanOfAllReviewParameters[2] = faeReviews.get(i).getInviteAdequacyValue();
            meanOfAllReviewParameters[3] = faeReviews.get(i).getRecomendationValue();
            standardReviewsMean[i] = Math.abs(getMeanRate(meanOfAllReviewParameters) - globalSubmissionMeanRate);
        }
        return getMeanRate(standardReviewsMean);
    }

    public double getFaeMeanStandardDeviation(double globalSubmissionMeanRate, List<Review> faeReviews) {
        double[] standardReviewsMean = new double[faeReviews.size()];
        for (int i = 0; i < faeReviews.size(); i++) {
            double[] meanOfAllReviewParameters = new double[4];
            meanOfAllReviewParameters[0] = faeReviews.get(i).getEventAdequacyValue();
            meanOfAllReviewParameters[1] = faeReviews.get(i).getFaeTopicKnowledgeValue();
            meanOfAllReviewParameters[2] = faeReviews.get(i).getInviteAdequacyValue();
            meanOfAllReviewParameters[3] = faeReviews.get(i).getRecomendationValue();
            standardReviewsMean[i] = Math.abs(getMeanRate(meanOfAllReviewParameters) - globalSubmissionMeanRate);
        }
        double meanRate = getMeanRate(standardReviewsMean);
        double variance = getVariance(standardReviewsMean, meanRate);
        return Math.round(Math.sqrt(variance) * 100.00) / 100.00;
    }

    public double getCriticalValueUnilateralTest(int significance) {
        double alpha = (double) significance / 100.00;
        double critValue;
        NormalDistribution nd = new NormalDistribution(0, 1);
        critValue = nd.inverseCumulativeProbability(1.0 - alpha);
        return critValue;
    }

    public Object getTestStatistic(int sample, int nAccepted) {
        double p0 = 0.5;
        double p, observedValue;
        p = (double) nAccepted / sample;
        observedValue = (p - p0) / Math.sqrt((p0 * (1 - p0)) / (double) sample);
        return observedValue;
    }

    public Object getDiferenceTestStatistic(int sample1, int nAccepted1, int sample2, int nAccepted2) {
        double p1, p2, observedValue;
        p1 = (double) nAccepted1 / sample1;
        p2 = (double) nAccepted2 / sample2;

        observedValue = (p1 - p2) / Math.sqrt(((p1 * (1 - p1)) / (double) sample1) + ((p2 * (1 - p2)) / (double) sample2));
        return observedValue;
    }

    public double getCriticalValueBylateralTest(int significance) {
        double alpha = (double) significance / 100.00;
        double critValue;
        NormalDistribution nd = new NormalDistribution(0, 1);
        critValue = nd.inverseCumulativeProbability(1.0 - alpha / 2.0);
        return critValue;
    }

    public Object getTestStatisticFae(double faeMeanStandardDeviationRate, double faeMeanStandardDeviation, int sample) {
        double p0 = 1.0;
        double observedValue;
        observedValue = (faeMeanStandardDeviationRate - p0) / (faeMeanStandardDeviation / Math.sqrt((double) sample));
        return observedValue;
    }

    public Object getDiferenceTestStatisticFae(int sample1, double faeMeanDeviationRate1, double standardDeviation1, int sample2, double faeMeanDeviationRate2, double standardDeviation2) {
        double observedValue;
        observedValue = (faeMeanDeviationRate1 - faeMeanDeviationRate2) / Math.sqrt((Math.pow(standardDeviation1, 2) / (double) sample1) + (Math.pow(standardDeviation2, 2) / (double) sample2));
        return observedValue;
    }
}
