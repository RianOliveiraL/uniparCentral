package br.unipar.central.repositories;

import br.unipar.central.model.Telefone;
import br.unipar.central.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDAO {
    private static final String FIND_BY_PESSOA_ID =
            "SELECT ID, NUMERO, OPERADORA, RA, AGENCIA_ID, PESSOA_ID " +
                    "FROM TELEFONE " +
                    "WHERE PESSOA_ID = ?";

    private static final String FIND_BY_AGENCIA_ID =
            "SELECT ID, NUMERO, OPERADORA, RA, AGENCIA_ID, PESSOA_ID " +
                    "FROM TELEFONE " +
                    "WHERE AGENCIA_ID = ?";

    public List<Telefone> findByPessoaId(int pessoaId) throws SQLException {
        ArrayList<Telefone> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_PESSOA_ID);
            pstmt.setInt(1, pessoaId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Telefone telefone = this.resultSetToTelefone(rs);
                retorno.add(telefone);
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return retorno;
    }

    public List<Telefone> findByAgenciaId(int agenciaId) throws SQLException {
        ArrayList<Telefone> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_AGENCIA_ID);
            pstmt.setInt(1, agenciaId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Telefone telefone = this.resultSetToTelefone(rs);
                retorno.add(telefone);
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return retorno;
    }

    private Telefone resultSetToTelefone(ResultSet rs) throws SQLException {
        Telefone telefone = new Telefone();
        telefone.setId(rs.getInt("ID"));
        telefone.setNumero(rs.getString("NUMERO"));
        telefone.setOperadora(rs.getString("OPERADORA"));
        telefone.setRa(rs.getString("RA"));
        telefone.setAgencia_id(rs.getInt("AGENCIA_ID"));
        telefone.setPessoa_id(rs.getInt("PESSOA_ID"));

        return telefone;
    }
}

