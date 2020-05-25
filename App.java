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
    public static String menuLogin() {
        System.out.println("-------------------------");
        System.out.println("| Digite suas iniciais: |");
        System.out.println("-------------------------");
        return input.nextLine();
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

        do {
            String iniciais = menuLogin();
        } while (true);

    }
}