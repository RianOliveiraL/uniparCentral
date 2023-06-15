package br.unipar.central.services;

import br.unipar.central.exceptions.CampoNaoInformadoException;
import br.unipar.central.exceptions.EntidadeNaoInformadaException;
import br.unipar.central.exceptions.RetornoVazioException;
import br.unipar.central.exceptions.TamanhoCampoInvalidoException;
import br.unipar.central.model.PessoaFisica;
import br.unipar.central.repositories.PessoaFisicaDAO;

import java.sql.SQLException;
import java.util.List;

public class PessoaFisicaService {

    private void validar(PessoaFisica pessoaFisica) throws
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        if (pessoaFisica == null) {
            throw new EntidadeNaoInformadaException("Pessoa Fisica");
        }

        if (pessoaFisica.getNome() == null ||
                pessoaFisica.getNome().isEmpty() ||
                pessoaFisica.getNome().isBlank()) {
            throw new CampoNaoInformadoException("Nome");
        }

        if (pessoaFisica.getCpf() == null ||
                pessoaFisica.getCpf().isEmpty() ||
                pessoaFisica.getCpf().isBlank()) {
            throw new CampoNaoInformadoException("Cep");
        }

        if (pessoaFisica.getRg() == null ||
                pessoaFisica.getRg().isEmpty() ||
                pessoaFisica.getRg().isBlank()) {
            throw new CampoNaoInformadoException("rg");
        }

        if (pessoaFisica.getCpf().length() > 11) {
            throw new TamanhoCampoInvalidoException("cpf", 11);
        }
    }

    public List<PessoaFisica> findAll() throws SQLException {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        List<PessoaFisica> resultado = pessoaFisicaDAO.findAll();

        return  resultado;

    }

    public PessoaFisica findById(int id) throws SQLException,
            TamanhoCampoInvalidoException,
            RetornoVazioException {

        if (id <= 0)
            throw new TamanhoCampoInvalidoException("id", 1);

        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();

        PessoaFisica retorno = pessoaFisicaDAO.findById(id);

        if (retorno == null) {
            throw new RetornoVazioException("id");
        }

        return retorno;
    }

    public void insert(PessoaFisica pessoaFisica) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        validar(pessoaFisica);

        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.insert(pessoaFisica);
    }

    public void update(PessoaFisica pessoaFisica) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        validar(pessoaFisica);
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.update(pessoaFisica);
    }

    public void delete (int id) throws SQLException {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.delete(id);
    }
}
