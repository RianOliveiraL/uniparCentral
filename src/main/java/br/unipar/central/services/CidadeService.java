package br.unipar.central.services;

import br.unipar.central.exceptions.CampoNaoInformadoException;
import br.unipar.central.exceptions.EntidadeNaoInformadaException;
import br.unipar.central.exceptions.RetornoVazioException;
import br.unipar.central.exceptions.TamanhoCampoInvalidoException;
import br.unipar.central.model.Cidade;
import br.unipar.central.repositories.CidadeDAO;

import java.sql.SQLException;
import java.util.List;

public class CidadeService {
    private void validar(Cidade cidade) throws
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        if (cidade == null) {
            throw new EntidadeNaoInformadaException("Cidade");
        }

        if (cidade.getNome() == null ||
                cidade.getNome().isEmpty() ||
                cidade.getNome().isBlank()) {
            throw new CampoNaoInformadoException("Nome");
        }

        if (cidade.getNome().length() > 60) {
            throw new TamanhoCampoInvalidoException("Nome", 60);
        }
    }

    public List<Cidade> findAll() throws SQLException {
        CidadeDAO cidadeDAO = new CidadeDAO();
        List<Cidade> resultado = cidadeDAO.findAll();

        return  resultado;

    }

    public Cidade findById(int id) throws SQLException,
            TamanhoCampoInvalidoException,
            RetornoVazioException {

        if (id <= 0)
            throw new TamanhoCampoInvalidoException("id", 1);

        CidadeDAO cidadeDAO = new CidadeDAO();

        Cidade retorno = cidadeDAO.findById(id);

        if (retorno == null) {
            throw new RetornoVazioException("id");
        }

        return retorno;
    }

    public void insert(Cidade cidade) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        validar(cidade);

        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.insert(cidade);
    }

    public void update(Cidade cidade) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        validar(cidade);
        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.update(cidade);
    }

    public void delete (int id) throws SQLException {
        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.delete(id);
    }
}
