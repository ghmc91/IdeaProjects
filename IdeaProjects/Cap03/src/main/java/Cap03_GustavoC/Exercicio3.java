package Cap03_GustavoC;

import Models.Curso;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;

public class Exercicio3 {
    public static void main(String[] args) {

        OrderedMap<Integer, Curso> catalogo = new LinkedMap<Integer, Curso>();
        catalogo.put(3, new Curso("JF", 100));
        catalogo.put(2, new Curso("JML", 200));
        catalogo.put(1, new Curso("JDL", 300));

        System.out.println("1º: " + catalogo.firstKey());
        System.out.println("1º curso: " + catalogo.get(catalogo.firstKey()).getNome());
        System.out.println(catalogo.lastKey());
        System.out.println(catalogo.nextKey(catalogo.firstKey()));
    }
}
