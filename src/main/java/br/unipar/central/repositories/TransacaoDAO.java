package br.unipar.central.repositories;

import br.unipar.central.model.Transacao;
import br.unipar.central.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {
    private static final String INSERT =
            "INSERT INTO TRANSACAO " +
                    "(ID, DATAHORA, VALOR, TIPO, RA, CONTAORIGEM_ID, CONTADESTINO_ID) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL =
            "SELECT ID, DATAHORA, VALOR, TIPO, RA, CONTAORIGEM_ID, CONTADESTINO_ID " +
                    "FROM TRANSACAO ";

    private static final String FIND_BY_ID =
            "SELECT ID, DATAHORA, VALOR, TIPO, RA, CONTAORIGEM_ID, CONTADESTINO_ID " +
                    "FROM TRANSACAO " +
                    "WHERE ID = ?";

    private static final String DELETE_BY_ID =
            "DELETE FROM TRANSACAO WHERE ID = ?";

    private static final String UPDATE =
            "UPDATE TRANSACAO SET datahora = ?, valor = ?, tipo = ?, ra = ?, " +
                    "contaorigem_id = ?, contadestino_id = ? " +
                    "WHERE id = ?";

    public List<Transacao> findAll() throws SQLException {
        ArrayList<Transacao> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Transacao transacao = this.resultSetToTransacao(rs);
                retorno.add(transacao);
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

    public Transacao findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Transacao transacao = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                transacao = this.resultSetToTransacao(rs);
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return transacao;
    }

    public void insert(Transacao transacao) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setInt(1, transacao.getId());
            pstmt.setTimestamp(2, new Timestamp(transacao.getDataHora().getTime()));
            pstmt.setDouble(3, transacao.getValor());
            pstmt.setString(4, transacao.getTipo());
            pstmt.setString(5, transacao.getRa());
            pstmt.setInt(6, transacao.getContaOrigem().getId());
            pstmt.setInt(7, transacao.getContaDestino().getId());

            pstmt.executeUpdate();
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public void update(Transacao transacao) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setTimestamp(1, new Timestamp(transacao.getDataHora().getTime()));
            pstmt.setDouble(2, transacao.getValor());
            pstmt.setString(3, transacao.getTipo());
            pstmt.setString(4, transacao.getRa());
            pstmt.setInt(5, transacao.getContaOrigem().getId());
            pstmt.setInt(6, transacao.getContaDestino().getId());
            pstmt.setInt(7, transacao.getId());

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

    private static final String FIND_BY_ACCOUNT_ID =
            "SELECT ID, DATAHORA, VALOR, TIPO, RA, CONTAORIGEM_ID, CONTADESTINO_ID " +
                    "FROM TRANSACAO WHERE CONTAORIGEM_ID = ? OR CONTADESTINO_ID = ?";

    public List<Transacao> findTransactionsByAccountId(int accountId) throws SQLException {
        ArrayList<Transacao> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ACCOUNT_ID);
            pstmt.setInt(1, accountId);
            pstmt.setInt(2, accountId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Transacao transacao = this.resultSetToTransacao(rs);
                retorno.add(transacao);
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

    private Transacao resultSetToTransacao(ResultSet rs) throws SQLException {
        Transacao transacao = new Transacao();
        transacao.setId(rs.getInt("ID"));
        transacao.setDataHora(rs.getTimestamp("DATAHORA"));
        transacao.setValor(rs.getDouble("VALOR"));
        transacao.setTipo(rs.getString("TIPO"));
        transacao.setRa(rs.getString("RA"));
        transacao.setContaOrigem(new ContaDAO().findById(rs.getInt("CONTAORIGEM_ID")));
        transacao.setContaDestino(new ContaDAO().findById(rs.getInt("CONTADESTINO_ID")));

        return transacao;
    }
}

