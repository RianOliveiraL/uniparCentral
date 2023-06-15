package br.unipar.central.exceptions;

public class CampoNaoInformadoException extends Exception {

    public CampoNaoInformadoException(String campo) {
        super("O campo: " + campo + "não pode ser vazio!");
    }
}
