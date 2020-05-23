package models;

import java.util.ArrayList;
import java.util.Date;

public class Conta {

    private int id;
    private Date dataCriacao;
    private Operador operador;
    private double saldo;
    private ArrayList<Movimentacao> movimentacoes;

    public Conta(int id, Date dataCriacao, Operador operador) {
        this.saldo = 0;
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.operador = operador;
        this.movimentacoes = new ArrayList<>();
    }

    //Pensar na verificação.
    public void adicionarMovimentacao(int numeroDoc, String descricao, double valor, String tipo){
        Movimentacao movimentacao = new Movimentacao(numeroDoc, descricao, valor, tipo);
        movimentacoes.add(movimentacao);
    }

    public ArrayList<Movimentacao> consultarMovimentos(Date dataInicial, Date dataFinal){return movimentacoes;}
    public ArrayList<Movimentacao> consultarMovimentos(Operador operador){return movimentacoes;}
    public ArrayList<Movimentacao> consultarMovimentos(String tipo){return movimentacoes;}

    public Date getDataCriacao() {
        return dataCriacao;
    }   

    public int getId() {
        return id;
    }

    public ArrayList<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public Operador getOperador() {
        return operador;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.dataCriacao + " - " + this.operador.getNome() + " - " + this.saldo;
    }
}