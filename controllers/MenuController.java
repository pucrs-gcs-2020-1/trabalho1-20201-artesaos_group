package controllers;

import java.util.Map;
import java.util.Scanner;

public class MenuController {
		private static Scanner input = new Scanner(System.in);
	    public static void printMenu(Map<String, String> opcoes, String title) {
	    	int maior = title.length();
	        for(String chave : opcoes.keySet()){
	        	int size = opcoes.get(chave).length() + chave.length(); 
	        	if(size > maior) maior = size; 
	        }
	        	    	
	        int foo = (maior + 6) - title.length();
	        System.out.println("-".repeat(Math.floorDiv(foo, 2)) + " " + title + " " + "-".repeat(Math.floorDiv(foo, 2)));
	        
	        for(String chave : opcoes.keySet()){
	        	System.out.println(String.format("| %s - %-"+ (maior - chave.length() + 1 ) +"s |", chave, opcoes.get(chave))); 
	        }
	    	System.out.println("-".repeat(maior + 8));
	    }
	    
	    public static String mostraMenuInterativo(Map<String, String> opcoes, String title) {
	    	String escolha = "";
	    	do {
	    		printMenu(opcoes, title);
		    	escolha = input.nextLine();

		    	String opcao = opcoes.get(escolha);
		    	if(opcao == null) {
		    		System.out.println("Opção Inválida, escolha uma correta");
		    		continue;
		    	}
		    	break;
	    	} while(true);
	    	return escolha;
	    }
}
