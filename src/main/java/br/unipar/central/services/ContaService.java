package br.unipar.central.services;

import br.unipar.central.model.Conta;
import br.unipar.central.repositories.ContaDAO;

import java.sql.SQLException;
import java.util.List;

public class ContaService {
    private ContaDAO contaDAO;

    public ContaService() {
        this.contaDAO = new ContaDAO();
    }

    public Conta getContaById(int id) throws SQLException {
        try {
            return contaDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a conta por ID.", e);
        }
    }

    public void addConta(Conta conta) throws SQLException {
        try {
            contaDAO.insert(conta);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a conta.", e);
        }
    }

    public void updateConta(Conta conta) throws SQLException {
        try {
            contaDAO.update(conta);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a conta.", e);
        }
    }

    public void deleteConta(int id) throws SQLException {
        try {
            contaDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir a conta.", e);
        }
    }

    public List<Conta> getAllContas() throws SQLException {
        try {
            return contaDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as contas.", e);
        }
    }
}
