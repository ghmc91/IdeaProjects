package LenetMnist;

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.LearningRatePolicy;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LenetMnist {

    public static void main(String[] args) throws IOException {

        int nChannels = 1; //número de canais de cores
        int outputNum = 10; // númeor de saídas possíveis
        int batchSize = 64; // Tamanho do batch
        int nEpochs = 1; // Número de epochs
        int iterations = 1; // número de iterações
        int seed = 123; // Seed

        System.out.println("Carregando os dados");
        DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize, true, 12245);
        DataSetIterator mnistTeste = new MnistDataSetIterator(batchSize, false, 12345);

        System.out.println("Construindo o modelo");

        // Definindo as taxas de aprendizagem que vão variar conforme o tempo
        Map<Integer, Double> lrSchedule = new HashMap<>();
        lrSchedule.put(0, 0.1);
        lrSchedule.put(1000, 0.005);
        lrSchedule.put(3000, 0.001);

        // Configurando a rede
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .iterations(iterations)
                .regularization(true).l2(0.0005)
                .learningRate(.01)
                .learningRateDecayPolicy(LearningRatePolicy.Schedule)
                .learningRateSchedule(lrSchedule)
                .weightInit(WeightInit.XAVIER)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(Updater.NESTEROVS)
                .list()
                .layer(0, new ConvolutionLayer.Builder(5, 5)
                        .nIn(nChannels)
                        .stride(1, 1)
                        .nOut(20)
                        .activation(Activation.IDENTITY)
                        .build()
                )
                .layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2, 2)
                        .stride(2, 2)
                        .build()
                )
                .layer(2, new ConvolutionLayer.Builder(5, 5)
                        .stride(1, 1)
                        .nOut(50)
                        .activation(Activation.IDENTITY)
                        .build()
                )
                .layer(3, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2, 2)
                        .stride(2, 2)
                        .build()
                )
                .layer(4, new DenseLayer.Builder().activation(Activation.RELU)
                        .nOut(500).build())
                .layer(5, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nOut(outputNum)
                        .activation(Activation.SOFTMAX)
                        .build())
                .setInputType(InputType.convolutional(28, 28, 1))
                .backprop(true).pretrain(false).build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        System.out.println("Treinando o modelo....");


        for (int i = 0; i < nEpochs; i++) {
            model.fit(mnistTrain);
            System.out.println("****Epoch " + i + " ****");

            System.out.println("Avaliando o modelo...");
            Evaluation eval = model.evaluate(mnistTeste);
            System.out.println(eval.stats());
            mnistTeste.reset();
        }

        System.out.println("Fim do exemplo");


    }
}
