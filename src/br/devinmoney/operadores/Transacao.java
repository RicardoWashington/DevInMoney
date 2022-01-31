package br.devinmoney.operadores;

import java.time.LocalDate;

import br.devinmoney.contas.Conta;

public class Transacao {
	private Conta conta;
	private String id;
	private LocalDate data;
	private String complemento;
	private double valor;
	private TipoTransacao tipoTransacao;
	private int contTransacoes;
	
	public Transacao() {
		
	}
	
	public Transacao(Conta conta, String id, LocalDate data, String complemento, double valor, TipoTransacao tipoTransacao) {
		this.conta = conta;
		this.id = id;
		this.data = data;
		this.complemento = complemento;
		this.valor = valor;
		this.tipoTransacao = tipoTransacao;
	}
	
	public String geraSeqTransacao() {
		int num = contTransacoes++;
		return Integer.toString(num);
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
}
