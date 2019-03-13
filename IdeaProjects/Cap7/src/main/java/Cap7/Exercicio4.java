package Cap7;

import opennlp.tools.tokenize.SimpleTokenizer;

public class Exercicio4 {

    public static void main(String[] args) {

        String sentence = "Artificial Intelligence (AI) and Machine Learning (ML) are two very hot buzzwords right now, and often seem to be used interchangeably. " +
                "They are not quite the same thing, but the perception that they are can sometimes lead to some confusion. " +
                "So I thought it would be worth writing a piece to explain the difference. " +
                "Both terms crop up very frequently when the topic is Big Data, analytics, and the broader waves of technological change which are sweeping through our world." +
                "In short, the best answer is that: Artificial Intelligence is the broader concept of machines being able to carry out tasks in a way that we would consider “smart.”";

        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

        String tokens[] = simpleTokenizer.tokenize(sentence);

        for (String token : tokens){
            System.out.println(token);
        }

    }
}