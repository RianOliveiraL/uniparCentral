package br.unipar.central.services;

import br.unipar.central.exceptions.CampoNaoInformadoException;
import br.unipar.central.exceptions.EntidadeNaoInformadaException;
import br.unipar.central.exceptions.RetornoVazioException;
import br.unipar.central.exceptions.TamanhoCampoInvalidoException;
import br.unipar.central.model.Pessoa;
import br.unipar.central.repositories.PessoaDAO;

import java.sql.SQLException;
import java.util.List;

public class PessoaService {

    private void validar(Pessoa pessoa) throws
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        if (pessoa == null) {
            throw new EntidadeNaoInformadaException("pessoa");
        }

        if (pessoa.getEmail() == null ||
                pessoa.getEmail().isEmpty() ||
                pessoa.getEmail().isBlank()) {
            throw new CampoNaoInformadoException("email");
        }

        if (pessoa.getEmail().length() > 60) {
            throw new TamanhoCampoInvalidoException("email", 60);
        }
    }

    public List<Pessoa> findAll() throws SQLException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> retorno = pessoaDAO.findAll();

        return retorno;
    }

    public Pessoa findById(int id) throws SQLException,
            TamanhoCampoInvalidoException,
            RetornoVazioException {
        if (id <= 0) {
            throw new TamanhoCampoInvalidoException("id", 1);
        }

        PessoaDAO pessoaDAO = new PessoaDAO();

        Pessoa retorno = pessoaDAO.findById(id);

        if (retorno == null) {
            throw new RetornoVazioException("id");
        }

        return retorno;
    }

    public void insert(Pessoa pessoa) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        validar(pessoa);

        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.insert(pessoa);
    }

    public void update(Pessoa pessoa) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        validar(pessoa);
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.update(pessoa);
    }

    public void delete(int id) throws SQLException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.delete(id);
    }
}
