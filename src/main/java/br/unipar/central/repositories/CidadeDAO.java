package br.unipar.central.repositories;

import br.unipar.central.model.Cidade;
import br.unipar.central.utils.DatabaseUtils;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {
    private static final String INSERT =
            "INSERT INTO CIDADE " +
                    "(ID, NOME, RA, ESTADO_ID) " +
                    "VALUES(?, ?, ?, ?)";

    private static final String FIND_ALL =
            "SELECT ID, NOME, RA, ESTADO_ID " +
                    "FROM CIDADE ";

    private static final String FIND_BY_ID =
            "SELECT ID, NOME, RA, ESTADO_ID " +
                    "FROM CIDADE " +
                    "WHERE ID = ?";

    private static final String DELETE_BY_ID =
            "DELETE FROM CIDADE WHERE ID = ?";

    private static final String UPDATE =
            "UPDATE CIDADE SET nome = ?, ra = ?, " +
                    "estado_id = ?" +
                    "WHERE id = ?";

    public List<Cidade> findAll() throws SQLException {
        ArrayList<Cidade> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("ID"));
                cidade.setNome(rs.getString("NOME"));
                cidade.setRa(rs.getString("RA"));
                cidade.setEstado(new EstadoDAO().findById(rs.getInt("ESTADO_ID")));

                retorno.add(cidade);
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

    public Cidade findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cidade cidade = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                cidade = new Cidade();
                cidade.setId(rs.getInt("ID"));
                cidade.setNome(rs.getString("NOME"));
                cidade.setRegistroAcademico(rs.getString("RA"));
                cidade.setEstado(new EstadoDAO().findById(rs.getInt("ESTADO_ID")));
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return cidade;
    }

    public void insert(Cidade cidade) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setInt(1, cidade.getId());
            pstmt.setString(2, cidade.getNome());
            pstmt.setString(3, cidade.getRegistroAcademico());
            pstmt.setInt(4, cidade.getEstado().getId());

        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public void update(Cidade cidade) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, cidade.getNome());
            pstmt.setString(2, cidade.getRegistroAcademico());
            pstmt.setInt(3, cidade.getEstado().getId());
            pstmt.setInt(4, cidade.getId());

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
