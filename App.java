import models.*;
import controllers.*;
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

    public static void menuPrincipal() { //TODO
        System.out.println("-------------------------------------------");
        System.out.println("|");
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

        } while (false);
    }
}
