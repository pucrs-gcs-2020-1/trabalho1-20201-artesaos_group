package controllers;

import models.Operador;

import java.util.ArrayList;

public class GerenciadorOperadores {

    ArrayList<Operador> operadores;

    public GerenciadorOperadores() {
        this.operadores = new ArrayList<>();
    }

    public Operador cadastrarOperador(String nome, String iniciais) {
        Operador operador = new Operador(nome, iniciais);
        operadores.add(operador);
        return operador;
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

    @Override
    public String toString() {
        String string = "";
        for (Operador operador:operadores) {
            string  += "\n" + operador;
        }
        return string;
    }

}
