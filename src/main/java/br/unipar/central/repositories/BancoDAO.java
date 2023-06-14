package br.unipar.central.repositories;

import br.unipar.central.model.Banco;
import br.unipar.central.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoDAO {
    private static final String INSERT =
            "INSERT INTO BANCO " +
                    "(ID, NOME, RA) " +
                    "VALUES(?, ?, ?)";

    private static final String FIND_BY_ID =
            "SELECT ID, NOME, RA " +
                    "FROM BANCO " +
                    "WHERE ID = ?";

    public void insert(Banco banco) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setInt(1, banco.getId());
            pstmt.setString(2, banco.getNome());
            pstmt.setString(3, banco.getRa());

            pstmt.executeUpdate();
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public Banco findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Banco banco = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                banco = this.resultSetToBanco(rs);
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return banco;
    }

    private Banco resultSetToBanco(ResultSet rs) throws SQLException {
        Banco banco = new Banco();
        banco.setId(rs.getInt("ID"));
        banco.setNome(rs.getString("NOME"));
        banco.setRa(rs.getString("RA"));

        return banco;
    }
}

