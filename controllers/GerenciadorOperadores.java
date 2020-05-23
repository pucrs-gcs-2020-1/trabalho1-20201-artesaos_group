package controllers;

import models.Operador;
import java.util.ArrayList;

public class GerenciadorOperadores {

    ArrayList operadores = new ArrayList<Operador>();

    public GerenciadorOperadores() {

    }

    public ArrayList<Operador> getOperadores() {
        return this.operadores;
    }
}