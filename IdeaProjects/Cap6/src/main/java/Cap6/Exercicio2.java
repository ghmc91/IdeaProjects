package Cap6;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class Exercicio2 {

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



            model.setDistanceFunction(new weka.core.EuclideanDistance());
            model.buildClusterer(data);

            System.out.println(model);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
