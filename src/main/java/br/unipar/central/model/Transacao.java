package br.unipar.central.model;

import java.util.Date;

public class Transacao extends Entidade {
    private Date dataHora;
    private double valor;
    private String tipo; // Depósito, retirada, transferência, etc.
    private String ra; // Suponho que seja um campo específico, porém sem especificação do que é, coloquei como String
    private Conta contaOrigem;
    private Conta contaDestino;

    public Transacao() {
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
        this.ra = ra;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }
}