public class OrdenaçãoPorInsercao {
    public static void main(String[] args) {

    int[] vetor = {5,1,10,20,15,25,22};

    int chave;
        for(int j = 1; j < vetor.length; j++){
        chave = vetor[j];
        int i;
        for (i = j-1;(i>=0) && (vetor[i]>chave); i--){
            vetor[i+1] = vetor[i];

        }
        vetor[i+1]=chave;

    }

    imprimir(vetor);

}

    public static void imprimir ( int [] array ){
        for ( int i = 0 ; i < array.length ; i++ ) {
            System.out.print ( " " + array[i]);
        }
        System.out.print ( "\n");
    }
}

