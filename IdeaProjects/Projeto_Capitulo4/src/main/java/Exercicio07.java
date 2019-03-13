import org.apache.log4j.BasicConfigurator;
import smile.data.*;
import smile.data.parser.DelimitedTextParser;
import smile.regression.OLS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;

/**
 * Created by suemareverton on 23/08/17.
 */
public class Exercicio07 {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setColumnNames(true);
        parser.setDelimiter(";");
        parser.setCommentStartWith("#");
        parser.setResponseIndex(new NumericAttribute("length"), 2);

        Attribute[] attributes = new Attribute[2];
        attributes[0] =  new NumericAttribute("age");
        attributes[1] =  new NumericAttribute("water_temperature");

        try {
            AttributeDataset ds =
                    parser.parse(
                            attributes,
                            new File("src/main/datasets/peixes.csv")
                    );

            // Agora utilizaremos 2 variáveis preditoras => Regressão múltipla

            // Atribuindo variáveis preditoras
            double[][] X = ds.toArray(new double[ds.size()][]);

            // Atribuindo variável target
            double[] y = ds.toArray(new double[ds.size()]);

            // Criando modelo preditivo
            OLS regr = new OLS.Trainer().train(X, y);

            System.out.println("R2: " + regr.RSquared());

            try{
                FileOutputStream fileOut = new
                        FileOutputStream("src/main/datasets/modelo.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(regr);
                out.close();
                fileOut.close();
                System.out.println("Modelo Serializado");
            }catch (IOException i){
                i.printStackTrace();
            }


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
