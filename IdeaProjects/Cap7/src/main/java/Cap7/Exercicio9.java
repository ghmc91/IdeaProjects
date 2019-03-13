package Cap7;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Exercicio9 {

    public static void main(String[] args) {

        String text = "Salt Lake City is the capital and the most populous municipality of the U.S. state of Utah.";

        TokenizerModel tokenizerModel = null;

        try {
            InputStream inputStreamTokenizer = new FileInputStream("nlp_models/en-token.bin");
            tokenizerModel = new TokenizerModel(inputStreamTokenizer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tokenizerModel == null) {
            System.out.println("Impossível carregar o modelo de tokenização");
            return;
        }

        TokenizerME tokenizer = new TokenizerME(tokenizerModel);

        String tokens[] = tokenizer.tokenize(text);
        TokenNameFinderModel tokenNameFinderModel = null;
        try {
            InputStream inputStreamFinder = new FileInputStream("nlp_models/en-ner-location.bin");
            tokenNameFinderModel = new TokenNameFinderModel(inputStreamFinder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tokenNameFinderModel == null) {
            System.out.println("Não foi possível carregar o modelo de reconhecimento de entidades");
            return;
        }

        NameFinderME nameFinder = new NameFinderME(tokenNameFinderModel);

        Span nameSpans[] = nameFinder.find(tokens);

        for (Span nameSpan : nameSpans) {
            String location = "";
            for (int i = nameSpan.getStart(); i < nameSpan.getEnd(); i++)
                location += tokens[i] + " ";
                System.out.println(nameSpan.toString().trim() + " - " + location);

        }


    }
}
