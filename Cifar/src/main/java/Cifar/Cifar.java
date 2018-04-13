package Cifar;

import org.apache.commons.io.FilenameUtils;
import org.datavec.image.loader.CifarLoader;
import org.deeplearning4j.datasets.iterator.impl.CifarDataSetIterator;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.buffer.util.DataTypeUtil;
import

/**
 * Hello world!
 *
 */
public class Cifar
{

    private static final String DATA_PATH = FilenameUtils.concat(System.getProperty("user.dir"), "/");

    private static String labelStr = "[]";
    private static int altura = 32;
    private static int largura =32;
    private static int canais = 3;
    private static int nClasses = CifarLoader.NUM_LABELS;
    private static int nExemplos = 50000;
    private static int batchSize = 100;
    private static int iterations = 1;
    private static int seed = 123;
    private static boolean preProcessCifar = false;
    private static int epochs = 1;

    public static void main( String[] args )
    {
        DataTypeUtil.setDTypeForContext(DataBuffer.Type.FLOAT);
        MultiLayerNetwork modelo = criarRede();

        CifarDataSetIterator cifar


    }
}
