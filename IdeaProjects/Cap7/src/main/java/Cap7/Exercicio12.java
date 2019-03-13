package Cap7;

import opennlp.tools.lemmatizer.SimpleLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Exercicio12 {

    public static void main(String[] args) {

        try {
            String[] words = new String[]{
                    "The", "boy's", "cars", "are", "different", "colors"
            };

            InputStream posModelIn = new FileInputStream("nlp_models/en-pos-maxent.bin");
            POSModel posModel = new POSModel(posModelIn);
            POSTaggerME posTagger = new POSTaggerME(posModel);
            String tags[] = posTagger.tag(words);

            InputStream dicLemmatizer = new FileInputStream("nlp_files/en-lemmatizer.dict.txt");
            SimpleLemmatizer lemmatizer = new SimpleLemmatizer(dicLemmatizer);

            System.out.println("Token - Tag - Lemma");
            for (int i=0; i < words.length; i++){
                String token = words[i];
                String tag = tags[i];
                String lemma = lemmatizer.lemmatize(token, tag);
                System.out.println(token + " - " + tag + ": " + lemma);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
