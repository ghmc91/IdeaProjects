package Cap03_GustavoC;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class Exercicio1 {
    public static void main(String[] args) {
        Bag bag = new HashBag();

        // 8 ocorrências to termo machine learning
        bag.add("Machine Learning", 8);

        // 2 ocorrências do termo Big Data
        bag.add("Big Data", 2);

        bag.remove("Machine Learning", 3);

        int totalOcorrencias = bag.getCount("Machine Learning");
        System.out.println(totalOcorrencias + " afinal, 8 - 3 = 5");


    }
}
