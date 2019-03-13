package Cap5;

import org.apache.log4j.BasicConfigurator;
import smile.classification.LogisticRegression;
import smile.data.AttributeDataset;
import smile.data.parser.ArffParser;
import smile.validation.Accuracy;
import smile.validation.CrossValidation;

import java.io.IOException;
import java.text.ParseException;

public class Exercicio9 {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        ArffParser parser = new ArffParser();
        parser.setResponseIndex(8);

        AttributeDataset ds = null;

        try {
            ds = parser.parse("src/main/datasets/diabetes.arff");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (ds == null) {
            System.out.println("Erro");
            return;
        }

        double[][] x = ds.toArray(new double[ds.size()][]);
        int[] y = ds.toArray(new int[ds.size()]);

        int folds = 10;

        double accuracy_total = 0;


        CrossValidation cv = new CrossValidation(ds.size(), folds);

        for (int i=0; i < folds; i++){
            int nTrainSamples = cv.train[i].length;
            int nTestSamples = cv.test[i].length;

            double[][] x_train = new double[nTrainSamples][];
            double[][] x_test = new double[nTestSamples][];

            int[] y_train = new int[nTrainSamples];
            int[] y_test = new int[nTestSamples];

            for (int j = 0; j<nTrainSamples; j++){
                x_train[j] = x[cv.train[i][j]];
                y_train[j] = y[cv.train[i][j]];
            }
            for (int j = 0; j<nTestSamples; j++){
                x_test[j] = x[cv.test[i][j]];
                y_test[j] = y[cv.test[i][j]];
            }

            LogisticRegression logR =
                new LogisticRegression.Trainer().train(x_train,y_train);

            int[] y_pred_test = new int[nTestSamples];
            for (int j = 0; j < nTestSamples; j++){
                y_pred_test[j] = logR.predict(x_test[j]);
            }
            double accuracy = new Accuracy().measure(y_test, y_pred_test);
            System.out.println("Acurácia do round " + (i+1) + ":" + accuracy);

            accuracy_total += accuracy;
        }

        System.out.println("---------");
        System.out.println("Acurácia média: " + accuracy_total/folds);



    }
}
