package br.unipar.central.repositories;

import br.unipar.central.model.Cidade;
import br.unipar.central.model.Pessoa;
import br.unipar.central.utils.DatabaseUtils;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private static final String INSERT =
            "INSERT INTO PESSOA " +
                    "(ID, EMAIL, RA) " +
                    "VALUES(?, ?, ?)";

    private static final String FIND_ALL =
            "SELECT ID, EMAIL, RA " +
                    "FROM PESSOA";

    private static final String FIND_BY_ID =
            "SELECT ID, EMAIL, RA " +
                    "FROM PESSOA " +
                    "WHERE ID = ?";

    private static final String DELETE_BY_ID =
            "DELETE FROM PESSOA WHERE ID = ?";

    private static final String UPDATE =
            "UPDATE PESSOA SET email = ?, ra = ? " +
                    "WHERE id = ?";

    public List<Pessoa> findAll() throws SQLException {

        ArrayList<Pessoa> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("ID"));
                pessoa.setEmail(rs.getString("EMAIL"));
                pessoa.setRegistroAcademico(rs.getString("RA"));

                retorno.add(pessoa);
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

    public Pessoa findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Pessoa pessoa = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setId(rs.getInt("ID"));
                pessoa.setEmail(rs.getString("EMAIL"));
                pessoa.setRegistroAcademico(rs.getString("RA"));
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return pessoa;
    }

    public void insert(Pessoa pessoa) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);

            pstmt.setInt(1, pessoa.getId());
            pstmt.setString(2, pessoa.getEmail());
            pstmt.setString(3, pessoa.getRegistroAcademico());

        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public void update(Pessoa pessoa) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setString(1, pessoa.getEmail());
            pstmt.setString(2, pessoa.getRegistroAcademico());
            pstmt.setInt(3, pessoa.getId());

            pstmt.executeUpdate();

        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public void delete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(DELETE_BY_ID);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }
}
