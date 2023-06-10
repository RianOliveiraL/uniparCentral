package model;

public class Telefone extends Entidade {
    private String numero;
    private String operadora;
    private String ra;
    private int agencia_id;
    private int pessoa_id;

    public Telefone(String numero, String operadora, String ra, int agencia_id, int pessoa_id) {
        this.numero = numero;
        this.operadora = operadora;
        this.ra = ra;
        this.agencia_id = agencia_id;
        this.pessoa_id = pessoa_id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
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
}
