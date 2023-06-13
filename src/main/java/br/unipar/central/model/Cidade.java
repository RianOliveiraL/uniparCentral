package br.unipar.central.model;

public class Cidade extends Entidade {
    private String nome;
    private Estado estado;
    private String ra;

    public Cidade(String nome, Estado estado, String ra) {
        this.nome = nome;
        this.estado = estado;
        this.ra = ra;
    }

    public Cidade() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
