package br.unipar.central.services;

import br.unipar.central.exceptions.CampoNaoInformadoException;
import br.unipar.central.exceptions.EntidadeNaoInformadaException;
import br.unipar.central.exceptions.RetornoVazioException;
import br.unipar.central.exceptions.TamanhoCampoInvalidoException;
import br.unipar.central.model.Pais;
import br.unipar.central.repositories.PaisDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisService {

    private void validar(Pais pais) throws
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        if (pais == null) {
            throw new EntidadeNaoInformadaException("Pais");
        }

        if (pais.getNome() == null ||
                pais.getNome().isEmpty() ||
                pais.getNome().isBlank()) {
            throw new CampoNaoInformadoException("Nome");
        }

        if (pais.getSigla()== null ||
                pais.getSigla().isEmpty() ||
                pais.getSigla().isBlank()) {
            throw new CampoNaoInformadoException("Sigla");
        }

        if (!(pais.getSigla().length() == 2)) {
            throw new TamanhoCampoInvalidoException("Sigla", 2);
        }

        if (pais.getNome().length() > 60) {
            throw new TamanhoCampoInvalidoException("Nome", 60);
        }
    }

    public List<Pais> findAll() throws SQLException {

        PaisDAO paisDAO = new PaisDAO();
        List<Pais> resultado =  paisDAO.findAll();

        return resultado;

    }

    public Pais findById(int id) throws SQLException,
            TamanhoCampoInvalidoException,
            RetornoVazioException {

        if (id <= 0)
            throw new TamanhoCampoInvalidoException("id", 1);

        PaisDAO paisDAO = new PaisDAO();

        Pais retorno = paisDAO.findById(id);

        if (retorno == null)
            throw new RetornoVazioException("id");

        return retorno;

    }

    public void insert(Pais pais) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        validar(pais);

        PaisDAO paisDAO = new PaisDAO();
        paisDAO.insert(pais);

    }

    public void update(Pais pais) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        validar(pais);
        PaisDAO paisDAO = new PaisDAO();
        paisDAO.update(pais);
    }

    public void delete(int id) throws SQLException {
        PaisDAO paisDAO = new PaisDAO();
        paisDAO.delete(id);
    }
}
