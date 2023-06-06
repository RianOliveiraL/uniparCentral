package model;

import java.util.Date;

public class Transacao {
    double valor;
    int tipo;
    String ra;
    int conta_origem;
    int conta_destino;
    Date datacadastro;
    Date datahora;


    public Transacao(double valor, int tipo, String ra, int conta_origem, int conta_destino, Date datacadastro, Date datahora) {
        this.valor = valor;
        this.tipo = tipo;
        this.ra = ra;
        this.conta_origem = conta_origem;
        this.conta_destino = conta_destino;
        this.datacadastro = datacadastro;
        this.datahora = datahora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public int getConta_origem() {
        return conta_origem;
    }

    public void setConta_origem(int conta_origem) {
        this.conta_origem = conta_origem;
    }

    public int getConta_destino() {
        return conta_destino;
    }

    public void setConta_destino(int conta_destino) {
        this.conta_destino = conta_destino;
    }

    public Date getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Date datacadastro) {
        this.datacadastro = datacadastro;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }
}
