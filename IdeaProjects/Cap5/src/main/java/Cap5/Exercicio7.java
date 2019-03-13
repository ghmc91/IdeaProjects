package Cap5;

import smile.validation.Accuracy;

public class Exercicio7 {

    public static void main(String[] args) {

        int[] truth = new int[]{0,1,1,1,0,1,1,0,1,0};
        int[] predict = new int[]{0,1,1,1,0,1,1,0,1,1};

        System.out.println("Acurácia: " + new Accuracy().measure(truth,predict));

    }
}
