package exemplo;

import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.ArrayList;
import java.util.Arrays;

public class Add extends No {

    public Add(String nome, No x, No y) {
        super(nome, new ArrayList<No>(Arrays.asList(x,y)));
    }

    public void foward() {
        INDArray valor_x = this.nos_entrada.get(0).valor;
        INDArray valor_y = this.nos_entrada.get(1).valor;
        this.valor = valor_x.add(valor_y);
    }

    public void backward() {

    }
}
