package controllers;

import models.Conta;
import models.Movimentacao;
import models.Operador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class GerenciadorContas {

    ArrayList<Conta> contas;
    //Recursos
    static Scanner input = new Scanner(System.in);

    public GerenciadorContas() {
        this.contas = new ArrayList<>();
    }

    public ArrayList<Conta> getContas() {
        return this.contas;
    }

    public Conta cadastrarConta(Operador operador) {
        //Gera um novo ID para o Operador
        int id_novo = contas.size() + 1;
        //Pega o horário atual
        Date date = new Date(System.currentTimeMillis());
        //Cria conta e adiciona
        Conta conta = new Conta(id_novo, date, operador);
        contas.add(conta);
        return conta;
    }

    public Conta consultarConta(Operador operador) {
        for (Conta conta:contas) {
            if(conta.getOperador()==operador) {
                return conta;
            }
        }
        return null;
    }

    public Conta pegaContaPorId(int numero) {
        for (Conta conta:contas) {
            if(conta.getId() == numero) {
                return conta;
            }
        }
        return null;
    }

    public boolean transferirFundos(Conta contaOrigem, Conta contaDestino, double valor, int numeroDoc, Operador operador, String descricao) {
        if((contaOrigem.getSaldo() - valor)>0) {
            contaOrigem.adicionarMovimentacao(numeroDoc, operador, descricao, -valor);
            contaDestino.adicionarMovimentacao(numeroDoc, operador, descricao, valor);
            return true;
        } else {
            return false;
        }
    }

    public Conta operacaoProcurarConta() throws Exception {
    	String escolha = "";
    	final String OPCAO_LISTAR = "L";
    	final String OPCAO_SAIR = "S";
    	Conta conta = null;
    	do {
	    	System.out.println("Informe Número da Conta: \n   '" + OPCAO_LISTAR + "' para Listar Existentes\n   '" + OPCAO_SAIR + "' para sair");
	    	escolha = input.nextLine();

	    	if(escolha.toUpperCase().equals(OPCAO_LISTAR)) {
	    		System.out.println(this.toString());
	    		continue;
	    	}

	    	if(escolha.toUpperCase().equals(OPCAO_SAIR)) {
	    		throw new Exception();
	    	}

	    	//Caso valor de conta nao seja um numero inteiro
	    	if(!escolha.matches("\\d+")) {
	    		System.out.println("Error: Valor de Conta deve ser um número Inteiro. Tente Novamente\n");
	    		continue;
	    	}

	    	// Busca conta pelo ID informado
	    	conta = pegaContaPorId(Integer.valueOf(escolha));

	    	if(conta == null) {
	    		System.out.println("Error: Conta Não encontrada\n");
	    		continue;
	    	}

	    	break;
    	}while(true);

    	return conta;
    }

    public Movimentacao operacaoAdicionarMovimento(Conta conta, Operador operador) throws Exception {
    	double montante;
    	String descricao;
    	int numeroDocumento;
    	final String OPCAO_SAIR = "S";
    	final String MENSAGEM_SAIR = "   '"+ OPCAO_SAIR  +"' para encerrar operação";
    	do {
	    	System.out.println("Informe Montante da Movimentação: \n"
	    			+ "Obs. 1 - Use ponto para separação dos centavos. Ex: 1583.50\n"
	    			+ "Obs. 2 - Use sinal de negativo para Movimentação de Saída. Ex: -1263.67\n"
	    			+ MENSAGEM_SAIR);
	    	String montanteString = input.nextLine();

	    	if(montanteString.toUpperCase().equals(OPCAO_SAIR)) {
	    		throw new Exception();
	    	}

	    	// Caso valor informado nao esteja no padrao XXX.XX
	    	if(!montanteString.matches("^-?[0-9]+(\\.[0-9]{1,2})?$")) {
	    		System.out.println("Montante Inválido. Separe parte inteira da decimal com ponto. Ex: 1583.50");
	    		continue;
	    	}

	    	montante = Double.valueOf(montanteString);
	    	break;
    	} while(true);

    	do {
    		System.out.println("Informe Número do Documento:\n"+ MENSAGEM_SAIR);
    		String nroDocString = input.nextLine();

	    	if(nroDocString.toUpperCase().equals(OPCAO_SAIR)) {
	    		throw new Exception();
	    	}

	    	// Caso valor de doc nao seja um inteiro
	    	if(!nroDocString.matches("^\\d+$")) {
	    		System.out.println("Número do Documento deve ser um Inteiro");
	    		continue;
	    	}

    		numeroDocumento = Integer.valueOf(nroDocString);
    		break;
    	}while(true);

    	System.out.println("Informe a Descrição da Movimentação:\n"+ MENSAGEM_SAIR);
    	descricao = input.nextLine();

    	return conta.adicionarMovimentacao(numeroDocumento, operador, descricao, montante);
    }
    
    public void operacaoTransferirParaConta(Conta contaOrigem, Operador operador) throws Exception {
    	Conta contaDestino;
		do {
			contaDestino = operacaoProcurarConta();
			if(contaDestino.getId() == contaOrigem.getId()) {
				System.out.println("Erro: Conta Destino é igual a Conta de Origem.");
				continue;
			}
			break;
		} while(true);

    	double montante;
    	String descricao;
    	int numeroDocumento;
    	final String OPCAO_SAIR = "S";
    	final String MENSAGEM_SAIR = "   '"+ OPCAO_SAIR  +"' para encerrar operação";
    	do {
	    	System.out.println("Informe Montante da Transferência: \n"
	    			+ "Obs. 1 - Use ponto para separação dos centavos. Ex: 1583.50\n"
	    			+ MENSAGEM_SAIR);
	    	String montanteString = input.nextLine();

	    	if(montanteString.toUpperCase().equals(OPCAO_SAIR)) {
	    		throw new Exception();
	    	}

	    	// Caso valor informado nao esteja no padrao XXX.XX
	    	if(!montanteString.matches("^[0-9]+(\\.[0-9]{1,2})?$")) {
	    		System.out.println("Montante Inválido. Separe parte inteira da decimal com ponto. Ex: 1583.50");
	    		continue;
	    	}

	    	montante = Double.valueOf(montanteString);
	    	break;
    	} while(true);

    	do {
    		System.out.println("Informe Número do Documento:\n"+ MENSAGEM_SAIR);
    		String nroDocString = input.nextLine();

	    	if(nroDocString.toUpperCase().equals(OPCAO_SAIR)) {
	    		throw new Exception();
	    	}

	    	// Caso valor de doc nao seja um inteiro
	    	if(!nroDocString.matches("^\\d+$")) {
	    		System.out.println("Número do Documento deve ser um Inteiro");
	    		continue;
	    	}

    		numeroDocumento = Integer.valueOf(nroDocString);
    		break;
    	}while(true);

    	System.out.println("Informe a Descrição da Movimentação:\n"+ MENSAGEM_SAIR);
    	descricao = input.nextLine();
    	
    	boolean result = transferirFundos(contaOrigem, contaDestino, montante, numeroDocumento, operador, descricao);
    	if(result) {
    		System.out.println("Transferência Realizada com Sucesso");
    	} else {
    		System.out.println("Erro na Transferência. Tente Novamente!");
    	}
    }


    public void printRelatorio(Conta conta, ArrayList<Movimentacao> movimentacoes, String filtro) {
    	// TODO: Formatar saida em forma de tabela https://github.com/iNamik/java_text_tables
    	// TODO: Talvez o toString de conta ja possa retornar neste formato
    	SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
    	System.out.println("Conta "+conta.getId() + ", criada por "+conta.getOperador().getNome()+"(" + conta.getOperador().getIniciais()  +") em "+formatter.format(conta.getDataCriacao()));
    	for(Movimentacao mov: movimentacoes) {
    		System.out.println(mov);
    	}
    }
    public void menuConsultarMovimentos(Conta conta, Operador op) {
    	Map<String, String> opcoes = new LinkedHashMap<String, String>();
    	opcoes.put("1", "Todos os Movimentos");
    	opcoes.put("2", "Filtrados por Período");
    	opcoes.put("3", "Filtrados por Operador");
    	opcoes.put("4", "Filtrados por Tipo");
    	opcoes.put("5", "Sair");

    	String escolha = MenuController.mostraMenuInterativo(opcoes, "Movimentações Conta " + conta.getId());

        chamaOpcaoMovimentacao(escolha, op, conta);
    }

    public void chamaOpcaoMovimentacao(String escolha, Operador op, Conta conta) {
    	switch(escolha) {
    		case "1":
	    		printRelatorio(conta, conta.getMovimentacoes(), "");
	    		menuConsultarMovimentos(conta, op);
    		break;
    		case "2":
    			System.out.println("TODO: Mostrar Interação para Filtros por Período");
    			menuConsultarMovimentos(conta, op);
			break;
    		case "3":
    			System.out.println("TODO: Mostrar Interação para Filtros por Operador");
    			menuConsultarMovimentos(conta, op);
			break;
    		case "4":
    			System.out.println("TODO: Mostrar Interação para Filtros por Tipo");
    			menuConsultarMovimentos(conta, op);
			break;

    	}
    }

    public void menuOperarConta(Conta conta, Operador operador) throws Exception {
    	Map<String, String> opcoes = new LinkedHashMap<String, String>();
    	opcoes.put("Saldo", "R$" + String.format("%.2f", conta.getSaldo()));
    	opcoes.put("1", "Adicionar Movimento");
    	opcoes.put("2", "Transferir Para Outra Conta");
    	opcoes.put("3", "Consultar Movimentos");
    	opcoes.put("4", "Sair");

    	String escolha = MenuController.mostraMenuInterativo(opcoes, "Conta " + conta.getId());

        chamaOpcao(escolha, operador, conta);
    }

    public void chamaOpcao(String escolha, Operador op, Conta conta) throws Exception {
    	switch(escolha) {
    	case "1":
    		try {
	    		operacaoAdicionarMovimento(conta, op);
	    		menuOperarConta(conta, op);
	    	} catch (Exception e) {
	    		menuOperarConta(conta, op);
			}
    		break;
    	case "2":
    		try {
    			operacaoTransferirParaConta(conta, op);
	    		menuOperarConta(conta, op);
	    	} catch (Exception e) {
	    		menuOperarConta(conta, op);
			}
    		break;
    	case "3":
    		menuConsultarMovimentos(conta, op);
    		menuOperarConta(conta, op);
    		break;
    	}
    }

    @Override
    public String toString() {
        String string = "";
        for (Conta conta:contas) {
            string  += "\n" + conta;
        }
        return string;
    }

}
