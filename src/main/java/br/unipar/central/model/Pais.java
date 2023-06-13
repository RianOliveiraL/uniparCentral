package br.unipar.central.model;

public class Pais extends Entidade {
    private String nome;
    private String sigla;

    public Pais(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public Pais() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
