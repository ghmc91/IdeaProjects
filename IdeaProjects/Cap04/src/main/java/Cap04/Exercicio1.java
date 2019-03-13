package Cap04;

import org.apache.log4j.BasicConfigurator;
import smile.regression.OLS;

public class Exercicio1 {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        double[][] x = new double[][]{{2},{3},{4},{5},{6},{7}};
        double[] y = new double[]{800, 1100, 1400, 1500, 1570,1700};
        //OLS regr = new OLS(x,y);
        OLS regr = new OLS.Trainer().train(x,y);
        double y_pred = regr.predict(new double[]{3});
        System.out.println("Valor previsto: " + y_pred);

    }
}
