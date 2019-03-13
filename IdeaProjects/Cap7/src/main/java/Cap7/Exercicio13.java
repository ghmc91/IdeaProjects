package Cap7;

import opennlp.tools.doccat.*;
import opennlp.tools.util.*;

import java.io.*;

public class Exercicio13 {
    public static void main(String[] args) {

        try {

            InputStreamFactory factory = new MarkableFileInputStreamFactory(new File("nlp_files/tweets-train.txt"));
            ObjectStream<String> lineStream = new PlainTextByLineStream(factory, "UTF-8");
            ObjectStream<DocumentSample> sampleStrean = new DocumentSampleStream(lineStream);

            DoccatModel model = DocumentCategorizerME.train("en", sampleStrean,
                    TrainingParameters.defaultParams(), new DoccatFactory());

            System.out.println("Modelo treinado");

            BufferedOutputStream modelOut =
                    new BufferedOutputStream(new FileOutputStream("nlp_files/en-tweets-sentiments.bin"));

            model.serialize(modelOut);
            System.out.println("Modelo salvo");

            DocumentCategorizer doccat = new DocumentCategorizerME(model);
            double[] aProbs = doccat.categorize("I don't love you");

            for (int i = 0; i < doccat.getNumberOfCategories(); i++) {
                System.out.println(doccat.getCategory(i) + ": " + aProbs[i]);
            }
            System.out.println("Resultado: " + doccat.getBestCategory(aProbs));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}