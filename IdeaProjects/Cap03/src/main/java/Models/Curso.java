package Models;

public class Curso implements Comparable<Curso> {
    public Curso(String nome, float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    private String nome;
    private float valor;

    public String getNome() {
        return nome;
    }

    public float getValor() {
        return valor;
    }

    public int compareTo(Curso o) {
    return this.nome.compareTo(o.getNome());
    }
}
