package PerceptronDuasCamadas;

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.LossFunction;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class PerceptronDuasCamadas
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        //Número de linhas e colunas da primeira camada
        final int nLinhas = 28;
        final int nColunas = 28;
        int nSaida = 10; // Número de saída
        int batchSize = 64; // Tamanho do lote
        int seed = 123; // Grarantir a produtividade
        int numEpochs = 15; // Número de epochs
        double taxaAprendizado = 0.0015; // Taxa de aprendizado

        // Dados de treino e de teste
        DataSetIterator mnisTreino = new MnistDataSetIterator(batchSize, true, seed);
        DataSetIterator mnistTeste = new MnistDataSetIterator(batchSize, false, seed);


        System.out.println("Cosntruindo o modelo");
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed) // Incluindo o seed para reprodutibilidade
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT) //Gradiente descendente estocástico como algoritmo de otimização
                .iterations(1)
                .activation(Activation.RELU)
                .weightInit(WeightInit.XAVIER)
                .learningRate(taxaAprendizado)
                .updater(new Nesterovs(0.98))
                .regularization(true).l2(taxaAprendizado*0.005) // regularização
                .list()
                .layer(0, new DenseLayer.Builder()
                .nIn(nLinhas*nColunas)
                .nOut(500)
                .build()
                )
                .layer(1,new DenseLayer.Builder()
                .nIn(500)
                .nOut(100)
                .build()
                )
                .layer(2,new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                .activation(Activation.SOFTMAX)
                .nOut(nSaida)
                .build()
                )
                .pretrain(false).backprop(true)
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        System.out.println("Treinando o modelo");
        for (int i = 0; i < numEpochs; i++){
            System.out.println("Epoch " + i);
            model.fit(mnisTreino);
        }

        System.out.println("Avaliando o modelo");
        Evaluation eval = new Evaluation(nSaida);
        while (mnistTeste.hasNext()){
            DataSet next = mnistTeste.next();
            INDArray output = model.output(next.getFeatureMatrix());
            eval.eval(next.getLabels(),output);
        }

        System.out.println(eval.stats());
        System.out.println("********Fim do exemplo********");

    }
}
