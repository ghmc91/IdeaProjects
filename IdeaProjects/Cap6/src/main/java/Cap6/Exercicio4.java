package Cap6;

import weka.clusterers.SimpleKMeans;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.util.Arrays;

public class Exercicio4 {

    public static void main(String[] args) {

        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource("src/main/datasets/iris.arff");
            Instances data = source.getDataSet();

            /*
            data.deleteAttributeAt(0);
            data.deleteAttributeAt(1);
            data.deleteAttributeAt(4);
            */

            data.deleteAttributeAt(data.attribute("sepallength").index());
            data.deleteAttributeAt(data.attribute("sepalwidth").index());
            data.deleteAttributeAt(data.attribute("class").index());

            SimpleKMeans model = new SimpleKMeans();
            model.setNumClusters(3);
            model.buildClusterer(data);
            System.out.println(model);

            Instance inst = new DenseInstance(2);
            inst.setValue(0, 1.4);
            inst.setValue(1, 0.2);

            int cluster = model.clusterInstance(inst);
            System.out.println("Instância 1 pertence a qual classifcação? " + cluster);

            Instance inst2 = new DenseInstance(2);
            inst.setValue(0, 5.1);
            inst.setValue(1, 1.8);
            System.out.println("Instância 2 pertence a qual classificação? " + model.clusterInstance(inst2));

            double[] probabilidades = model.distributionForInstance(inst2);
            System.out.println("Probabilidades: " + Arrays.toString(probabilidades));

            System.out.println(Arrays.toString(model.getClusterSizes()));

            Instances instances = model.getClusterCentroids();
            System.out.println("Centróides: " + instances);

            int i = 0;
            for (Instance instance : instances){
                System.out.println("------");
                System.out.println("Cluster: " + i++);
                System.out.println("Posição: " + instance.value(0) + " ," + instance.value(1));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
