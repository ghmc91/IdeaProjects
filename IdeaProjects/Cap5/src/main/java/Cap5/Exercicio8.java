package Cap5;

import smile.validation.CrossValidation;

public class Exercicio8 {

    public static void main(String[] args) {

        CrossValidation cv = new CrossValidation(100,10);
        int[][] train = cv.train;
        int[][] test = cv.test;


    }
}
