/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static controller.Busca.ControllerClienteView.colunaFiltro;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Cliente;
import model.bo.Endereco;

public class DAOCliente implements InterfaceDAO<Cliente> {

    private static DAOCliente instance;
    protected EntityManager entityManager;

    public static DAOCliente getInstance() {
        if (instance == null) {
            instance = new DAOCliente();
        }
        return instance;
    }

    public DAOCliente() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_Cantina");

        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
    
    @Override
    public void create(Cliente objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Cliente objeto) {

    }

    @Override
    public void update(Cliente objeto) {
        Connection conexao = ConnectionFactory.getConnection();
        String sqlExecutar = "UPDATE tblcliente SET nome = ? ,"
                                                 +"fone1 = ?,"
                                                 +"fone2 = ?,"
                                                 +"email = ?,"
                                                 +"status = ?,"
                                                 +"complementoEndereco = ?,"
                                                 +"cpf = ?,"
                                                 +"rg = ?,"
                                                 +"matricula = ?,"
                                                 +"dataNascimento = ?,"
                                                 +"tblendereco_id = (SELECT id FROM TBLENDERECO WHERE logradouro LIKE ?)"
                                                 +"WHERE id = ?";
        PreparedStatement pstm = null;
        Cliente cliente = new Cliente();

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, objeto.getNome());
            pstm.setString(2,objeto.getFone1());
            pstm.setString(3,objeto.getFone2());
            pstm.setString(4,objeto.getEmail());
            pstm.setString(5,objeto.getStatus());
            pstm.setString(6,objeto.getComplementoEndereco());
            pstm.setString(7, objeto.getCpf());
            pstm.setString(8,objeto.getRg());
            pstm.setString(9,objeto.getMatricula());
            pstm.setString(10,objeto.getDataNascimento());
            pstm.setString(11, objeto.getEndereco().getLogradouro());
            pstm.setInt(12, objeto.getId());
            pstm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm);
        }

    }

    
    public void retrieve(Cliente objeto) {

    }

    @Override
    public List<Cliente> retrieve() {
        List<Cliente> listaBairros;
        listaBairros = entityManager.createQuery("Select b From Cliente b", Cliente.class).getResultList();
        return listaBairros;
    }

    @Override
    public Cliente retrieve(int parPK) {
        return entityManager.find(Cliente.class, parPK);
    }

    @Override
    public List<Cliente> retrieve(String parString) {
        
        List<Cliente> listaBairros;
        listaBairros = entityManager.createQuery("Select b From Cliente b Where b.cpf like "
                + ":parDescricao", Cliente.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        
        return listaBairros;
    }
   

}
