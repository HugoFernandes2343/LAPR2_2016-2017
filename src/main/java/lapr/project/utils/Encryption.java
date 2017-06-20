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
    @XmlElementWrapper(name = "decryptKey")
    @XmlElement(name = "elements")
    private char[] decryptKey;
    @XmlElement
    private int shift;
    @XmlTransient
    private final char[] characterKey = getAscii();

    public Encryption(String keyword) {
        Random rn = new Random();
        this.keyword = keyword;
        this.shift = rn.nextInt(95);
        this.decryptKey = createDecryptKeyword();
    }

    public Encryption() {
        //Avoiding xml conflicts
    }

    public String encrypt(String word) {
        String substitutionResult = encryptSubstitution(word);
        return encryptTransposition(substitutionResult);
    }

    public String decrypt(String word) {
        String decryptTranspositionResult = decryptTransposition(word);
        return decryptSubstitution(decryptTranspositionResult);
    }

    private String encryptSubstitution(String word) {
        char[] wordByChar = word.toCharArray();
        char[] cC = replaceValueEncryption(wordByChar);
        return String.valueOf(cC);
    }

    private char[] replaceValueEncryption(char[] c) {
        char[] cC = new char[c.length];
        for (int i = 0; i < cC.length; i++) {
            cC[i] = tradeValueEncryption(c[i]);
        }
        return cC;
    }

    private char tradeValueEncryption(char c) {
        String s1 = Character.toString(c);
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
        return String.valueOf(cC);
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
        char[][] encrypted = encryptBasedOnKeyword(cT, order);
        return passMatrixToString(encrypted);
    }

    private char[][] encryptBasedOnKeyword(char[][] cT, char[] order) {
        cT = transposeMatrix(cT);
        for (int j = 0; j < order.length; j++) {
            for (int a = j + 1; a < order.length; a++) {
                if (order[j] > order[a]) {
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
        char[][] decrypted = decryptBasedOnDecryptKey(cT);
        return passMatrixToString(decrypted);
    }

    private char[][] decryptBasedOnDecryptKey(char[][] cT) {
        cT = transposeMatrix(cT);
        char[] decrypt = new char[decryptKey.length];
        for (int i = 0; i < decrypt.length; i++) {
            decrypt[i] = decryptKey[i];
        }
        for (int j = 0; j < decrypt.length; j++) {
            for (int a = j + 1; a < decrypt.length; a++) {
                if (decrypt[j] > decrypt[a]) {
                    char trade1 = decrypt[a];
                    decrypt[a] = decrypt[j];
                    decrypt[j] = trade1;
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
        char[] decryptKey = new char[keywordByChar.length];
        for (int i = 0; i < decryptKey.length; i++) {
            String n = Integer.toString(decryptKeyPosition);
            decryptKey[i] = n.charAt(0);
            decryptKeyPosition++;
        }
        for (int j = 0; j < keywordByChar.length; j++) {
            for (int a = j + 1; a < keywordByChar.length; a++) {
                if (keywordByChar[j] > keywordByChar[a]) {
                    char trade1 = decryptKey[a];
                    decryptKey[a] = decryptKey[j];
                    decryptKey[j] = trade1;
                }
            }
        }
        return decryptKey;
    }

    private char[] getAscii() {
        char[] ascii = new char[127 - 32];
        for (int i = 32; i < 127; i++) {
            ascii[i - 32] = (char) i;
        }
        return ascii;
    }
}
