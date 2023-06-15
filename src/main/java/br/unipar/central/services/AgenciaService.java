package br.unipar.central.services;

import br.unipar.central.model.Agencia;
import br.unipar.central.repositories.AgenciaDAO;

import java.sql.SQLException;
import java.util.List;

public class AgenciaService {
    private AgenciaDAO agenciaDAO;

    public AgenciaService() {
        this.agenciaDAO = new AgenciaDAO();
    }

    public Agencia getAgenciaById(int id) throws SQLException {
        try {
            return agenciaDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a agência por ID.", e);
        }
    }

    public void addAgencia(Agencia agencia) throws SQLException {
        try {
            agenciaDAO.insert(agencia);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a agência.", e);
        }
    }

    public void updateAgencia(Agencia agencia) throws SQLException {
        try {
            agenciaDAO.update(agencia);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a agência.", e);
        }
    }

    public void deleteAgencia(int id) throws SQLException {
        try {
            agenciaDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir a agência.", e);
        }
    }

    public List<Agencia> getAllAgencias() throws SQLException {
        try {
            return agenciaDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as agências.", e);
        }
    }
}
