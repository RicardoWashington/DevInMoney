package br.devinmoney.menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import br.devinmoney.contas.Agencia;
import br.devinmoney.contas.Conta;
import br.devinmoney.contas.ContaCorrente;
import br.devinmoney.contas.ContaInvestimento;
import br.devinmoney.contas.ContaPoupanca;
import br.devinmoney.contas.TipoConta;
import br.devinmoney.operadores.Operador;
import br.devinmoney.operadores.TipoTransacao;
import br.devinmoney.operadores.Transacao;
import br.devinmoney.validacoes.ValidaDoctos;

public class Menu {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Operador operador = new Operador();
		ValidaDoctos valida = new ValidaDoctos();
		Agencia agencia;
		TipoConta tipoConta;
		int operacao = 0, meses= 0, ag = 0, op, tpConta = 0, gn = 0;
		String nome, cpf, numero, numDestino, tpmov;
		double renda, saldo, valor;
		String dataIni, dataFin;
		ArrayList<Conta> contas;
		ArrayList<ContaCorrente> contaCorrente;
		Conta conta;
		Transacao transacao = new Transacao();;
		try {
			do {
				 System.out.println("##---->>>BANCO DEVinMoney<<<----##");
			     System.out.println(":::: O melhor banco do mundo! ::::");
			     System.out.println("Selecione uma opção");
			     System.out.println(" 1 - Cadastrar Contas");
			     System.out.println(" 2 - Listar todas as Contas");
			     System.out.println(" 3 - Listar Contas negativas");
			     System.out.println(" 4 - Depósito");
			     System.out.println(" 5 - Transferência");
			     System.out.println(" 6 - Saque");
			     System.out.println(" 7 - Saldo");
			     System.out.println(" 8 - Extrato");
			     System.out.println(" 9 - Investimento");
			     System.out.println("10 - Histórico de Transferencias");
			     System.out.println("11 - Simular Poupança");
			     System.out.println("12 - Alterar dados cadastrais");
			     System.out.println(" 0 - Sair");
			     System.out.print("Opção escolhida: ");
			     operacao = scanner.nextInt();
			     switch(operacao) {
			    
			     case 1:
				    	 System.out.println("Informe o seu nome");
				    	 scanner.nextLine();
				    	 nome = scanner.nextLine();
				    	 System.out.println("Informe o CPF");
				      	 cpf = scanner.nextLine();
				    	 while (!valida.validaCPF(cpf)) {
				    		 System.out.println("Informe um CPF Válido");
				    		 cpf = scanner.nextLine();
				    	 }
				    	 do{
				      	     System.out.println("Informe o tipo de Conta");
				      		 System.out.println("1 - Conta Corrente / 2 - Poupança / 3 - Investimento");
				      		 tpConta = scanner.nextInt();
				      	 }while ((tpConta != 1) && (tpConta != 2) && (tpConta != 3)) ;
				    	 System.out.println("Informe a renda mensal");
				      	 renda = scanner.nextDouble();
				      	 while (renda < 0) {
				      		 System.out.println("A renda mensal não pode ser negativa");
				      		 System.out.println("Informe a renda mensal");
					      	 renda = scanner.nextDouble();
				      	 }
				      	 do{
				      	     System.out.println("Informe a Agencia");
				      		 System.out.println("1 - Florianopolis / 2 - São Jose");
				      		 ag = scanner.nextInt();
				      	 }while ((ag != 1) && (ag != 2)) ;
				      	 System.out.println("Informe o saldo");
				      	 saldo = scanner.nextDouble();
				      	 if (ag == 1) {
				      		 agencia = Agencia.FLORIANOPOLIS;
				       	 } else {
				       		 agencia = Agencia.SAO_JOSE;
				      	 }
				      	 if (tpConta == 1) {
				      		 tipoConta = TipoConta.CONTA_CORRENTE;
				      	 }else if (tpConta == 2){
				      		 tipoConta = TipoConta.POUPANÇA;
				      	 }else {
				      		 tipoConta = TipoConta.INVESTIMENTO;
				      	 }
				      	 conta = new ContaCorrente(nome,cpf,renda,operador.geraNumConta(),tipoConta, agencia, saldo, 0);
			      		 operador.cadastrar(conta);
				      	 break;
			     case 2:
			    	    try{ 
					    	 contas = operador.listar();
					    	 System.out.println("     L I S T A   C O N T A  C O R R E N T E     ");
					    	 System.out.println("------------------------------------------------");
					    	 for (int c = 0; c < contas.size(); c++) {
					    		if (contas.get(c).getTipoConta() == TipoConta.CONTA_CORRENTE) {
					    			System.out.println("Cliente  : "+contas.get(c).getNome());
					    			System.out.println("Agencia  : "+contas.get(c).getAgencia());
					    			System.out.println("Num Conta: "+contas.get(c).getNumConta());
					    			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - ");
					    			System.out.println("SALDO ATUAL:"+ String.format("%36s", String.format("%.2f",contas.get(c).getSaldo())));
							    	System.out.println("LIMITE:"+ String.format("%41s", String.format("%.2f", contas.get(c).getChequeEspecial())));
							        System.out.println("SALDO + LIMITE:"+ String.format("%33s", String.format("%.2f",(contas.get(c).getSaldo() + contas.get(c).getChequeEspecial()))));
							        System.out.println("------------------------------------------------");
					    		}
					    	 }
					    	 System.out.println("     L I S T A   C O N T A  P O U P A N Ç A     ");
					    	 System.out.println("------------------------------------------------");
						     for (int p = 0; p < contas.size(); p++) {
						    	if (contas.get(p).getTipoConta() == TipoConta.POUPANÇA) {
					    			System.out.println("Cliente  : "+contas.get(p).getNome());
					    			System.out.println("Agencia  : "+contas.get(p).getAgencia());
					    			System.out.println("Num Conta: "+contas.get(p).getNumConta());
					    			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - ");
					    			System.out.println("SALDO ATUAL:"+ String.format("%36s", String.format("%.2f",contas.get(p).getSaldo())));
					    			System.out.println("------------------------------------------------");
						         }
						     }
						     System.out.println(" L I S T A   C O N T A  I N V E S T I M E N T O ");
						     System.out.println("------------------------------------------------");
						     for (int i = 0; i < contas.size(); i++) {
						    	if (contas.get(i).getTipoConta() == TipoConta.INVESTIMENTO) {
					    			System.out.println("Cliente  : "+contas.get(i).getNome());
					    			System.out.println("Agencia  : "+contas.get(i).getAgencia());
					    			System.out.println("Num Conta: "+contas.get(i).getNumConta());
					    			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - ");
					    			System.out.println("SALDO ATUAL:"+ String.format("%36s", String.format("%.2f",contas.get(i).getSaldo())));
					    			System.out.println("------------------------------------------------");
						    	}
						     }
						     System.out.println("Selecione: 0 - Voltar");
					    	  op = scanner.nextInt();
					    	  while (op == 0) {
					    		  break;
					    	  } 
				    	  }catch (NullPointerException e){
				    		  System.out.println("Nenhuma conta cadastrada");
				    		  System.out.println("--------------------------------------");
				    		  ocultarTela();
				    		  break;
				    	  }
			    	      break;
			      case 3:
			      		try{ 
					    	 contas = operador.listar();
					    	 System.out.println("###>>>    CONTAS NEGATIVAS    <<<###");
					    	 System.out.println("--------------------------------------");
					    	 for (int c = 0; c < contas.size(); c++) {
					    		if ((contas.get(c).getTipoConta() == TipoConta.CONTA_CORRENTE) &&
					    				(contas.get(c).getSaldo() < 0)){
					    			System.out.println("Cliente  : "+contas.get(c).getNome());
					    			System.out.println("Agencia  : "+contas.get(c).getAgencia());
					    			System.out.println("Num Conta: "+contas.get(c).getNumConta());
					    			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - ");
					    			System.out.println("SALDO ATUAL:"+ String.format("%36s", String.format("%.2f",contas.get(c).getSaldo())));
					    			System.out.println("------------------------------------------------");
					    		}else {
					    			System.out.println("Nenhuma conta cadastrada!");	
					    		}
					    	 }
					    	 System.out.println("Selecione: 0 - Voltar");
					    	  op = scanner.nextInt();
					    	  while (op == 0) {
					    		  break;
					    	  } 
				    	  }catch (NullPointerException e){
				    		  System.out.println("Nenhuma conta cadastrada");
				    		  System.out.println("--------------------------------------");
				    		  ocultarTela();
				    		  break;
				    	  }
			      		  break;
			      case 4:
			      		  System.out.println("Informe a conta");
				    	  scanner.nextLine();
				    	  numero = scanner.nextLine();
				    	  System.out.println("Quanto deseja depositar?");
				    	  valor = scanner.nextDouble();
				    	  operador.deposito(numero, valor);
				    	  conta = operador.consultar(numero);
				    	  System.out.println("--------------------------------");
				  		  System.out.println("-Deposito efetuado com sucesso!-");
				  		  System.out.println("-Seu saldo atual é: "+ conta.getSaldo() +"      -");
				  		  System.out.println("--------------------------------");
				    	  System.out.println("Selecione: 0 - Voltar");
				    	  op = scanner.nextInt();
				    	  while (op == 0) {
				    		  break;
				    	  }
				    	  break;
			      case 5:
			    	  	  System.out.println("Informe a conta origem");
			    	  	  scanner.nextLine();
			    	  	  numero = scanner.nextLine();
			    	  	  System.out.println("Informe a conta destino");
			    	  	  numDestino = scanner.nextLine();
			    	  	  System.out.print("Quanto deseja transferir: ");
			    	  	  valor = scanner.nextDouble();
			    	  	  operador.transferencia(numero, numDestino, valor);
			    	  	  System.out.println("Selecione: 0 - Voltar");
				    	  op = scanner.nextInt();
				    	  while (op == 0) {
				    		  break;
				    	  }
				    	  break;
			      case 6:
				    	  System.out.println("Informe a conta");
				    	  scanner.nextLine();
				    	  numero = scanner.nextLine();
				    	  System.out.println("Quanto deseja sacar?");
				    	  valor = scanner.nextDouble();
				    	  operador.saque(numero, valor);
				    	  System.out.println("Selecione: 0 - Voltar");
				    	  op = scanner.nextInt();
				    	  while (op == 0) {
				    		  break;
				    	  }
				    	  break;
			      case 7:
				    	  System.out.println("Informe a conta");
				    	  scanner.nextLine();
				    	  numero = scanner.nextLine();
				    	  conta = operador.consultar(numero);
				    	  System.out.println("-----------------------------------------");
				    	  System.out.println("CONTA:            "+conta.getNumConta());
				    	  System.out.println("SALDO ATUAL:"+ String.format("%36s", conta.getSaldo()));
				    	  if (conta.getTipoConta().equals(TipoConta.CONTA_CORRENTE)){
				    		  System.out.println("LIMITE:"+ String.format("%41s", conta.getChequeEspecial()));
				    		  System.out.println("SALDO + LIMITE:"+ String.format("%33s", (conta.getSaldo() + conta.getChequeEspecial())));
					    	    
				    	  }
				    	  System.out.println("------------------------------------------");
				    	  System.out.println("Selecione: 0 - Voltar");
				    	  op = scanner.nextInt();
				    	  while (op == 0) {
				    		  break;
				    	  }
				    	  break;
			      case 8:
			    	      try {
				    	  	  System.out.println("Informe a conta");
				    	  	  scanner.nextLine();
				    	  	  numero = scanner.nextLine();
				    	  	  conta = operador.consultar(numero);
				              System.out.println("        E X T R A T O   B A N C Á R I O         ");
				              System.out.println("------------------------------------------------");
				              System.out.println(conta.getTipoConta());
				              System.out.println("Cliente: "+ conta.getNome());
				              System.out.println("Agência: "+ conta.getAgencia());
				              System.out.println("Conta: " + conta.getNumConta());
				              System.out.println("------------------------------------------------");
				              System.out.println("DATA        HISTORICO           DOC        VALOR");
				              System.out.println("------------------------------------------------");
				              for (int i = 0; i <conta.getTransacoes().size(); i++) {
				            	  Transacao t = conta.getTransacoes().get(i);
				            	  if (t.getTipoTransacao().equals(TipoTransacao.CRÉDITO)){
				            		  tpmov = "C";
				            	  }else {
				            		  tpmov = "D";
				            	  }
				            	  System.out.println((t.getData().format(formataData)) + "  " + String.format("%-20s", t.getComplemento()) +
				            	  	String.format("%1$5s", t.getId()).replace(' ', '0')+ String.format("%10s", String.format("%.2f",t.getValor()))+ tpmov );
				            	  
				              }
				              System.out.println("------------------------------------------------");
					    	  System.out.println("SALDO ATUAL:"+ String.format("%36s", String.format("%.2f",conta.getSaldo())));
					    	  if (conta.getTipoConta().equals(TipoConta.CONTA_CORRENTE)){
					    		  System.out.println("LIMITE:"+ String.format("%41s", String.format("%.2f", conta.getChequeEspecial())));
					    		  System.out.println("SALDO + LIMITE:"+ String.format("%33s", String.format("%.2f",(conta.getSaldo() + conta.getChequeEspecial()))));
						    	    
					    	  }
					    	  System.out.println("------------------------------------------------");
				              System.out.println("Selecione: 0 - Voltar");
					    	  op = scanner.nextInt();
					    	  while (op == 0) {
					    		  break;
					    	  }
			    	      }catch (NullPointerException e){
				    		  ocultarTela();
				    		  break;
				    	  }
			      		  break;
			      case 9:
		        	  System.out.println("Informe a conta");
			    	  scanner.nextLine();
			    	  numero = scanner.nextLine();
			    	  do{
				      	 System.out.println("Informe qual aplicação deseja:");
				         System.out.println("1 - CDB / 2 - CDI");
				         gn = scanner.nextInt();
				      	 }while ((gn != 1) && (gn != 2)) ;
			    	  
			    	  operador.simuInvest(numero, gn);
			    	  conta = operador.consultar(numero);
			    	  System.out.println("--------------------------------");
			  		  System.out.println("-Calculo efetuado com sucesso!-");
			  		  System.out.println("-Seu novo saldo seria: "+ String.format("%.2f", conta.getSaldo()));
			  		  System.out.println("--------------------------------");
			    	  System.out.println("Selecione: 0 - Voltar");
			    	  op = scanner.nextInt();
			    	  while (op == 0) {
			    		  break;
			    	  }
			    	  break;
			      case 10:
			    	  try {
			    	  	  System.out.println("Informe a conta");
			    	  	  scanner.nextLine();
			    	  	  numero = scanner.nextLine();
			    	  	  conta = operador.consultar(numero);

			              System.out.println("  E X T R A T O   T R A N S F E R E N C I A S   ");
			              System.out.println("------------------------------------------------");
			              System.out.println(conta.getTipoConta());
			              System.out.println("Cliente: "+ conta.getNome());
			              System.out.println("Agência: "+ conta.getAgencia());
			              System.out.println("Conta: " + conta.getNumConta());
			              System.out.println("------------------------------------------------");
			              System.out.println("DATA        HISTORICO           DOC        VALOR");
			              System.out.println("------------------------------------------------");
			              for (int i = 0; i <conta.getTransacoes().size(); i++) {
			            	  Transacao t = conta.getTransacoes().get(i);
			            	  if (t.getTipoTransacao().equals(TipoTransacao.CRÉDITO)){
			            		  tpmov = "C";
			            	  }else {
			            		  tpmov = "D";
			            	  }
			            	  if (t.getComplemento().equals("TRANSFERENCIA")) {
			            		  System.out.println((t.getData().format(formataData)) + "  " + String.format("%-20s", t.getComplemento()) +
						            	  	String.format("%1$5s", t.getId()).replace(' ', '0')+ String.format("%10s", String.format("%.2f",t.getValor()))+ tpmov );
			            	  }
			              }
			              System.out.println("------------------------------------------------");
				    	  System.out.println("SALDO ATUAL:"+ String.format("%36s", String.format("%.2f",conta.getSaldo())));
				    	  if (conta.getTipoConta().equals(TipoConta.CONTA_CORRENTE)){
				    		  System.out.println("LIMITE:"+ String.format("%41s", String.format("%.2f", conta.getChequeEspecial())));
				    		  System.out.println("SALDO + LIMITE:"+ String.format("%33s", String.format("%.2f",(conta.getSaldo() + conta.getChequeEspecial()))));
					    	    
				    	  }
				    	  System.out.println("------------------------------------------------");
			              System.out.println("Selecione: 0 - Voltar");
				    	  op = scanner.nextInt();
				    	  while (op == 0) {
				    		  break;
				    	  }
		    	      }catch (NullPointerException e){
			    		  ocultarTela();
			    		  break;
			    	  }
		      		  break;
		      	case 11:
		        	
		        	  System.out.println("Informe a conta");
			    	  scanner.nextLine();
			    	  numero = scanner.nextLine();
			    	  System.out.println("Quantos meses deseja calcular?");
			    	  meses = scanner.nextInt();
			    	  System.out.println("Informa a Taxa Anual");
			    	  valor = scanner.nextDouble();
			    	  operador.rendeJuros(numero, meses, valor);
			    	  conta = operador.consultar(numero);
			    	  System.out.println("--------------------------------");
			  		  System.out.println("-Rendimento efetuado com sucesso!-");
			  		  System.out.println("-Seu saldo atual é: "+ String.format("%.2f", conta.getSaldo()));
			  		  System.out.println("--------------------------------");
			    	  System.out.println("Selecione: 0 - Voltar");
			    	  op = scanner.nextInt();
			    	  while (op == 0) {
			    		  break;
			    	  }
			    	  break;
			    case 12:
			    	 System.out.println("Informe a conta");
		    	  	 scanner.nextLine();
		    	  	 numero = scanner.nextLine();
		    	  	 conta = operador.consultar(numero);
		    	  	 System.out.println(" D A D O S   A T U A I S   D A   C O N T A");
		    	  	 System.out.println("Conta: " + conta.getNumConta());
		    	  	 System.out.println("Cliente: " + conta.getNome());
		    	  	 System.out.println("Agencia: " + conta.getAgencia());
		    	  	 System.out.println("Renda: " + conta.getRenda());
		    	  	 
		    	  	 do{
			      	     System.out.println("Deseja alterar os dados?");
			      		 System.out.println("1 - Sim / 2 - Não");
			      		 gn = scanner.nextInt();
			      	 }while ((gn != 1) && (gn != 2)) ;
		    	  	 if (gn == 1) {
		    	  		 System.out.println("Informe o seu nome");
				    	 scanner.nextLine();
				    	 nome = scanner.nextLine();
				    	 
				    	 System.out.println("Informe a renda mensal");
				      	 renda = scanner.nextDouble();
				      	 while (renda < 0) {
				      		 System.out.println("A renda mensal não pode ser negativa");
				      		 System.out.println("Informe a renda mensal");
					      	 renda = scanner.nextDouble();
				      	 }
				      	 conta.setNome(nome);
				      	 conta.setRenda(renda);
		    	  	 
		    	  	 }
			    	 break;
			    	 
				
			     
			     
			     }// switch
			    
			}while(operacao != 0);
		} catch (InputMismatchException e) {
			System.out.println("Desculpe, valor inválido! A aplicação será encerrada!\n"
					+ "### BANCO DevInMoney. O banco do futuro! ###");
		}
		System.out.println("Obrigado por fazer parte do BANCO DevInMoney.");
	}
	
	public static void ocultarTela() { //Este metódo é usado apenas para "limpar" a tela para 

		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println();
	}
}
