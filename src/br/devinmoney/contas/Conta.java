package br.devinmoney.contas;

import java.util.ArrayList;

import br.devinmoney.operadores.Transacao;

public abstract class Conta {
	private String nome;
	private String cpf;
	private double renda;
	private String numConta;
	private TipoConta tipoConta;
	private Agencia agencia;
	private double saldo;
	private double chequeEspecial;
	private ArrayList<Transacao> transacoes = new ArrayList<>();
	
	public Conta(String nome, String cpf, double renda, String numConta, TipoConta tipoConta, Agencia agencia, double saldo, double chequeEspecial) {
		this.nome = nome;
		this.cpf = cpf;
		this.renda = renda;
		this.numConta = numConta;
		this.tipoConta = tipoConta;
		this.agencia = agencia;
		this.saldo = saldo;
		this.chequeEspecial = chequeEspecial;
	}
	public void credita(double vlr) {
		this.saldo += vlr;
		
	}
	public void rendJuros(int meses, double taxa) {
		this.saldo = this.saldo * Math.pow((1+(taxa/100)/12), meses);
	}
	public Conta() {
		
	}
	
	public abstract void debita(double vlr);
	

	
//getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getRenda() {
		return renda;
	}

	public void setRenda(double renda) {
		this.renda = renda;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getChequeEspecial() {
		if (this.tipoConta.equals(TipoConta.CONTA_CORRENTE)) {
			if (this.renda > 1000) {
				this.chequeEspecial = this.renda * 0.30;
			}
		}
		return chequeEspecial;
	}
	public void setChequeEspecial(double chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
 
	}
	public ArrayList<Transacao> getTransacoes() {
		return transacoes;
	}
	public void setTransacoes(ArrayList<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
	
}
