package br.unipar.central.services;

import br.unipar.central.model.Telefone;
import br.unipar.central.repositories.TelefoneDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelefoneService {
    private static final Logger LOGGER = Logger.getLogger(TelefoneService.class.getName());

    private TelefoneDAO telefoneDAO;

    public TelefoneService() {
        this.telefoneDAO = createDAO();
    }

    protected TelefoneDAO createDAO() {
        return new TelefoneDAO();
    }

    public List<Telefone> getTelefonesByPessoaId(int pessoaId) {
        try {
            return telefoneDAO.findByPessoaId(pessoaId);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar telefones da pessoa por ID: " + pessoaId, e);
            throw new RuntimeException("Erro ao buscar telefones da pessoa por ID.", e);
        }
    }

    public List<Telefone> getTelefonesByAgenciaId(int agenciaId) {
        try {
            return telefoneDAO.findByAgenciaId(agenciaId);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar telefones da agência por ID: " + agenciaId, e);
            throw new RuntimeException("Erro ao buscar telefones da agência por ID.", e);
        }
    }
}
