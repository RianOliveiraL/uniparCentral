package br.unipar.central.model;

public class Agencia extends Entidade {
    private String codigo;
    private String digito;
    private String razaoSocial;
    private String cnpj;
    private String ra;
    private int banco_id;

    public Agencia(String codigo, String digito, String razaoSocial, String cnpj, String ra, int banco_id) {
        this.codigo = codigo;
        this.digito = digito;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.ra = ra;
        this.banco_id = banco_id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public int getBanco_id() {
        return banco_id;
    }

    public void setBanco_id(int banco_id) {
        this.banco_id = banco_id;
    }
}
