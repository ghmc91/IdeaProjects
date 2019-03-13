package Cap6;

import weka.clusterers.SimpleKMeans;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.Arrays;

public class Exercicio3 {

    public static void main(String[] args) {

        try {
            DataSource source = new DataSource("src/main/datasets/iris.arff");
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


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
