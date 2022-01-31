package br.devinmoney.contas;

public class ContaCorrente extends Conta{
	
	
	
	public ContaCorrente(String nome, String cpf, double renda, String numConta, TipoConta tipoConta, Agencia agencia,
			double saldo, double chequeEspecial) {
		super(nome, cpf, renda, numConta, tipoConta, agencia, saldo, chequeEspecial);
		
		 
	}

	@Override
	public void debita(double vlr) {
		if ((super.getSaldo() + super.getChequeEspecial() - vlr) < 0){
			System.out.println("Transação não realizada! Sem saldo suficiente.");
		}else {
			super.setSaldo(super.getSaldo() - vlr);
		}
		
		
	}

		
}
	
	


	
	

