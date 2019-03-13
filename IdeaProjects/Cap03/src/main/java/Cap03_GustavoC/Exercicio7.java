package Cap03_GustavoC;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

public class Exercicio7 {
    public static void main(String[] args) {
        Range<Integer> range1 = Range.closed(0, 9);
        System.out.println("[0,9]: ");
        printRange(range1);
        System.out.println(range1.contains(5));
        System.out.println(range1.containsAll(Ints.asList(5, 6, 7)));
        System.out.println(range1.lowerEndpoint());
        System.out.println(range1.upperEndpoint());

        Range<Integer> range2 = Range.open(0, 9);
        printRange(range2);

        Range<Integer> range3 = Range.openClosed(0, 9);
        printRange(range3);

        Range<Integer> range4 = Range.closedOpen(0, 9);
        printRange(range4);

        //Do ponto indicado ao infinito
        Range<Integer> range5 = Range.greaterThan(9);
        System.out.println(range5.hasUpperBound());


        Range<Integer> range6 = Range.open(3,5);
        System.out.printf(String.valueOf(range1.encloses(range6)));
        System.out.println(range1.intersection(range6));

    }

    private static void printRange(Range<Integer> range){
        System.out.print("[");
        for (int grade : ContiguousSet.create(range, DiscreteDomain.integers())){
            System.out.print(grade + " ");
        }
        System.out.print("]");
    }
}
