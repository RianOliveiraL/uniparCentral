package model;

import java.util.Date;

public abstract class Entidade {
    protected Date dataCadastro;
    protected int id;

    // Construtor
    public Entidade() {
        this.dataCadastro = new Date();
    }

    // Getters e Setters
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
