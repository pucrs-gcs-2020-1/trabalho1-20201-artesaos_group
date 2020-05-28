package controllers;

import models.Operador;

import java.util.ArrayList;
import java.util.Scanner;

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
    public Operador cadastraNovoOperador(){
        Scanner input = new Scanner(System.in);
        String nomeCompleto = "";
    	do {
            System.out.println("Informe o nome do novo Operador:");
            nomeCompleto = input.nextLine();
	        // Validacao: nao pode conter digitos
	        if(!nomeCompleto.equals("X Æ A-12") && nomeCompleto.matches(".*\\d+.*")) {
	        	System.out.println("Informe um Nome Válido");
	        	continue;
	        }
	        break;
    	} while(true);

        String iniciais = "";
        // Pegas as iniciais
        for(String elemento : nomeCompleto.split(" ")) {
        	// Ignora Preposicoes
        	if(elemento.toLowerCase().matches("d(e|o|a)s?")) {
        		continue;
        	}
        	iniciais = iniciais.concat(Character.toString(elemento.charAt(0)).toUpperCase());
        }
        Operador o = this.cadastrarOperador(nomeCompleto, iniciais);
        return o;
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
