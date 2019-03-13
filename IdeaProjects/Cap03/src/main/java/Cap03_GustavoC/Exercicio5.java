package Cap03_GustavoC;

import org.apache.wicket.util.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Exercicio5 {

    public static void main(String[] args) {


        InputStream in = null;
        /*try{

            in = new URL("https://www.java.com").openStream();
            InputStreamReader inR = new InputStreamReader(in);
            BufferedReader buf = new BufferedReader(inR);
            String line;
            while ((line = buf.readLine()) != null){
                System.out.printf(line);
            }
        }catch (MalformedURLException e){
            e.fillInStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(in != null){
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }*/
        try{
            in = new URL("https://www.java.com").openStream();
            System.out.printf(IOUtils.toString(in));
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(in);
        }
    }
}
