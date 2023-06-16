package br.unipar.central.repositories;

import br.unipar.central.model.Conta;
import br.unipar.central.model.Agencia;
import br.unipar.central.model.Pessoa;
import br.unipar.central.model.Transacao;
import br.unipar.central.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    private static final String INSERT =
            "INSERT INTO CONTA " +
                    "(ID, NUMERO, DIGITO, TIPO, SALDO, RA, AGENCIA_ID, TITULAR_ID) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL =
            "SELECT ID, NUMERO, DIGITO, TIPO, SALDO, RA, AGENCIA_ID, TITULAR_ID " +
                    "FROM CONTA ";

    private static final String FIND_BY_ID =
            "SELECT ID, NUMERO, DIGITO, TIPO, SALDO, RA, AGENCIA_ID, TITULAR_ID " +
                    "FROM CONTA " +
                    "WHERE ID = ?";

    private static final String DELETE_BY_ID =
            "DELETE FROM CONTA WHERE ID = ?";

    private static final String UPDATE =
            "UPDATE CONTA SET NUMERO = ?, DIGITO = ?, TIPO = ?, SALDO = ?, RA = ?, " +
                    "AGENCIA_ID = ?, TITULAR_ID = ? " +
                    "WHERE ID = ?";

    public List<Conta> findAll() throws SQLException {
        ArrayList<Conta> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Conta conta = this.resultSetToConta(rs);
                retorno.add(conta);
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

    public Conta findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Conta conta = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                conta = this.resultSetToConta(rs);
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return conta;
    }

    public void insert(Conta conta) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setInt(1, conta.getId());
            pstmt.setString(2, conta.getNumero());
            pstmt.setString(3, conta.getDigito());
            pstmt.setString(4, conta.getTipo());
            pstmt.setDouble(5, conta.getSaldo());
            pstmt.setString(6, conta.getRa());
            pstmt.setInt(7, conta.getAgencia().getId());
            pstmt.setInt(8, conta.getTitular().getId());

            pstmt.executeUpdate();
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public void update(Conta conta) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, conta.getNumero());
            pstmt.setString(2, conta.getDigito());
            pstmt.setString(3, conta.getTipo());
            pstmt.setDouble(4, conta.getSaldo());
            pstmt.setString(5, conta.getRa());
            pstmt.setInt(6, conta.getAgencia().getId());
            pstmt.setInt(7, conta.getTitular().getId());
            pstmt.setInt(8, conta.getId());

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

    private static final String FIND_BY_NOME =
            "SELECT ID, NUMERO, DIGITO, TIPO, SALDO, RA, AGENCIA_ID, TITULAR_ID " +
                    "FROM CONTA " +
                    "JOIN PESSOA ON CONTA.TITULAR_ID = PESSOA.ID " +
                    "WHERE PESSOA.NOME = ?";

    private static final String FIND_BY_TELEFONE =
            "SELECT ID, NUMERO, DIGITO, TIPO, SALDO, RA, AGENCIA_ID, TITULAR_ID " +
                    "FROM CONTA " +
                    "JOIN TELEFONE ON CONTA.TITULAR_ID = TELEFONE.PESSOA_ID " +
                    "WHERE TELEFONE.NUMERO = ?";

    private static final String FIND_BY_NUMERO =
            "SELECT ID, NUMERO, DIGITO, TIPO, SALDO, RA, AGENCIA_ID, TITULAR_ID " +
                    "FROM CONTA " +
                    "WHERE NUMERO = ?";

    public Conta findByNome(String nome) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Conta conta = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_NOME);
            pstmt.setString(1, nome);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                conta = this.resultSetToConta(rs);
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return conta;
    }

    public Conta findByTelefone(String telefone) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Conta conta = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_TELEFONE);
            pstmt.setString(1, telefone);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                conta = this.resultSetToConta(rs);
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return conta;
    }

    public Conta findByNumero(String numeroConta) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Conta conta = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_NUMERO);
            pstmt.setString(1, numeroConta);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                conta = this.resultSetToConta(rs);
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return conta;
    }

    private Conta resultSetToConta(ResultSet rs) throws SQLException {
        Conta conta = new Conta();
        conta.setId(rs.getInt("ID"));
        conta.setNumero(rs.getString("NUMERO"));
        conta.setDigito(rs.getString("DIGITO"));
        conta.setTipo(rs.getString("TIPO"));
        conta.setSaldo(rs.getDouble("SALDO"));
        conta.setRa(rs.getString("RA"));
        conta.setAgencia(new AgenciaDAO().findById(rs.getInt("AGENCIA_ID")));
        conta.setTitular(new PessoaDAO().findById(rs.getInt("TITULAR_ID")));
        conta.setTransacoes(new TransacaoDAO().findTransactionsByAccountId(rs.getInt("ID")));

        return conta;
    }
}
