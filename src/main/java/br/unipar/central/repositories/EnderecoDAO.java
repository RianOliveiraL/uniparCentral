package br.unipar.central.repositories;

import br.unipar.central.model.Endereco;
import br.unipar.central.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    private static final String INSERT =
            "INSERT INTO ENDERECO " +
                    "(ID, LOGRADOURO, NUMERO, BAIRRO, CEP, COMPLEMENTO, RA, PESSOA_ID, CIDADE_ID) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ALL =
            "SELECT ID, LOGRADOURO, NUMERO, BAIRRO, CEP, COMPLEMENTO, RA, PESSOA_ID, CIDADE_ID " +
                    "FROM ENDERECO";

    private static final String FIND_BY_ID =
            "SELECT ID, LOGRADOURO, NUMERO, BAIRRO, CEP, COMPLEMENTO, RA, PESSOA_ID, CIDADE_ID " +
                    "FROM ENDERECO " +
                    "WHERE ID = ?";

    private static final String DELETE_BY_ID =
            "DELETE FROM ENDERECO WHERE ID = ?";

    private static final String UPDATE =
            "UPDATE ENDERECO SET logradouro = ?, numero = ?, bairro = ?, cep = ?, complemento = ?, " +
                    "ra = ?, pessoa_id = ?, " +
                    "cidade_id = ? " +
                    "WHERE id = ?";

    public List<Endereco> findAll() throws SQLException {

        ArrayList<Endereco> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("ID"));
                endereco.setLogradouro(rs.getString("LOGRADOURO"));
                endereco.setNumero(rs.getString("NUMERO"));
                endereco.setBairro(rs.getString("BAIRRO"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setComplemento(rs.getString("COMPLEMENTO"));
                endereco.setRa(rs.getString("RA"));
                endereco.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
                endereco.setCidade(new CidadeDAO().findById(rs.getInt("CIDADE_ID")));

                retorno.add(endereco);
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

    public Endereco findById(int id) throws SQLException {

        Endereco endereco = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DatabaseUtils().getConnection();
            pstmt = conn.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                endereco = new Endereco();
                endereco.setId(rs.getInt("ID"));
                endereco.setLogradouro(rs.getString("LOGRADOURO"));
                endereco.setNumero(rs.getString("NUMERO"));
                endereco.setBairro(rs.getString("BAIRRO"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setComplemento(rs.getString("COMPLEMENTO"));
                endereco.setRa(rs.getString("RA"));
                endereco.setPessoa(new PessoaDAO().findById(rs.getInt("PESSOA_ID")));
                endereco.setCidade(new CidadeDAO().findById(rs.getInt("CIDADE_ID")));


            }
        } finally {
            if (conn != null)
                conn.close();

            if (pstmt != null)
                pstmt.close();

            if (rs != null)
                rs.close();
        }

        return endereco;
    }
}
