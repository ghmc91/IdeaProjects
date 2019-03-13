package Cap03_GustavoC;
import org.jsoup.Jsoup;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Exercicio9 {

    public static void main(String[] args) {

        try{
            Document doc = Jsoup.connect("https://www.w3schools.com/html/html_tables.asp").get();

            System.out.println("Title: " + doc.title());

            Elements tableRows = doc.select("#customers tbody tr");
            System.out.println(tableRows);

            for(Element tr : tableRows){
                Elements columns = tr.select("td");

                if(columns.isEmpty()){
                    continue;
                }
                String empresa = columns.get(0).text();
                String contato = columns.get(1).text();
                String pais = columns.get(2).text();

                System.out.println(contato + " é o contato da empresa " + empresa + " em " + pais);
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }


            }

        }
