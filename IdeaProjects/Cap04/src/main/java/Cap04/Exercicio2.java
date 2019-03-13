package Cap04;

import org.apache.log4j.BasicConfigurator;
import smile.data.AttributeDataset;
import smile.data.NumericAttribute;
import smile.data.parser.DelimitedTextParser;
import smile.regression.OLS;

import java.io.IOException;
import java.text.ParseException;

public class Exercicio2 {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setCommentStartWith("#");
        parser.setColumnNames(true);
        parser.setDelimiter(";");
        parser.setResponseIndex(new NumericAttribute("valor"),1);
        try {
            AttributeDataset ds = parser.parse("src/main/dataset/AutoInsuranceSweden.txt");
            double[][] x = ds.toArray(new double[ds.size()][]);
            double[] y = ds.toArray(new double[ds.size()]);
            System.out.printf(String.valueOf(x[0][0]));
            System.out.printf(String.valueOf(y[0]));

            OLS regr = new OLS.Trainer().train(x,y);

            double y_pred = regr.predict(new double[]{18});

            System.out.printf("Valor previsto para 18 solicitações: " + y_pred);
        }catch (ParseException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
