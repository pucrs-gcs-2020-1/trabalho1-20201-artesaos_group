package models;

import java.text.SimpleDateFormat;
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
    public void adicionarMovimentacao(int numeroDoc, Operador operador, String descricao, double valor){
        Movimentacao movimentacao = new Movimentacao(numeroDoc, operador, descricao, valor);
        saldo = saldo+valor;
        movimentacoes.add(movimentacao);
    }

    public ArrayList<Movimentacao> consultarMovimentos(Date dataInicial, Date dataFinal){return movimentacoes;} //TODO
    public ArrayList<Movimentacao> consultarMovimentos(Operador operador){return movimentacoes;} //TODO
    public ArrayList<Movimentacao> consultarMovimentos(String tipo){return movimentacoes;} //TODO

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
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        return this.id + " - " + formatter.format(this.dataCriacao) + " - " + this.operador.getNome() + " - " + this.saldo;
    }
}