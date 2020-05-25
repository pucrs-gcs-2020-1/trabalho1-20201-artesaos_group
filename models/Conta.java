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

    public ArrayList<Movimentacao> consultarMovimentosData(Date dataInicial, Date dataFinal){
        ArrayList<Movimentacao> movimentacoesData = new ArrayList<>();
        for (int i = 0; i < movimentacoes.size(); i++){
            if (movimentacoes.get(i).getData().compareTo(dataInicial) >= 0 &&
            movimentacoes.get(i).getData().compareTo(dataFinal) <= 0){
                movimentacoesData.add(movimentacoes.get(i));
            }
        }
        return movimentacoesData;
    }

    public ArrayList<Movimentacao> consultarMovimentosTipo(String tipo){
        ArrayList<Movimentacao> movimentacoesTipo = new ArrayList<>();
        for (int i = 0; i < movimentacoes.size(); i++){
            if(movimentacoes.get(i).getTipo().equalsIgnoreCase(tipo)){
                movimentacoesTipo.add(movimentacoes.get(i));
            }
        }
        return movimentacoesTipo;
    }

    public ArrayList<Movimentacao> consultarMovimentosOperador(Operador operador){
        ArrayList<Movimentacao> movimentacoesOperador = new ArrayList<>();
        for (int i = 0; i < movimentacoes.size(); i++){
            if(movimentacoes.get(i).getOperador().equals(operador){
                movimentacoesOperador.add(movimentacoes.get(i));
            }
        }
        return movimentacoesOperador;
    }

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