/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.Persiste.produtoList;
import controller.Busca.ControllerProdutoView;
import static controller.Busca.ControllerProdutoView.colunaFiltro;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import model.bo.Produto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import view.Busca.ProdutoView;

public class DAOProduto implements InterfaceDAO<Produto> {

    private static DAOProduto instance;
    protected EntityManager entityManager;

    public static DAOProduto getInstance() {
        if (instance == null) {
            instance = new DAOProduto();
        }
        return instance;
    }

    public DAOProduto() {
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
    public void create(Produto objeto) {
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
    public void delete(Produto objeto) {

    }

    @Override
    public void update(Produto objeto) {
        try{
            Produto bairro = entityManager.find(Produto.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    
    public void retrieve(Produto objeto) {

    }

    @Override
    public List<Produto> retrieve() {
        List<Produto> listaBairros;
        listaBairros = entityManager.createQuery("Select b From Produto b", Produto.class).getResultList();
        return listaBairros;
    }

    @Override
    public Produto retrieve(int parPK) {
        return entityManager.find(Produto.class, parPK);
    }

    @Override
    public List<Produto> retrieve(String parString) {
        List<Produto> listaBairros;
        listaBairros = entityManager.createQuery("Select b From Produto b Where b.descricao like "
                + ":parDescricao", Produto.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        
        
        return listaBairros;
    }

    public Produto RetornaDeLadinho(String cod) {
        String jpql = "SELECT p FROM Produto p WHERE p.codigoBarra = :codigoBarra";
        Produto produto = entityManager.createQuery(jpql, Produto.class).setParameter("codigoBarra", cod).getResultList().get(0);
        return produto;
    }

//    public Produto RetornaIDproduto(String cod) {
//        Connection conexao = ConnectionFactory.getConnection();
//        ProdutoView produtoView = new ProdutoView(null, true);
//        ControllerProdutoView controllerProdutoView = new ControllerProdutoView(produtoView);
//        // String coluna = produtoView.getComboBoxFiltrar().getSelectedItem().toString().trim();
//
//        String sqlExecutar = "SELECT ID "
//                + "FROM tblproduto "
//                + "WHERE codigoBarra LIKE ?";
//
//        PreparedStatement pstm = null;
//        ResultSet rst = null;
//        Produto produto = new Produto();
//
//        try {
//            pstm = conexao.prepareStatement(sqlExecutar);
//
//            //pstm.setString(1,colunaFiltro); 
//            pstm.setString(1, "%" + cod + "%");  // Usando parString diretamente
//
//            rst = pstm.executeQuery();
//
//            while (rst.next()) {
//
//                produto.setDescricao(rst.getString("ID"));
//
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionFactory.closeConnection(conexao, pstm, rst);
//        }
//
//        return produto;
//    }
}
