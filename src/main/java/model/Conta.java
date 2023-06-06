package model;

import java.util.Date;

public class Conta {
    int numero;
    int digito;
    double saldo;
    int tipo;
    String ra;
    int agencia_id;
    int pessoa_id;
    Date datacadastro;

    public Conta(int numero, int digito, double saldo, int tipo, String ra, int agencia_id, int pessoa_id, Date datacadastro) {
        this.numero = numero;
        this.digito = digito;
        this.saldo = saldo;
        this.tipo = tipo;
        this.ra = ra;
        this.agencia_id = agencia_id;
        this.pessoa_id = pessoa_id;
        this.datacadastro = datacadastro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getDigito() {
        return digito;
    }

    public void setDigito(int digito) {
        this.digito = digito;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public int getAgencia_id() {
        return agencia_id;
    }

    public void setAgencia_id(int agencia_id) {
        this.agencia_id = agencia_id;
    }

    public int getPessoa_id() {
        return pessoa_id;
    }

    public void setPessoa_id(int pessoa_id) {
        this.pessoa_id = pessoa_id;
    }

    public Date getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Date datacadastro) {
        this.datacadastro = datacadastro;
    }
}
