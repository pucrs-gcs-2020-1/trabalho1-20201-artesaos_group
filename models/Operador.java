package models;

public class Operador {
    
    String nome;
    String iniciais;

    public Operador(String nome, String iniciais) {
        this.nome = nome;
        this.iniciais = iniciais;
    }

    public String getNome() {
        return this.nome;
    }

    public String getIniciais() {
        return this.iniciais;
    }

}