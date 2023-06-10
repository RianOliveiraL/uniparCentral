package model;

import java.util.List;

public class Conta extends Entidade {
    private String numero;
    private String digito;
    private String tipo;
    private double saldo;
    private String ra;
    private Agencia agencia;
    private Pessoa titular;
    private List<Transacao> transacoes;

    public Conta(String numero, String digito, String tipo, double saldo, String ra, Agencia agencia, Pessoa titular, List<Transacao> transacoes) {
        this.numero = numero;
        this.digito = digito;
        this.tipo = tipo;
        this.saldo = saldo;
        this.ra = ra;
        this.agencia = agencia;
        this.titular = titular;
        this.transacoes = transacoes;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}