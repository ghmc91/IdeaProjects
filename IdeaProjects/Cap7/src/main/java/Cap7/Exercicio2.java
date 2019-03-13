package Cap7;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Exercicio2 {

    public static void main(String[] args) {

        SentenceModel model = null;

        try {
            InputStream inputStream = new FileInputStream("nlp_models/pt-sent.bin");
            model = new SentenceModel(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (model == null) {
            System.out.println("Modelo não foi carregado");
            return;
        }

        String sentence = "Novo reforço do Flamengo, Gabriel Barbosa já está no Rio de Janeiro. " +
                "O atacante desembarcou na manhã desta quarta-feira na cidade para realizar exames e assinar contrato até dezembro. " +
                "Ao lado dos pais, ele foi recebido por cerca de 20 torcedores, aos gritos de 'ão, ão, ão, Gabigol é do Mengão'.";

        SentenceDetectorME detector = new SentenceDetectorME(model);
        Span[] spans = detector.sentPosDetect(sentence);

        for (Span span : spans){
            System.out.println(span);
        }

        int i = 0;
        for (Span span : spans){
            System.out.println("Sentença " + (++i) + " vai de " + span.getStart() +
            " até " + span.getEnd() +
            " com " + span.length() + " caracteres");
        }

    }
}
