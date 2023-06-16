package br.unipar.central.repositories;

import br.unipar.central.model.PessoaFisica;
import br.unipar.central.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    public static final String INSERT =
            "INSERT INTO PESSOAFISICA " +
                    "(NOME, CPF, RG, DATANASCIMENTO, PESSOA_ID) " +
                    "VALUES(?, ?, ?, ?, ?)";

    private static final String FIND_ALL =
            "SELECT NOME, CPF, RG, DATANASCIMENTO, PESSOA_ID " +
                    "FROM PESSOAFISICA";

    private static final String FIND_BY_ID =
            "SELECT NOME, CPF, RG, DATANASCIMENTO, PESSOA_ID " +
                    "FROM PESSOAFISICA " +
                    "WHERE PESSOA_ID = ?";

    private static final String DELETE_BY_ID =
            "DELETE FROM PESSOAFISICA WHERE ID = ?";


    private static final String UPDATE =
            "UPDATE PESSOAFISICA SET nome = ?, cpf = ?, rg = ?, datanascimento = ?, " +
                    "pessoa_id = ?" +
                    "WHERE pessoa_id = ?";

    public List<PessoaFisica> findAll() throws SQLException {

        ArrayList<PessoaFisica> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setNome(rs.getString("NOME"));
                pessoaFisica.setRg(rs.getString("RG"));
                pessoaFisica.setCpf(rs.getString("CPF"));
                pessoaFisica.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
                pessoaFisica.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));

                retorno.add(pessoaFisica);
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

    public PessoaFisica findById(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PessoaFisica pessoaFisica = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                pessoaFisica = new PessoaFisica();
                pessoaFisica.setNome(rs.getString("NOME"));
                pessoaFisica.setRg(rs.getString("RG"));
                pessoaFisica.setCpf(rs.getString("CPF"));
                pessoaFisica.setDataNascimento(rs.getDate("DATANASCIMENTO"));
                pessoaFisica.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return pessoaFisica;
    }

    public void insert(PessoaFisica pessoaFisica) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);

            pstmt.setString(1, pessoaFisica.getNome());
            pstmt.setString(2, pessoaFisica.getCpf());
            pstmt.setString(3, pessoaFisica.getRg());
            pstmt.setDate(4, (Date) pessoaFisica.getDataNascimento());
            pstmt.setInt(5, pessoaFisica.getPessoa().getId());

        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public void update(PessoaFisica pessoaFisica) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setString(1, pessoaFisica.getNome());
            pstmt.setString(2, pessoaFisica.getCpf());
            pstmt.setString(3, pessoaFisica.getRg());
            pstmt.setDate(4, (Date) pessoaFisica.getDataNascimento());
            pstmt.setInt(5, pessoaFisica.getPessoa().getId());
            pstmt.setInt(6, pessoaFisica.getId());

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
