package Cap7;

import opennlp.tools.stemmer.PorterStemmer;

public class Exercicio11 {

    public static void main(String[] args) {

        String[] words = new String[]{
                "The", "boy's", "cars", "are", "different", "colors"
        };

        PorterStemmer stemmer = new PorterStemmer();

        System.out.println("Word - Stem");
        for (String word : words){
            String stem = stemmer.stem(word);
            System.out.println(word + " - " + stem);
        }
    }
}
