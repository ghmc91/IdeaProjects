package Cap5;

import org.apache.log4j.BasicConfigurator;
import smile.data.AttributeDataset;
import smile.data.parser.ArffParser;

import java.io.IOException;
import java.text.ParseException;

public class Exercicio1 {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        ArffParser parser = new ArffParser();
        parser.setResponseIndex(4);

        AttributeDataset ds = null;

        try{
        ds = parser.parse("src/main/datasets/weather.arff");
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
    }
}
