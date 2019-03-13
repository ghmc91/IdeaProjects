package Cap5;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Exercicio10 {

    public static void main(String[] args) {
        NaiveBayes nb = null;
        DataSource source = null;
        Instances dataset = null;
        try {
            source = new DataSource("src/main/datasets/iris.arff");
            dataset = source.getDataSet();

            dataset.setClassIndex(dataset.numAttributes()-1);

            nb = new NaiveBayes();
            nb.buildClassifier(dataset);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro: " + e.getMessage());
        }

        if(nb==null){
            return;
        }

        Instance inst = new DenseInstance(4);
        inst.setValue(0,5.1);
        inst.setValue(1,3.5);
        inst.setValue(2,1.4);
        inst.setValue(3,0.2);
        inst.setDataset(dataset);

        System.out.println("Instância recém criada: " + inst);

        try{
            double result = nb.classifyInstance(inst);
            System.out.println("Resultado: " + result);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
