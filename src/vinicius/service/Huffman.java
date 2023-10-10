package vinicius.service;

import java.util.Arrays;

class HuffmanNode{
    int freq;
    char caracter;
    HuffmanNode esq;
    HuffmanNode dir;
}

public class Huffman {
    public HuffmanNode raiz;
    public String[] codes; // guardar os códigos que serão usados para os caracteres da palavra

    public void buildTree(int n, char[] charArray, int[] freqArray){

        Heap heapMinimo = new Heap();
        HuffmanNode node;

        for(int i=0;i<n;i++){
            node = new HuffmanNode();

            node.caracter = charArray[i];
            node.freq = freqArray[i];

            node.esq = null;
            node.dir = null;

            heapMinimo.inserir(node);
        }

        raiz = null;

        while(heapMinimo.tamanho() > 1){
            HuffmanNode x = heapMinimo.remover();
            HuffmanNode y = heapMinimo.remover();

            HuffmanNode z = new HuffmanNode();

            z.freq = x.freq + y.freq;
            z.caracter = '-';
            z.esq = x;
            z.dir = y;
            raiz = z;

            heapMinimo.inserir(z);
        }

        codes = new String[128]; // Um array para os caracteres ASCII
        buildCodeArray(raiz, "");

    }

    public String compress(String input) {
        char[] charArray = getCharacters(input);
        Arrays.sort(charArray);
        int[] charFrequencies = getFrequencies(input);

        buildTree(charArray.length, charArray, charFrequencies);

        String compressed = "";
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            compressed += codes[character];
        }

        return compressed;
    }

    public String decompress(String compressed) {
        String decompressed = "";
        HuffmanNode current = raiz;
        int currentIndex = 0;

        while (currentIndex < compressed.length()) {
            char bit = compressed.charAt(currentIndex);

            if (bit == '0') {
                current = current.esq;
            } else if (bit == '1') {
                current = current.dir;
            }

            if (current.esq == null && current.dir == null) {
                decompressed += current.caracter;
                current = raiz;
            }

            currentIndex++;
        }

        return decompressed;
    }

    // Método auxiliar para preencher o array de códigos de Huffman
    private void buildCodeArray(HuffmanNode node, String currentCode) {
        if (node == null) {
            return;
        }

        if (node.esq == null && node.dir == null) {
            codes[node.caracter] = currentCode;
        }

        buildCodeArray(node.esq, currentCode + "0");
        buildCodeArray(node.dir, currentCode + "1");
    }

    // Métodos que separam os caracteres e calculam suas frequencias

    public char[] getCharacters(String input){
        boolean[] charExists = new boolean[128]; // Um array para os caracteres ASCII

        StringBuilder uniqueCharsBuilder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (!charExists[currentChar]) {
                charExists[currentChar] = true;
                uniqueCharsBuilder.append(currentChar);
            }
        }

        char[] uniqueChars = new char[uniqueCharsBuilder.length()];
        uniqueCharsBuilder.getChars(0, uniqueCharsBuilder.length(), uniqueChars, 0);

        return uniqueChars;
    }

    public int[] getFrequencies(String input){
        int[] charFrequencies = new int[128]; // Um array para todos os caracteres ASCII (0-127)

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar >= 0 && currentChar <= 127) {
                if (Character.isLetterOrDigit(currentChar) || Character.isWhitespace(currentChar)) {
                    charFrequencies[currentChar]++;
                }
                else if(currentChar == '/' || currentChar == '.' || currentChar == '-' || currentChar == '@'){
                    charFrequencies[currentChar]++;
                }
            }
        }

        charFrequencies = removeZeros(charFrequencies);
        return charFrequencies;
    }

    public int[] removeZeros(int[] array) {
        int nonZeroCount = 0;
        for (int value : array) {
            if (value != 0) {
                nonZeroCount++;
            }
        }

        int[] nonZeroArray = new int[nonZeroCount];
        int index = 0;
        for (int value : array) {
            if (value != 0) {
                nonZeroArray[index++] = value;
            }
        }

        return nonZeroArray;
    }
}
