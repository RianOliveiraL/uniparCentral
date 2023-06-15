package br.unipar.central.services;

import br.unipar.central.exceptions.CampoNaoInformadoException;
import br.unipar.central.exceptions.EntidadeNaoInformadaException;
import br.unipar.central.exceptions.RetornoVazioException;
import br.unipar.central.exceptions.TamanhoCampoInvalidoException;
import br.unipar.central.model.Endereco;
import br.unipar.central.repositories.EnderecoDAO;

import java.sql.SQLException;
import java.util.List;

public class EnderecoService {
    private void validar(Endereco endereco) throws
            EntidadeNaoInformadaException,
            CampoNaoInformadoException {

        if (endereco == null) {
            throw new EntidadeNaoInformadaException("endereco");
        }

        if (endereco.getLogradouro() == null ||
                endereco.getLogradouro().isEmpty() ||
                endereco.getLogradouro().isBlank()) {
            throw new CampoNaoInformadoException("Nome");
        }

        if (endereco.getNumero() == null ||
                endereco.getNumero().isEmpty() ||
                endereco.getNumero().isBlank()) {
            throw new CampoNaoInformadoException("numero");
        }

        if (endereco.getBairro() == null ||
                endereco.getBairro().isEmpty() ||
                endereco.getBairro().isBlank()) {
            throw new CampoNaoInformadoException("bairro");
        }

        if (endereco.getCep() == null ||
                endereco.getCep().isEmpty() ||
                endereco.getCep().isBlank()) {
            throw new CampoNaoInformadoException("cep");
        }
    }

    public List<Endereco> findAll() throws SQLException {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        List<Endereco> resultado =enderecoDAO.findAll();

        return resultado;
    }

    public Endereco findById(int id) throws SQLException,
            TamanhoCampoInvalidoException,
            RetornoVazioException {

        if (id <= 0) {
            throw new TamanhoCampoInvalidoException("id", 0);
        }

        EnderecoDAO enderecoDAO = new EnderecoDAO();

        Endereco retorno = enderecoDAO.findById(id);

        if (retorno == null) {
            throw new RetornoVazioException("id");
        }

        return retorno;
    }

    public void insert(Endereco endereco) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException {
        validar(endereco);

        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.insert(endereco);
    }

    public void update(Endereco endereco) throws SQLException,
            EntidadeNaoInformadaException,
            CampoNaoInformadoException,
            TamanhoCampoInvalidoException {
        validar(endereco);

        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.update(endereco);
    }

    public void delete(int id) throws SQLException {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.delete(id);
    }
}
