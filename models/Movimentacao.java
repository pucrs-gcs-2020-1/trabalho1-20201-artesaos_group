package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimentacao {

    private Date data;
    private int numeroDoc;
    private String descricao;
    private double valor;

    public Movimentacao(int numeroDoc, String descricao, double valor){      
        this.data = new Date(System.currentTimeMillis());
        this.numeroDoc = numeroDoc;
        this.descricao = descricao;
        this.valor = valor;
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
        if(valor>0) {
            return "Depósito";
        } else {
            return "Saque";
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.data) + " - " + this.numeroDoc + " - " + this.descricao + " - " + this.valor + " - " + getTipo();
    }

}