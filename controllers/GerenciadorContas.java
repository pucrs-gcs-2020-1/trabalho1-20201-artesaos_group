package controllers;

import models.Conta;
import models.Operador;

import java.util.ArrayList;
import java.util.Date;

public class GerenciadorContas {
    
    ArrayList<Conta> contas;

    public GerenciadorContas() {
        this.contas = new ArrayList<>();
    }

    public ArrayList<Conta> getContas() {
        return this.contas;
    }

    public void cadastrarConta(Operador operador) {
        //Gera um novo ID para o Operador
        int id_novo = contas.size() + 1;
        //Pega o horário atual
        Date date = new Date(System.currentTimeMillis());
        //Cria conta e adiciona        
        Conta conta = new Conta(id_novo, date, operador);
        contas.add(conta);
    }

    public Conta consultarConta(Operador operador) {
        for (Conta conta:contas) {
            if(conta.getOperador()==operador) {
                return conta;
            }
        }
        return null;
    }

    public boolean transferirFundos(Conta contaOrigem, Conta contaDestino, double valor, int numeroDoc, Operador operador, String descricao) {
        if((contaOrigem.getSaldo() - valor)>0) {
            contaOrigem.adicionarMovimentacao(numeroDoc, operador, descricao, -valor);
            contaDestino.adicionarMovimentacao(numeroDoc, operador, descricao, valor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String string = "";
        for (Conta conta:contas) {
            string  += "\n" + conta;
        }
        return string;
    }

}