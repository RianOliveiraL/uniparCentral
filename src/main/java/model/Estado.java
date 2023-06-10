package model;

public class Estado extends Entidade {
    private String nome;
    private String sigla;
    private String ra;
    private int pais_id;

    public Estado(String nome, String sigla, String ra, int pais_id) {
        this.nome = nome;
        this.sigla = sigla;
        this.ra = ra;
        this.pais_id = pais_id;
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

    public int getPais_id() {
        return pais_id;
    }

    public void setPais_id(int pais_id) {
        this.pais_id = pais_id;
    }
}