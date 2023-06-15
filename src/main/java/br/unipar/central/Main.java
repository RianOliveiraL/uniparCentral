package br.unipar.central;

import br.unipar.central.model.Pais;
import br.unipar.central.services.PaisService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        PaisService paisService = new PaisService();

        List<Pais> listaPais = paisService.findAll();

        System.out.println(listaPais.toString());
    }
}
