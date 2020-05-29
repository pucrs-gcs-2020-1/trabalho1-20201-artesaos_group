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
		gerenciadorOperadores.cadastrarOperador("Joao Victor Granzinoli", "JVG");
		gerenciadorOperadores.cadastrarOperador("Francisco João Lucca", "FJL");
		gerenciadorOperadores.cadastrarOperador("Felipe Hamester","FH");
		gerenciadorOperadores.cadastrarOperador("Marntin Ferreira", "MF");
		gerenciadorOperadores.cadastrarOperador("Bruno Andrade", "BA");
		gerenciadorOperadores.cadastrarOperador("Willian Damasceno", "WD");
		gerenciadorOperadores.cadastrarOperador("Filipe Oliveira", "FO");
		gerenciadorOperadores.cadastrarOperador("Joaquim Alberto Santos","JAS");
		gerenciadorOperadores.cadastrarOperador("Lucca Pereira Lima", "LPL");
		gerenciadorOperadores.cadastrarOperador("Rubens Cardoso Borges", "RCB");
		gerenciadorOperadores.cadastrarOperador("Bernardo Alves Barbosa ", "BAB");
		gerenciadorOperadores.cadastrarOperador("Pedro Barros Batista", "PBB");
        Operador operadorJoao = gerenciadorOperadores.consultarOperador("JVMB");
		Operador operadorEduardo = gerenciadorOperadores.consultarOperador("EAS");
		Operador operadorJoaoGranzinoli = gerenciadorOperadores.consultarOperador("JVG");
		Operador operadorFranciscoLucca = gerenciadorOperadores.consultarOperador("FJL");
		Operador operadorFelipeHamester = gerenciadorOperadores.consultarOperador("FH");
		Operador operadorMartinFerreira = gerenciadorOperadores.consultarOperador("MF");
		Operador operadorBrunoAndrade = gerenciadorOperadores.consultarOperador("BA");
		Operador operadorWilliamDamasceno = gerenciadorOperadores.consultarOperador("WD");
		Operador operadorFilipeOliveira = gerenciadorOperadores.consultarOperador("FO");
		Operador operadorJoaquimSantos = gerenciadorOperadores.consultarOperador("JAS");
		Operador operadorLuccaLima = gerenciadorOperadores.consultarOperador("LPL");
		Operador operadorRubensBorges = gerenciadorOperadores.consultarOperador("RCB");
		Operador operadorBernardoBarbosa = gerenciadorOperadores.consultarOperador("BAB");
		Operador operadorPedroBatista = gerenciadorOperadores.consultarOperador("PBB");

        //Contas
        gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("JVMB"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("EAS"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("JVG"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("FJL"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("FH"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("MF"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("BA"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("WD"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("FO"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("JAS"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("LPL"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("RCB"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("BAB"));
		gerenciadorContas.cadastrarConta(gerenciadorOperadores.consultarOperador("PBB"));
        Conta contaJoao = gerenciadorContas.consultarConta(operadorJoao);
		Conta contaEduardo = gerenciadorContas.consultarConta(operadorEduardo);
		Conta contaJoaoGranzinoli = gerenciadorContas.consultarConta(operadorJoaoGranzinoli);
		Conta contaFranciscoLucca = gerenciadorContas.consultarConta(operadorFranciscoLucca);
		Conta contaFelipeHamester = gerenciadorContas.consultarConta(operadorFelipeHamester);
		Conta contaMartinFerreira = gerenciadorContas.consultarConta(operadorMartinFerreira);
		Conta contaBrunoAndrade = gerenciadorContas.consultarConta(operadorBrunoAndrade);
		Conta contaWilliamDamasceno = gerenciadorContas.consultarConta(operadorWilliamDamasceno);
		Conta contaFilipeOliveira = gerenciadorContas.consultarConta(operadorFilipeOliveira);
		Conta contaJoaquimSantos = gerenciadorContas.consultarConta(operadorJoaquimSantos);
		Conta contaLuccaLima = gerenciadorContas.consultarConta(operadorLuccaLima);
		Conta contaRubensBorges = gerenciadorContas.consultarConta(operadorRubensBorges);
		Conta contaBernarndoBarbosa= gerenciadorContas.consultarConta(operadorBernardoBarbosa);
		Conta contaPedroBatista = gerenciadorContas.consultarConta(operadorPedroBatista);

        //Movimentações
        contaJoao.adicionarMovimentacao(1, operadorJoao, "Prêmio da loteria", 1000000);
		contaEduardo.adicionarMovimentacao(1, operadorEduardo, "Lucro do curso de vender curso - Hotmart", 3000000);
		contaJoaoGranzinoli.adicionarMovimentacao(1, operadorJoaoGranzinoli, "Comercio de roupas", 10000);
		contaFranciscoLucca.adicionarMovimentacao(1, operadorFranciscoLucca, "Venda de gado", 400000);
		contaFelipeHamester.adicionarMovimentacao(1, operadorFelipeHamester, "Salario", 2000);
		contaMartinFerreira.adicionarMovimentacao(1, operadorMartinFerreira, "Venda de Artigos pessoais", 300);
		contaBrunoAndrade.adicionarMovimentacao(1, operadorBrunoAndrade, "Lucro mensal de empresa", 5000000);
		contaWilliamDamasceno.adicionarMovimentacao(1, operadorWilliamDamasceno, "Venda de Livros", 400);
		contaFilipeOliveira.adicionarMovimentacao(1, operadorFilipeOliveira, "Marketing digital", 20000);
		contaJoaquimSantos.adicionarMovimentacao(1, operadorJoaquimSantos, "Lucro de loja de artigos tecnológicos", 8500);
		contaLuccaLima.adicionarMovimentacao(1, operadorLuccaLima, "Lucro em competição de LOL ", 1000000);
		contaRubensBorges.adicionarMovimentacao(1, operadorRubensBorges, "Venda de carros usados", 40000);
		contaBernarndoBarbosa.adicionarMovimentacao(1, operadorBernardoBarbosa, "Lucro de bar", 9000);
		contaPedroBatista.adicionarMovimentacao(1, operadorPedroBatista, "Lucro de venda de curso na udemy", 12000);
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
