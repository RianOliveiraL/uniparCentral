package model;

public class Pais extends Entidade {
    private String nome;
    private String sigla;
    private String ra;

    public Pais(String nome, String sigla, String ra) {
        this.nome = nome;
        this.sigla = sigla;
        this.ra = ra;
    }

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

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
