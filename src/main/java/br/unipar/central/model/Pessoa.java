package br.unipar.central.model;

public class Pessoa extends Entidade {
    private String email;
    private String ra;

    public Pessoa(String email, String ra) {
        this.email = email;
        this.ra = ra;
    }

    public Pessoa() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}