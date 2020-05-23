package controllers;

import models.Operador;

import java.util.ArrayList;

public class GerenciadorOperadores {

    ArrayList<Operador> operadores = new ArrayList<>();

    public GerenciadorOperadores() {

    }

    public void cadastrarOperador(String nome, String iniciais) {
        Operador operador = new Operador(nome, iniciais);
        operadores.add(operador);
    }

    public Operador consultarOperador(String iniciais) {
        for (Operador op:operadores) {
            if(op.getIniciais()==iniciais) {
                return op;
            }
        }
        return null;
    }

    public ArrayList<Operador> getOperadores() {
        return this.operadores;
    }
}