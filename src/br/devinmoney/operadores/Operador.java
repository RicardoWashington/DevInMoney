package br.devinmoney.operadores;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import br.devinmoney.contas.Conta;
import br.devinmoney.contas.ContaCorrente;
import br.devinmoney.contas.ContaPoupanca;
import br.devinmoney.contas.TipoConta;

public class Operador implements InterfaceOperador{
	private ArrayList<Conta> contas;
	private Transacao transacao = new Transacao();
	private LocalDate data, novaData ;
	private double saldoAnt, saldoAtu, tx;
	private int num = 20220000;
	 
	
	public Operador() {
		this.contas = new ArrayList();
	}

	@Override
	public void cadastrar(Conta conta) {
		if(conta.getTipoConta() != TipoConta.CONTA_CORRENTE) {
			if(conta.getSaldo() >= 0) {
				this.contas.add(conta);
			}
		}else {	
			this.contas.add(conta);
		}
		
	}

	@Override
	public ArrayList<Conta> listar() {
		if(this.contas.size()>0) {
			return this.contas;
		}
		return null;
	}
	
	public String geraNumConta() {
		int conta = num++;
		return Integer.toString(conta);
	}
	
	

	@Override
	public void saque(String conta, double valor) throws Exception {
		if (valor >= 0) {
			Conta cta = this.consultar(conta);
			if (cta.getSaldo() - valor >= 0) {
				cta.debita(valor);
				this.alterar(cta);
				transacao(cta, "SAQUE", valor, TipoTransacao.DÉBITO);
				System.out.println("Saque efetuado com sucesso!");
			}else if (cta.getSaldo() + cta.getChequeEspecial() - valor >= 0){
				cta.debita(valor);
				this.alterar(cta);
				transacao(cta, "SAQUE", valor, TipoTransacao.DÉBITO);
				System.out.println("Saque efetuado com sucesso!");
			}else{
				System.out.println("Conta sem saldo. Operação não realizada");
			}
			System.out.println("------------------------------------------------");
	  		System.out.println(String.format("%48s", "Seu saldo atual é: " + String.format("%.2f", cta.getSaldo())));
	  		System.out.println("------------------------------------------------");
	    	
		}else {
			throw new Exception();
		}
		
	}

	@Override
	public Conta consultar(String numero) throws Exception {
		Conta cta = null;
		if (this.contas.size() > 0) {
			for (int i = 0; i < this.contas.size(); i++) {
				if(this.contas.get(i).getNumConta().equals(numero)) {
					cta = this.contas.get(i);
					break;
				}
			}
			if (cta != null) {
				return cta;
			}else {
				throw new Exception();
			}
		}else {
			System.out.println("Nenhuma conta cadastrada");
			return cta;//throw new Exception();
		}
		
	}

	@Override
	public void alterar(Conta cta) throws Exception {
		if(this.contas.size() >0) {
			Conta c = this.consultar(cta.getNumConta());
			c = cta;
		}
	}

	@Override
	public void deposito(String conta, double valor) throws Exception {
		if (valor >= 0) {
			Conta cta = this.consultar(conta);
			cta.credita(valor);
			this.alterar(cta);
			transacao(cta, "DEPOSITO", valor, TipoTransacao.CRÉDITO);
		}else {
			throw new Exception();
		}
		
	}

	@Override
	public void transferencia(String contaOri, String contaDes, double valor) throws Exception {
		if (valor >= 0) {
			if (!contaOri.equals(contaDes)) {
				Conta ctaOri = this.consultar(contaOri);
				Conta ctaDes = this.consultar(contaDes);
				ctaOri.debita(valor);
				ctaDes.credita(valor);
				this.alterar(ctaOri);
				this.alterar(ctaDes);
				transacao(ctaOri, "TRANSFERENCIA", valor, TipoTransacao.DÉBITO);
				transacao(ctaDes, "TRANSFERENCIA", valor, TipoTransacao.CRÉDITO);
				System.out.println("------------------------------------------------");
		  		System.out.println("-Transferência realizada com sucesso!-");
		  		if (data.now().getDayOfWeek().equals(DayOfWeek.SATURDAY) || data.now().getDayOfWeek().equals(DayOfWeek.SUNDAY) ) {
					System.out.println("Os movimentos gerados no final de"
							+ "semana serão processados no próximo dia útil");
				}
		  		System.out.println("------------------------------------------------");
		  	
			}else {
				System.out.println("Não é possível transferir para a mesma conta!");
			}
		}else {
			throw new Exception();
		}
		
	}

	@Override
	public Conta consultarConta(String numero) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transacao(Conta conta, String acao, double valor,
			TipoTransacao tipoTransacao) throws Exception {
		if (acao.equals("TRANSFERENCIA")) {
			if (data.now().getDayOfWeek().equals(DayOfWeek.SATURDAY)  ) {
				novaData = data.now().plusDays(2);
			}else if (data.now().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				novaData = data.now().plusDays(1);
			}else {
				novaData = data.now();
			}
		}else {
			novaData = data.now();
		}
		transacao = new Transacao(conta,transacao.geraSeqTransacao(),novaData,acao,
				  					valor, tipoTransacao);
		conta.getTransacoes().add(transacao);
	}

	@Override
	public void rendeJuros(String conta, int meses, double taxa) throws Exception {
		if (meses > 0 && taxa > 0) {
			Conta cta = this.consultar(conta);
			saldoAnt = cta.getSaldo();
			cta.rendJuros(meses, taxa);
			this.alterar(cta);
			saldoAtu = cta.getSaldo();
			transacao(cta, "REND JUROS", saldoAtu - saldoAnt, TipoTransacao.CRÉDITO);
		
		}
		
	}

	@Override
	public void simuInvest(String conta, int tipo) throws Exception {
		Conta cta = this.consultar(conta);
		saldoAnt = cta.getSaldo();
		if (tipo == 1) {
			tx = 13.00;
		}else {
			tx = 16.00;
		}
		cta.rendJuros(12, tx);
				
	}

	

}
