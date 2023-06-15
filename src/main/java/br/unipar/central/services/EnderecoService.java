package br.unipar.central.services;

import br.unipar.central.model.Endereco;
import br.unipar.central.repositories.EnderecoDAO;

import java.sql.SQLException;
import java.util.List;

public class EnderecoService {
    private EnderecoDAO enderecoDAO;

    public EnderecoService() {
        this.enderecoDAO = new EnderecoDAO();
    }

    public Endereco getEnderecoById(int id) throws SQLException {
        try {
            return enderecoDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o endereço por ID.", e);
        }
    }

    public void addEndereco(Endereco endereco) throws SQLException {
        try {
            enderecoDAO.insert(endereco);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar o endereço.", e);
        }
    }

    public void updateEndereco(Endereco endereco) throws SQLException {
        try {
            enderecoDAO.update(endereco);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o endereço.", e);
        }
    }

    public void deleteEndereco(int id) throws SQLException {
        try {
            enderecoDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o endereço.", e);
        }
    }

    public List<Endereco> getAllEnderecos() throws SQLException {
        try {
            return enderecoDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os endereços.", e);
        }
    }
}
