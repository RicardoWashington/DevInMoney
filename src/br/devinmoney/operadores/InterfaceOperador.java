package br.devinmoney.operadores;

import java.time.LocalDate;
import java.util.ArrayList;

import br.devinmoney.contas.Conta;
import br.devinmoney.contas.ContaCorrente;

public interface InterfaceOperador {
	public void cadastrar(Conta conta);
	public ArrayList<Conta> listar();
	public Conta consultar(String numero) throws Exception;
	public Conta consultarConta(String numero) throws Exception;
	public void alterar(Conta cta) throws Exception;
	public void saque(String conta, double valor) throws Exception;
	public void deposito(String conta, double valor) throws Exception;
	public void transferencia(String contaOri, String contaDes, double valor) throws Exception;
	public void transacao(Conta conta, String acao, double valor, TipoTransacao tipoTransacao)throws Exception;
	public void rendeJuros(String conta, int meses, double taxa) throws Exception;
	public void simuInvest(String conta, int tipo) throws Exception;
}
