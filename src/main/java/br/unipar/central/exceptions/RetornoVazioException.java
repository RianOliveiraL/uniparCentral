package br.unipar.central.exceptions;

public class RetornoVazioException extends Exception {
    public RetornoVazioException(String campo) {
        super("O campo: " + campo + "não possuí nenhum retorno!");
    }
}
