package Cap5;

import org.apache.log4j.BasicConfigurator;
import smile.classification.LogisticRegression;
import smile.data.AttributeDataset;
import smile.data.parser.ArffParser;

import java.io.IOException;
import java.text.ParseException;

public class Exercicio3 {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        ArffParser parser = new ArffParser();
        parser.setResponseIndex(8);

        AttributeDataset ds = null;

        try{
            ds = parser.parse("src/main/datasets/diabetes.arff");
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }

        if(ds==null){
            System.out.println("Erro");
            return;
        }

        double[][] x = ds.toArray(new double[ds.size()][]);

        int[] y = ds.toArray(new int[ds.size()]);

        System.out.println(x[0][0]);
        System.out.println(y[0]);

        LogisticRegression logR = new LogisticRegression.Trainer().train(x,y);

        int temDiabetes = logR.predict(x[0]);

        System.out.println("Tem diabetes? " + temDiabetes);
    }
}
