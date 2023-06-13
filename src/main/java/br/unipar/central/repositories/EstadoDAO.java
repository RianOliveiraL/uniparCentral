package br.unipar.central.repositories;

import br.unipar.central.model.Estado;
import br.unipar.central.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    private static final String INSERT =
            "INSERT INTO ESTADO " +
                    "(ID, NOME, SIGLA, RA, PAIS_ID) " +
                    "VALUES(?, ?, ?, ?, ?)";

    private static final String FIND_ALL =
            "SELECT ID, NOME, SIGLA, RA, PAIS_ID  " +
                    "FROM ESTADO ";

    private static final String FIND_BY_ID =
            "SELECT ID, NOME, SIGLA, RA, PAIS_ID  " +
                    "FROM ESTADO " +
                    "WHERE ID = ?";

    private static final String DELETE_BY_ID =
            "DELETE FROM ESTADO WHERE ID = ?";

    private static final String UPDATE =
            "UPDATE ESTADO SET nome = ?, sigla = ?, ra = ?, " +
                    " pais_id = ? " +
                    "WHERE id = ?";

    public List<Estado> findAll() throws SQLException {

        ArrayList<Estado> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Estado estado = new Estado();
                estado.setId(rs.getInt("ID"));
                estado.setNome(rs.getString("NOME"));
                estado.setRegistroAcademico(rs.getString("RA"));
                estado.setSigla(rs.getString("SIGLA"));
                estado.setPais(new PaisDAO().findById(rs.getInt("PAIS_ID")));

                retorno.add(estado);
            }

        } finally {

            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return retorno;
    }

    public Estado findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Estado estado = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                estado = new Estado();
                estado.setId(rs.getInt("ID"));
                estado.setNome(rs.getString("NOME"));
                estado.setRegistroAcademico(rs.getString("RA"));
                estado.setSigla(rs.getString("SIGLA"));
                estado.setPais(new PaisDAO().findById(rs.getInt("PAIS_ID")));

            }
        } finally {
            if (rs != null)
                rs.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }

        return estado;
    }

    public void insert(Estado estado) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setInt(1, estado.getId());
            pstmt.setString(2, estado.getNome());
            pstmt.setString(3, estado.getSigla());
            pstmt.setString(4, estado.getRegistroAcademico());
            pstmt.setInt(5, estado.getPais().getId());

        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public void update(Estado estado) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, estado.getNome());
            pstmt.setString(2, estado.getSigla());
            pstmt.setString(3, estado.getRegistroAcademico());
            pstmt.setInt(4, estado.getPais().getId());
            pstmt.setInt(5, estado.getId());

            pstmt.executeUpdate();

        } finally {
            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
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
            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }
    }
}
