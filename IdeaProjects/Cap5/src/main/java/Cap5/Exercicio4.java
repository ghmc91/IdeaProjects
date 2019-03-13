package Cap5;

import org.apache.log4j.BasicConfigurator;
import smile.classification.LogisticRegression;
import smile.data.AttributeDataset;
import smile.data.parser.ArffParser;
import smile.math.Math;

import java.io.IOException;
import java.text.ParseException;

public class Exercicio4 {

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

        int[] indexes = Math.permutate(ds.size());
        int trainSize = (int) (ds.size()*0.7);
        int testSize = ds.size() - trainSize;

        double[][] x_train = new double[trainSize][];
        double[][] x_test = new double[testSize][];
        int[] y_train = new int[trainSize];
        int[] y_test = new int[testSize];

        int j = -1;

        for(int i=0; i < trainSize; i++){
            j++;
            x_train[j] = x[indexes[i]];
            y_train[j] = y[indexes[i]];
        }

        j=-1;
        for(int i = trainSize; i<ds.size();i++){
            j++;
            x_test[j] = x[indexes[i]];
            y_test[j] = y[indexes[i]];
        }

        LogisticRegression logR =
                new LogisticRegression.Trainer().train(x_train,y_train);

        int temDiabetes = logR.predict(x[0]);

        System.out.println("Tem diabetes? " + temDiabetes);




    }
}
