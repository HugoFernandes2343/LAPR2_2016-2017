/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Random;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Hugo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Encryption {

    @XmlElement
    private String keyword;
    @XmlElementWrapper(name="decryptKey")
    @XmlElement(name="elements")
    private char[] decryptKey;
    @XmlElement
    private int shift;
    @XmlTransient
    private final char[] characterKey = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        ',', '.', ';', ':', '-', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
        'W', 'X', 'Y', 'Z', ' '};

    public Encryption(String keyword) {
        Random rn = new Random();
        this.keyword = keyword;
        this.shift = rn.nextInt(68);
        this.decryptKey = createDecryptKeyword();
    }

    public Encryption() {
        //Avoiding xml conflicts
    }

    public String encrypt(String word) {
        String substitutionResult = encryptSubstitution(word);
        String transpositionResult = encryptTransposition(substitutionResult);
        return transpositionResult;
    }

    public String decrypt(String word) {
        String decryptTranspositionResult = decryptTransposition(word);
        String decryptSubstitutionResult = decryptSubstitution(decryptTranspositionResult);
        return decryptSubstitutionResult;
    }

    private String encryptSubstitution(String word) {
        char[] wordByChar = word.toCharArray();
        char[] cC = replaceValueEncryption(wordByChar);
        String wordEncrypted = String.valueOf(cC);
        return wordEncrypted;
    }

    private char[] replaceValueEncryption(char[] c) {
        char[] cC = new char[c.length];
        for (int i = 0; i < cC.length; i++) {
            cC[i] = tradeValueEncryption(c[i]);
        }
        return cC;
    }

    private char tradeValueEncryption(char c) {
        String s1 = "" + c;
        int shiftTemp = shift;
        char replacer = 0;
        for (int i = 0; i < characterKey.length; i++) {
            String s2 = "" + characterKey[i];
            if (s1.equals(s2)) {
                if (i + shift >= characterKey.length) {
                    shiftTemp = (i + shift) - characterKey.length;
                    replacer = characterKey[0 + shiftTemp];
                } else {
                    replacer = characterKey[i + shift];

                }

            }

        }
        return replacer;
    }

    private String decryptSubstitution(String word) {
        char[] wordByChar = word.toCharArray();
        char[] cC = replaceValueDecryption(wordByChar);
        String wordEncrypted = String.valueOf(cC);
        return wordEncrypted;
    }

    private char[] replaceValueDecryption(char[] c) {
        char[] cC = new char[c.length];
        for (int i = 0; i < cC.length; i++) {
            cC[i] = tradeValueDecryption(c[i]);
        }
        return cC;
    }

    private char tradeValueDecryption(char c) {
        String s1 = "" + c;
        int shiftTemp = shift;
        char replacer = 0;
        for (int i = 0; i < characterKey.length; i++) {
            String s2 = "" + characterKey[i];
            if (s1.equals(s2)) {
                if (i - shift < 0) {
                    shiftTemp = -(i - shift);
                    replacer = characterKey[characterKey.length - shiftTemp];
                } else {
                    replacer = characterKey[i - shift];

                }

            }

        }
        return replacer;
    }

    private String encryptTransposition(String word) {
        int cont = 0;
        char[] wordByChar = word.toCharArray();
        char[][] cT = new char[20][keyword.length()];  //columnarTransposition
        char[] order = keyword.toCharArray();
        for (int i = 0; i < cT.length; i++) {
            for (int a = 0; a < cT[i].length; a++) {
                if (cont < wordByChar.length) {
                    cT[i][a] = wordByChar[cont];
                    cont++;
                }
            }
        }
        //printMatrix(cT);
        char[][] encrypted = encryptBasedOnKeyword(cT, order);
        String wordEncrypted = passMatrixToString(encrypted);
        return wordEncrypted;
    }

    private char[][] encryptBasedOnKeyword(char[][] cT, char[] order) {
        cT = transposeMatrix(cT);
        for (int j = 0; j < order.length; j++) {
            for (int a = j + 1; a < order.length; a++) {
                if (order[j] > order[a]) {
                    char trade1 = decryptKey[a];
                    decryptKey[a] = decryptKey[j];
                    decryptKey[j] = trade1;
                    char[] trade = cT[j];
                    cT[j] = cT[a];
                    cT[a] = trade;
                }
            }
        }
        cT = transposeMatrix(cT);
        return cT;
    }

    private String decryptTransposition(String word) {
        int cont = 0;
        char[] wordByChar = word.toCharArray();
        char[][] cT = new char[20][decryptKey.length];  //columnarTransposition
        for (int i = 0; i < cT.length; i++) {
            for (int a = 0; a < cT[i].length; a++) {
                if (cont < wordByChar.length) {
                    cT[i][a] = wordByChar[cont];
                    cont++;
                }
            }
        }
        //printMatrix(cT);
        char[][] decrypted = decryptBasedOnDecryptKey(cT);
        String wordDecrypted = passMatrixToString(decrypted);
        return wordDecrypted;
    }

    private char[][] decryptBasedOnDecryptKey(char[][] cT) {
        cT = transposeMatrix(cT);
        for (int j = 0; j < decryptKey.length; j++) {
            for (int a = j + 1; a < decryptKey.length; a++) {
                if (decryptKey[j] > decryptKey[a]) {
                    char trade1 = decryptKey[a];
                    decryptKey[a] = decryptKey[j];
                    decryptKey[j] = trade1;
                    char[] trade = cT[j];
                    cT[j] = cT[a];
                    cT[a] = trade;
                }
            }
        }
        cT = transposeMatrix(cT);
        return cT;
    }

    private char[][] transposeMatrix(char[][] cT) {
        char[][] transposeMatrix = new char[cT[0].length][cT.length];
        char x;
        for (int i = 0; i < cT.length; i++) {
            for (int a = 0; a < cT[i].length; a++) {
                transposeMatrix[a][i] = cT[i][a];
            }
        }
        return transposeMatrix;
    }

    private String passMatrixToString(char[][] cT) {
        String word = "";
        for (int i = 0; i < cT.length; i++) {
            word = word + String.valueOf(cT[i]);
        }
        return word;
    }

    private char[] createDecryptKeyword() {
        int decryptKeyPosition = 0;
        char[] keywordByChar = keyword.toCharArray();
        char[] DecryptKey = new char[keywordByChar.length];
        for (int i = 0; i < DecryptKey.length; i++) {
            String n = Integer.toString(decryptKeyPosition);
            DecryptKey[i] = n.charAt(0);
            decryptKeyPosition++;
        }
        return DecryptKey;
    }
}
