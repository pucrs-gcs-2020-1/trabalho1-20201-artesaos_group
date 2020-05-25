package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimentacao {

    private Date data;
    private int numeroDoc;
    private Operador operador;
    private String descricao;
    private double valor;

    public Movimentacao(int numeroDoc, Operador operador, String descricao, double valor){      
        this.data = new Date(System.currentTimeMillis());
        this.numeroDoc = numeroDoc;
        this.operador = operador;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public int getNumeroDoc() {
        return numeroDoc;
    }

    public Operador getOperador() {
        return this.operador;
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
        return formatter.format(this.data) + " - " + this.numeroDoc + " - " + this.operador + " - " + this.descricao + " - " + this.valor + " - " + getTipo();
    }

}