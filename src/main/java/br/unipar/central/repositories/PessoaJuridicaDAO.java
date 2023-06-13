package br.unipar.central.repositories;

import br.unipar.central.model.Pessoa;
import br.unipar.central.model.PessoaJuridica;
import br.unipar.central.utils.DatabaseUtils;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    private static final String INSERT =
            "INSERT INTO PESSOAJURIDICA " +
                    "(RAZAOSOCIAL, CNPJ, CNAEPRINCIPAL, FANTASIA, PESSOA_ID) " +
                    "VALUES(?, ?, ?, ?, ?)";

    private static final String FIND_ALL =
            "SELECT RAZAOSOCIAL, CNPJ, CNAEPRINCIPAL, FANTASIA, PESSOA_ID " +
                    "FROM PESSOAJURIDICA";

    private static final String FIND_BY_ID =
            "SELECT RAZAOSOCIAL, CNPJ, CNAEPRINCIPAL, FANTASIA, PESSOA_ID " +
                    "FROM PESSOAJURIDICA " +
                    "WHERE PESSOA_ID = ?";

    private static final String DELETE_BY_ID =
            "DELETE FROM PESSOAJURIDICA WHERE PESSOA_ID = ?";

    private static final String UPDATE =
            "UPDATE PESSOAJURIDICA SET razaosocial = ?, cnpj = ?, cnaeprincipal = ?, fantasia = ? " +
                    "WHERE Pessoa_id = ?";

    private List<PessoaJuridica> findAll() throws SQLException {

        ArrayList<PessoaJuridica> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
                pessoaJuridica.setCnpj(rs.getString("CNPJ"));
                pessoaJuridica.setFantasia(rs.getString("FANTASIA"));
                pessoaJuridica.setRazaoSocial(rs.getString("RAZAOSOCIAL"));
                pessoaJuridica.setCnaePrincipal(rs.getString("CNAEPRINCIPAL"));
                pessoaJuridica.setDataCadastro(rs.getTimestamp("DATACADASTRO"));

                retorno.add(pessoaJuridica);
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

    public PessoaJuridica findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PessoaJuridica pessoaJuridica = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                pessoaJuridica = new PessoaJuridica();

                pessoaJuridica.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
                pessoaJuridica.setCnpj(rs.getString("CNPJ"));
                pessoaJuridica.setRazaoSocial(rs.getString("RAZAOSOCIAL"));
                pessoaJuridica.setFantasia(rs.getString("FANTASIA"));
                pessoaJuridica.setCnaePrincipal(rs.getString("CNAEPRINCIPAL"));
                pessoaJuridica.setDataCadastro(rs.getTimestamp("DATACADASTRO"));

            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return pessoaJuridica;
    }

    public void insert(PessoaJuridica pessoaJuridica) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(INSERT);


            pstmt.setString(1, pessoaJuridica.getRazaoSocial());
            pstmt.setString(2, pessoaJuridica.getCnpj());
            pstmt.setString(3, pessoaJuridica.getCnaePrincipal());
            pstmt.setString(4, pessoaJuridica.getFantasia());
            pstmt.setInt(5, pessoaJuridica.getId());

        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();
        }
    }

    public void update(PessoaJuridica pessoaJuridica) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setString(1, pessoaJuridica.getRazaoSocial());
            pstmt.setString(2, pessoaJuridica.getCnpj());
            pstmt.setString(3, pessoaJuridica.getCnaePrincipal());
            pstmt.setString(4, pessoaJuridica.getFantasia());
            pstmt.setInt(5, pessoaJuridica.getPessoa().getId());

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
            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();
        }
    }
}
