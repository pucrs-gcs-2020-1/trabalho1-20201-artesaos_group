package controllers;

import models.Conta;
import models.Operador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GerenciadorContas {
    
    ArrayList<Conta> contas = new ArrayList<>();

    public GerenciadorContas() {

    }

    public ArrayList<Conta> getContas() {
        return this.contas;
    }

    public void cadastrarConta(Operador operador, double saldo) {
        //Gera um novo ID para o Operador
        int id_novo = contas.size() + 1;
        //Pega o horário atual
        Date date = new Date(System.currentTimeMillis());
        //Cria conta e adiciona        
        Conta conta = new Conta(id_novo, date, operador, saldo);
        contas.add(conta);
    }

    public boolean transferirFundos(Conta contaOrigem, Conta contaDestino, double valor, int numeroDoc, String descricao) {
        if((contaOrigem.getSaldo - valor)>0) {
            contaOrigem.adicionarMovimentacao(numeroDoc, descricao, -valor);
            contaDestino.adicionarMovimentacao(numeroDoc, descricao, valor);
            return true;
        } else {
            return false;
        }
    }
}