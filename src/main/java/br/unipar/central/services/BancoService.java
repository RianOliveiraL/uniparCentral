package br.unipar.central.services;

import br.unipar.central.model.Banco;
import br.unipar.central.repositories.BancoDAO;

import java.sql.SQLException;
import java.util.List;

public class BancoService {
    private BancoDAO bancoDAO;

    public BancoService() {
        this.bancoDAO = new BancoDAO();
    }

    public Banco getBancoById(int id) throws SQLException {
        try {
            return bancoDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o banco por ID.", e);
        }
    }

    public void addBanco(Banco banco) throws SQLException {
        try {
            bancoDAO.insert(banco);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar o banco.", e);
        }
    }

    public void updateBanco(Banco banco) throws SQLException {
        try {
            bancoDAO.update(banco);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o banco.", e);
        }
    }

    public void deleteBanco(int id) throws SQLException {
        try {
            bancoDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o banco.", e);
        }
    }

    public List<Banco> getAllBancos() throws SQLException {
        try {
            return bancoDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os bancos.", e);
        }
    }
}
