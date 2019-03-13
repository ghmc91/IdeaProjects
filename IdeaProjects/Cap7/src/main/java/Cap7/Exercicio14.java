package Cap7;

import opennlp.tools.doccat.*;
import opennlp.tools.util.*;

import java.io.*;

public class Exercicio14 {

    public static void main(String[] args) {

        try {
            InputStreamFactory factory = new MarkableFileInputStreamFactory(new File("nlp_files/tweets-train.txt"));
            ObjectStream<String> lineStream = new PlainTextByLineStream(factory, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

            InputStream is = new FileInputStream("nlp_files/en-tweets-sentiments.bin");
            DoccatModel model = new DoccatModel(is);
            DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);

            DocumentCategorizerEvaluator evaluator = new DocumentCategorizerEvaluator(myCategorizer);
            evaluator.evaluate(sampleStream);
            System.out.println("Acurácia: " + evaluator.getAccuracy());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
