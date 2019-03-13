package Cap03_GustavoC;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

public class Exercicio6 {
    public static void main(String[] args) {
        Table<String, String, Integer> tabela = TreeBasedTable.create();
        tabela.put("Java Fundamentos", "Créditos", 15);
        tabela.put("Java Fundamentos", "Valor", 1000);

        tabela.put("AP", "Créditos", 25);
        tabela.put("AP", "Créditos", 2000);

        tabela.put("PF", "Créditos", 35);
        tabela.put("PF", "Valor", 3000);

        System.out.println(String.valueOf(tabela.containsColumn("Valor")));
        System.out.println(String.valueOf(tabela.containsRow("Java Fundamentos")));
        System.out.println(tabela.get("PF", "Créditos"));


    }
}
