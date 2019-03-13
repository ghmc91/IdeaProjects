package ExemploRNN;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration.ListBuilder;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.GravesLSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.indexaccum.IMax;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

/**
 * Treinando uma RNN. Após o treino, informar o primeiro caracter de uma frase e
 * ela conseguirá prever a frase inteira
 */
public class ExemploRNN {
    //Frase que a rede vai aprender
    private static final char[] FRASE = "*A vingança nunca é plena mata a alma e envenena - Madruga, seu".toCharArray();

    //Lista com todos os caracteres possíveis
    private static final List<Character> LISTA_CARACTER_FRASE = new ArrayList<>();

    //Dimensões da RNN
    private static final int CAMADAS_OCULTAS_WIDTH = 50;
    private static final int CAMADAS_OCULTAS_CONT = 2;

    public static void main(String[] args) {

    }
}
