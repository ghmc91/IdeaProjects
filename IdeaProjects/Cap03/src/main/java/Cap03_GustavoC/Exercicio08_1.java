package Cap03_GustavoC;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.Random;

public class Exercicio08_1 {

    public static void main(String[] args) {


    DescriptiveStatistics stats = new DescriptiveStatistics();

    Random random = new Random();
    for(int i=0; i < 1000; i++){
        stats.addValue(random.nextInt(100));
    }
        System.out.println("Max:" + stats.getMax());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Média: " + stats.getMean());
        System.out.println("Media: " + stats.getPercentile(50));
        System.out.println("Variancia: " + stats.getVariance());
        System.out.println("Desvio Padrão: " + stats.getStandardDeviation());

}
    }
