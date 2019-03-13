package Cap6;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.util.Arrays;


public class Exercicio5 {

    public static void main(String[] args) {
        try {
            DataSource source = new DataSource("src/main/datasets/iris.arff");
            Instances data = source.getDataSet();

            Remove filter = new Remove();

            filter.setAttributeIndicesArray(new int[]{4});
            filter.setInputFormat(data);
            Instances dataClusterer = Filter.useFilter(data, filter);

            SimpleKMeans model = new SimpleKMeans();
            model.setNumClusters(3);
            model.setPreserveInstancesOrder(true);

            model.buildClusterer(dataClusterer);

            System.out.println("Avaliação do modelo");
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(model);
            data.setClassIndex(4);
            eval.evaluateClusterer(data);
            System.out.println(eval.clusterResultsToString());
            System.out.println(Arrays.toString(model.getAssignments()));
            System.out.println("FIM");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}