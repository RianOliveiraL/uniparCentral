package br.unipar.central.services;

import br.unipar.central.model.Estado;
import br.unipar.central.repositories.EstadoDAO;

import java.sql.SQLException;
import java.util.List;

public class EstadoService {
    private EstadoDAO estadoDAO;

    public EstadoService() {
        this.estadoDAO = new EstadoDAO();
    }

    public List<Estado> getAllEstados() {
        try {
            return estadoDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os estados.", e);
        }
    }

    public Estado getEstadoById(int id) {
        try {
            return estadoDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o estado por ID.", e);
        }
    }

    public void addEstado(Estado estado) {
        try {
            estadoDAO.insert(estado);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar o estado.", e);
        }
    }

    public void updateEstado(Estado estado) {
        try {
            estadoDAO.update(estado);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o estado.", e);
        }
    }

    public void deleteEstado(int id) {
        try {
            estadoDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o estado.", e);
        }
    }
}
