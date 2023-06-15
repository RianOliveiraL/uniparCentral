package br.unipar.central.services;

import br.unipar.central.model.Cidade;
import br.unipar.central.repositories.CidadeDAO;

import java.sql.SQLException;
import java.util.List;

public class CidadeService {
    private CidadeDAO cidadeDAO;

    public CidadeService() {
        this.cidadeDAO = new CidadeDAO();
    }

    public Cidade getCidadeById(int id) throws SQLException {
        try {
            return cidadeDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a cidade por ID.", e);
        }
    }

    public void addCidade(Cidade cidade) throws SQLException {
        try {
            cidadeDAO.insert(cidade);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a cidade.", e);
        }
    }

    public void updateCidade(Cidade cidade) throws SQLException {
        try {
            cidadeDAO.update(cidade);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a cidade.", e);
        }
    }

    public void deleteCidade(int id) throws SQLException {
        try {
            cidadeDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir a cidade.", e);
        }
    }

    public List<Cidade> getAllCidades() throws SQLException {
        try {
            return cidadeDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as cidades.", e);
        }
    }
}
