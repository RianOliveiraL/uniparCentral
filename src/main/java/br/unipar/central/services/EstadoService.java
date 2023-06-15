package br.unipar.central.services;

import br.unipar.central.exceptions.CampoNaoInformadoException;
import br.unipar.central.exceptions.EntidadeNaoInformadaException;
import br.unipar.central.exceptions.RetornoVazioException;
import br.unipar.central.exceptions.TamanhoCampoInvalidoException;
import br.unipar.central.model.Estado;
import br.unipar.central.repositories.EstadoDAO;

import java.sql.SQLException;
import java.util.List;

public class EstadoService {

    private void validar(Estado estado) throws
            TamanhoCampoInvalidoException,
            CampoNaoInformadoException,
            EntidadeNaoInformadaException {

        if (estado == null) {
            throw new EntidadeNaoInformadaException("Estado");
        }

        if (estado.getNome() == null ||
                estado.getNome().isEmpty() ||
                estado.getNome().isBlank()) {
            throw new CampoNaoInformadoException("Nome");
        }

        if (estado.getSigla()== null ||
                estado.getSigla().isEmpty() ||
                estado.getSigla().isBlank()) {
            throw new CampoNaoInformadoException("Sigla");
        }

        if (!(estado.getSigla().length() == 2)) {
            throw new TamanhoCampoInvalidoException("Sigla", 2);
        }

        if (estado.getNome().length() > 60) {
            throw new TamanhoCampoInvalidoException("Nome", 60);
        }
    }

    public List<Estado> findAll() throws SQLException {

        EstadoDAO estadoDAO = new EstadoDAO();
        List<Estado> resultado = estadoDAO.findAll();

        return resultado;

    }

    public Estado findById(int id) throws
            SQLException,
            TamanhoCampoInvalidoException,
            RetornoVazioException {

        if (id <= 0)
            throw new TamanhoCampoInvalidoException("id", 1);

        EstadoDAO estadoDAO = new EstadoDAO();

        Estado retorno = estadoDAO.findById(id);

        if (retorno == null)
            throw new RetornoVazioException("id");

        return retorno;
    }

    public void insert(Estado estado) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        validar(estado);

        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.insert(estado);

    }

    public void update(Estado estado) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        validar(estado);
        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.update(estado);
    }

    public void delete(int id) throws SQLException {
        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.delete(id);
    }
}
