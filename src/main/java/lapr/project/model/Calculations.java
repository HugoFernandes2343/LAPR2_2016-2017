/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class Calculations {

    public static Object[][] getEventStandsFrequencyTable(double[] areas) {
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
        double nClass = 1 + Math.round(3.322 * Math.log10(areas.length));
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

    private static Object getStandsAbsoluteFrequency(double[] areas, double min, double max) {
        int counter = 0;
        for (int i = 0; i < areas.length; i++) {
            if (areas[i] >= min && areas[i] <= max) {
                counter++;
            }
        }
        return counter;
    }

    private static Object[][] calculateStandsRelativeFrequency(Object[][] frequencyTableData, double[] areas) {
        double total = areas.length;
        for (int i = 0; i < frequencyTableData.length; i++) {
            double absoluteFrequency = (int) frequencyTableData[i][1];
            frequencyTableData[i][2] = (double) Math.round(((absoluteFrequency / total) * 1000.00) / 10.00);
        }
        return frequencyTableData;
    }

    public static double getMeanRate(double[] eventStandsAreas) {
        double totalValue = 0;
        for (int i = 0; i < eventStandsAreas.length; i++) {
            totalValue = eventStandsAreas[i] + totalValue;
        }
        double meanRate = totalValue / (double) eventStandsAreas.length;
        return Math.round(meanRate * 100.00) / 100.00;
    }

    public static double getStandardDeviation(double[] eventStandsAreas) {
        double meanRate = getMeanRate(eventStandsAreas);
        double variance = getVariance(eventStandsAreas, meanRate);
        return Math.round(Math.sqrt(variance) * 100.00) / 100.00;
    }

    public static double getVariance(double[] eventStandsAreas, double meanRate) {
        double variance = 0;
        for (int i = 0; i < eventStandsAreas.length; i++) {
            variance = variance + Math.pow((eventStandsAreas[i] - meanRate), 2);
        }
        variance = variance / (double) eventStandsAreas.length;
        return Math.round(variance * 100.00) / 100.00;
    }

    public static Object[][] getEventKeywordsFrequencyTable(List<String> allKeywords) {
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

    private static boolean keywordNotCopied(List<String> keywordList, String keyword) {
        for (int i = 0; i < keywordList.size(); i++) {
            if (keywordList.get(i).equalsIgnoreCase(keyword)) {
                return false;
            }
        }
        return true;
    }

    public static double getFrequency(String element, List<String> allElements) {
        double counter = 0;
        double totalKeywords = allElements.size();
        for (int i = 0; i < allElements.size(); i++) {
            if (element.equalsIgnoreCase(allElements.get(i))) {
                counter++;
            }
        }
        return Math.round((counter / totalKeywords) * 1000.00) / 10.00;

    }

    public static double getFaeMeanRate(List<Review> faeReviews) {
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

}
