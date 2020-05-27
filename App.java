import models.*;
import controllers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    //Controllers
    static GerenciadorContas gerenciadorContas = new GerenciadorContas();
    static GerenciadorOperadores gerenciadorOperadores = new GerenciadorOperadores();

    //Recursos
    static Scanner input = new Scanner(System.in);

    //Métodos para menus
    public static Operador menuLogin() {
    	String nomeCompleto = "";
    	do {
	        System.out.println("-------------------------");
	        System.out.println("| Informe Seu Nome:      |");
	        System.out.println("-------------------------");
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
        return gerenciadorOperadores.cadastrarOperador(nomeCompleto, iniciais);
    }

    public static void menuPrincipal(Operador operador) throws Exception {
    	System.out.println("Operador:" + operador.getIniciais());
    	Map<String, String> opcoes = new HashMap<String, String>();
    	opcoes.put("1", "Criar Nova Conta");
    	opcoes.put("2", "Gerenciar Conta Existente");
    	opcoes.put("3", "Cadastrar Operador");
    	opcoes.put("4", "Trocar de Operador");
    	opcoes.put("5", "Sair");

    	String escolha = MenuController.mostraMenuInterativo(opcoes, "Menu Principal");

    	chamaOpcao(escolha, operador);
    }

    public static void chamaOpcao(String escolha, Operador operador) throws Exception {
    	switch(escolha) {
    	case "1":
    		Conta conta = gerenciadorContas.cadastrarConta(operador);
    		System.out.println("Conta " + conta.getId() + " Criada");
			gerenciadorContas.menuOperarConta(conta, operador);
			menuPrincipal(operador);
    		break;
    	case "2":
    		try {
				gerenciadorContas.menuOperarConta(gerenciadorContas.operacaoProcurarConta(), operador);
				menuPrincipal(operador);
    		} catch (Exception e) {
    			menuPrincipal(operador);
			}
    		break;
		case "3":
			
			Operador op = gerenciadorOperadores.cadastraNovoOperador();
			System.out.println("Oparador -> " + op.toString() + " cadastrado" );
    		menuPrincipal(operador);
    		break;
    	case "4":
    		System.out.println("TODO: Troca Operador");
    		menuPrincipal(operador);
    		break;
    	case "5":
    		throw new Exception();
    	default:
    		break;
    	}
    }

    //Método para alimentar dados
    public static void alimentarDados() {
        //Operadores
        gerenciadorOperadores.cadastrarOperador("João Brentano", "JVMB");
        gerenciadorOperadores.cadastrarOperador("Eduardo André Soares", "EAS");
        Operador operadorJoao = gerenciadorOperadores.consultarOperador("JVMB");
        Operador operadorEduardo = gerenciadorOperadores.consultarOperador("EAS");
        //Contas
        gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("JVMB"));
        gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("EAS"));
        Conta contaJoao = gerenciadorContas.consultarConta(operadorJoao);
        Conta contaEduardo = gerenciadorContas.consultarConta(operadorEduardo);
        //Movimentações
        contaJoao.adicionarMovimentacao(1, operadorJoao, "Prêmio da loteria", 1000000);
        contaEduardo.adicionarMovimentacao(1, operadorEduardo, "Lucro do curso de vender curso - Hotmart", 3000000);
        //Imprime
        System.out.println(gerenciadorContas);
        System.out.println(gerenciadorOperadores);
        for(Conta conta:gerenciadorContas.getContas()) {
            System.out.println(conta.getMovimentacoes());
        }
    }
    public static void main(String args[]) { //TODO

        alimentarDados();
        Operador operador = menuLogin();

        do {
        	try {
				menuPrincipal(operador);
			} catch (Exception e) {
				System.out.println("Encerrando Programa!");
			}
        } while (false);
    }
}
