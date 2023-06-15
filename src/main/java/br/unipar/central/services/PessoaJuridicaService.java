package br.unipar.central.services;

import br.unipar.central.exceptions.CampoNaoInformadoException;
import br.unipar.central.exceptions.EntidadeNaoInformadaException;
import br.unipar.central.exceptions.RetornoVazioException;
import br.unipar.central.exceptions.TamanhoCampoInvalidoException;
import br.unipar.central.model.PessoaJuridica;
import br.unipar.central.repositories.PessoaJuridicaDAO;

import java.sql.SQLException;
import java.util.List;

public class PessoaJuridicaService {
    private void validar(PessoaJuridica pessoaJuridica) throws
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        if (pessoaJuridica == null) {
            throw new EntidadeNaoInformadaException("Pessoa Juridica");
        }

        if (pessoaJuridica.getCnpj() == null ||
                pessoaJuridica.getCnpj().isEmpty() ||
                pessoaJuridica.getCnpj().isBlank()) {
            throw new CampoNaoInformadoException("cnpj");
        }

        if (pessoaJuridica.getRazaoSocial() == null ||
                pessoaJuridica.getRazaoSocial().isEmpty() ||
                pessoaJuridica.getRazaoSocial().isBlank()) {
            throw new CampoNaoInformadoException("Razão social");
        }

        if (pessoaJuridica.getFantasia() == null ||
                pessoaJuridica.getFantasia().isEmpty() ||
                pessoaJuridica.getFantasia().isBlank()) {
            throw new CampoNaoInformadoException("fantasia");
        }

        if (pessoaJuridica.getCnpj().length() > 14) {
            throw new TamanhoCampoInvalidoException("cnpj", 14);
        }
    }

    public List<PessoaJuridica> findAll() throws SQLException {
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        List<PessoaJuridica> resultado = pessoaJuridicaDAO.findAll();

        return  resultado;

    }

    public PessoaJuridica findById(int id) throws SQLException,
            TamanhoCampoInvalidoException,
            RetornoVazioException {

        if (id <= 0)
            throw new TamanhoCampoInvalidoException("id", 1);

        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        PessoaJuridica retorno = pessoaJuridicaDAO.findById(id);

        if (retorno == null) {
            throw new RetornoVazioException("id");
        }

        return retorno;
    }

    public void insert(PessoaJuridica pessoaJuridica) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        validar(pessoaJuridica);

        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.insert(pessoaJuridica);
    }

    public void update(PessoaJuridica pessoaJuridica) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {

        validar(pessoaJuridica);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.update(pessoaJuridica);
    }

    public void delete (int id) throws SQLException {
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.delete(id);
    }
}
