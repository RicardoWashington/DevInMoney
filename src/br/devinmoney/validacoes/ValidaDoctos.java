package br.devinmoney.validacoes;

import java.util.InputMismatchException;

public class ValidaDoctos {
			
		public static boolean validaCPF(String cpf) {
			cpf = removeCaracEsp(cpf);
			if (cpf.length() != 11) {
				return false;
			}		
			int sm = 0;
		    int peso = 10;
		    //Distribuindo os 9 primeiros dígitos,colocando os pesos 10, 
			//9, 8, 7, 6, 5, 4, 3, 2 abaixo da esquerda para a direita.
		    for (int i=0; i<9; i++) {
		    	int num = Integer.parseInt(cpf.substring(i, i+1));
		    	//Multiplicando o valor de cada caractere pelo seu peso e
		    	//realizando a soma dos resultado de cada caractere.
		    	sm = sm + (num * peso);
		        peso = peso - 1;

		     }
		     //O resultado obtido será divido por 11, sendo considerado o resto 
		     //da divisão, em seguida será subtraido por 11
		     int r = 11 - (sm % 11);
		     char dig10;
		     char dig11;
		     if ((sm % 11) < 2) {
		    	 dig10 = '0';
		     }else {
		    	 //Converteendo r em char (Necessário somar a 48 que é 0 na posição 48 da tab ASCII.
		    	 //poderia ter feito (char) (r + "0"), mas achei estranho 0 como string.
		    	 dig10 = (char) (r + 48); 
		     }    
		     // Calculo do 2o. Digito Verificador
	         sm = 0;
	         peso = 11;
	         for(int i=0; i<10; i++) {
	        	 int num = Integer.parseInt(cpf.substring(i, i+1));
	        	 sm = sm + (num * peso);
	        	 peso = peso - 1;
	         }
	         r = 11 - (sm % 11);
	         if ((sm % 11) < 2) {
	             dig11 = '0';
	         }else { 
	        	dig11 = (char)(r + 48);
	         }
		     // Verifica se os digitos calculados conferem com os digitos informados.
	         if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
	        	return(true);
	         }else{
	        	return (false);
	         }
			
			
		}
		
		private static String removeCaracEsp(String cpf) {
			cpf = cpf.replaceAll("[^0-9]", "");
			return cpf;
		}
	}
