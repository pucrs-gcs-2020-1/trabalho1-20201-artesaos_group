import models.*;
import controllers.*;
import java.util.Scanner;

public class App {

    //Controllers
    GerenciadorContas gerenciadorContas = new GerenciadorContas();
    GerenciadorOperadores gerenciadorOperadores = new GerenciadorOperadores();

    //Recursos
    static Scanner input = new Scanner(System.in);

    public static String menuLogin() {
        System.out.println("-------------------------");
        System.out.println("| Digite suas iniciais: |");
        System.out.println("-------------------------");
        return input.nextLine();
    }

    public static void menuPrincipal() {
        System.out.println("-------------------------------------------");
        System.out.println("|");
    }
    public static void main(String args[]) {

        do {
            String iniciais = menuLogin();
            //GerenciadorOperadores
        } while (true);

    }
}