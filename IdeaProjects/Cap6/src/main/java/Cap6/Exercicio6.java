package Cap6;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.PrincipalComponents;
import weka.filters.unsupervised.attribute.Remove;

public class Exercicio6 {

    public static void main(String[] args) {

        try {

            DataSource source = new DataSource("src/main/datasets/iris.arff");
            Instances data = source.getDataSet();

            Remove filter = new Remove();

            filter.setAttributeIndicesArray(new int[]{4});
            filter.setInputFormat(data);
            Instances dataClusterer = Filter.useFilter(data, filter);

            PrincipalComponents pca = new PrincipalComponents();

            pca.setInputFormat(dataClusterer);

            pca.setMaximumAttributes(2);

            Instances dataPrincipalComponents = Filter.useFilter(dataClusterer, pca);

            dataPrincipalComponents.renameAttribute(0, "pc1");
            dataPrincipalComponents.renameAttribute(1, "pc2");

            SimpleKMeans model = new SimpleKMeans();
            model.setNumClusters(3);
            model.setPreserveInstancesOrder(true);

            model.buildClusterer(dataPrincipalComponents);
            System.out.println("Avaliando o modelo");
            ClusterEvaluation eval = new ClusterEvaluation();

            Instances dataEvaluation = source.getStructure();

            dataEvaluation.deleteAttributeAt(dataEvaluation.attribute("sepallength").index());
            dataEvaluation.deleteAttributeAt(dataEvaluation.attribute("sepalwidth").index());
            dataEvaluation.deleteAttributeAt(dataEvaluation.attribute("petallength").index());
            dataEvaluation.deleteAttributeAt(dataEvaluation.attribute("petalwidth").index());

            System.out.println("Número de atributos: " + dataEvaluation.numAttributes());

            for (int i = 0; i < dataEvaluation.numAttributes(); i++) {
                System.out.println("Atributo " + i + ": " + dataEvaluation.attribute(i).name());
            }
            Attribute pc1Attribute = dataPrincipalComponents.attribute(0);
            Attribute pc2Attribute = dataPrincipalComponents.attribute(1);

            dataEvaluation.insertAttributeAt(pc1Attribute, 0);
            dataEvaluation.insertAttributeAt(pc2Attribute, 1);

            System.out.println("Número de atributos: " + dataEvaluation.numAttributes());

            for (int i = 0; i < dataEvaluation.numAttributes(); i++) {
                System.out.println("Atributo " + i + ": " + dataEvaluation.attribute(i).name());
            }
            dataEvaluation.setClassIndex(2);
            for (int i = 0; i < dataPrincipalComponents.numInstances(); i++) {
                Instance instance = new DenseInstance(3);
                instance.setValue(0, dataPrincipalComponents.get(i).value(0));
                instance.setValue(1, dataPrincipalComponents.get(i).value(1));
                instance.setValue(2, data.get(i).value(4));

                dataEvaluation.add(instance);
            }

            eval.setClusterer(model);
            eval.evaluateClusterer(dataEvaluation);
            System.out.println(eval.clusterResultsToString());
            System.out.println("Fim");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
