package br.unipar.central.exceptions;

public class EntidadeNaoInformadaException extends Exception {

    public EntidadeNaoInformadaException(String entidade) {
        super(entidade + "entidade não pode ser vazia!");
    }
}
