package Cap03_GustavoC;

import Models.Curso;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

/**
 * Mapas bidirecionais
 */

public class Exercicio4 {

    public static void main(String[] args) {


        BidiMap<Integer, Curso> catalogo = new TreeBidiMap<Integer, Curso>();
        catalogo.put(3, new Curso("JF", 100));
        catalogo.put(2, new Curso("JML", 200));
        catalogo.put(1, new Curso("JDL", 300));

        Curso curso2 = catalogo.get(2);
        System.out.println("Curso 2:" + curso2.getNome());

        System.out.printf(String.valueOf(catalogo.inverseBidiMap().get(curso2)));

    }
}

