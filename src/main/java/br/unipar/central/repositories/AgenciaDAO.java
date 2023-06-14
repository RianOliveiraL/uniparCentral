package br.unipar.central.repositories;

import br.unipar.central.model.Agencia;
import br.unipar.central.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDAO {

    private static final String INSERT =
            "INSERT INTO AGENCIA " +
                    "(ID, CODIGO, DIGITO, RAZAOSOCIAL, CNPJ, RA, BANCO_ID) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL =
            "SELECT ID, CODIGO, DIGITO, RAZAOSOCIAL, CNPJ, RA, BANCO_ID " +
                    "FROM AGENCIA";

    private static final String FIND_BY_ID =
            "SELECT ID, CODIGO, DIGITO, RAZAOSOCIAL, CNPJ, RA, BANCO_ID " +
                    "FROM AGENCIA WHERE ID = ?";

    private static final String DELETE_BY_ID =
            "DELETE FROM AGENCIA WHERE ID = ?";

    private static final String UPDATE =
            "UPDATE AGENCIA SET CODIGO = ?, DIGITO = ?, RAZAOSOCIAL = ?, CNPJ = ?, RA = ?, BANCO_ID = ? " +
                    "WHERE ID = ?";

    public List<Agencia> findAll() throws SQLException {
        ArrayList<Agencia> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Agencia agencia = this.resultSetToAgencia(rs);
                retorno.add(agencia);
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

    public Agencia findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Agencia agencia = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                agencia = this.resultSetToAgencia(rs);
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return agencia;
    }

    public void insert(Agencia agencia) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setInt(1, agencia.getId());
            pstmt.setString(2, agencia.getCodigo());
            pstmt.setString(3, agencia.getDigito());
            pstmt.setString(4, agencia.getRazaoSocial());
            pstmt.setString(5, agencia.getCnpj());
            pstmt.setString(6, agencia.getRa());
            pstmt.setInt(7, agencia.getBanco_id());

            pstmt.executeUpdate();
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public void update(Agencia agencia) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, agencia.getCodigo());
            pstmt.setString(2, agencia.getDigito());
            pstmt.setString(3, agencia.getRazaoSocial());
            pstmt.setString(4, agencia.getCnpj());
            pstmt.setString(5, agencia.getRa());
            pstmt.setInt(6, agencia.getBanco_id());
            pstmt.setInt(7, agencia.getId());

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

    private Agencia resultSetToAgencia(ResultSet rs) throws SQLException {
        Agencia agencia = new Agencia();
        agencia.setId(rs.getInt("ID"));
        agencia.setCodigo(rs.getString("CODIGO"));
        agencia.setDigito(rs.getString("DIGITO"));
        agencia.setRazaoSocial(rs.getString("RAZAOSOCIAL"));
        agencia.setCnpj(rs.getString("CNPJ"));
        agencia.setRa(rs.getString("RA"));
        agencia.setBanco_id(rs.getInt("BANCO_ID"));

        return agencia;
    }
}
