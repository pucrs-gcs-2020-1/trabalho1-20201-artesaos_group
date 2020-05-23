package models;
import java.util.Date;

public class Movimentacao {

    private Date data;
    private int numeroDoc;
    private String descricao;
    private double valor;
    private String tipo;

    public Movimentacao(int numeroDoc, String descricao, double valor, String tipo){      
        this.data = new Date(System.currentTimeMillis());
        this.numeroDoc = numeroDoc;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public int getNumeroDoc() {
        return numeroDoc;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return this.data + " - " + this.numeroDoc + " - " + this.descricao + " - " + this.valor + " - " + this.tipo;
    }

}