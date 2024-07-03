/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;

import static DAO.Persiste.enderecoList;
import static controller.Busca.ControllerEnderecoView.colunaFiltro;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import model.bo.Endereco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Bairro;
import model.bo.Caixa;
import model.bo.Cidade;
import model.bo.Funcionario;
import view.Compra.AbreCaixaView;


/**
 *
 * @author eduar
 */
public class DAOAbreCaixa implements InterfaceDAO<Caixa> {

    private static DAOAbreCaixa instance;
    protected EntityManager entityManager;

    public static DAOAbreCaixa getInstance() {
        if (instance == null) {
            instance = new DAOAbreCaixa();
        }
        return instance;
    }

    public DAOAbreCaixa() {
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
    public void create(Caixa objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

  
    public void retrieve(Caixa objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Caixa objeto) {
        try{
            Caixa bairro = entityManager.find(Caixa.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Caixa objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Caixa> retrieve() {
        List<Caixa> listaBairros;
        listaBairros = entityManager.createQuery("Select b From Caixa b", Caixa .class).getResultList();
        return listaBairros;
    }

    @Override
    public Caixa retrieve(int parPK) {
        return entityManager.find(Caixa.class, parPK);
    }

    @Override
    public List<Caixa> retrieve(String parString) {
        List<Caixa> listaBairros;
        listaBairros = entityManager.createQuery("Select b From Caixa b Where b.codigocaixa like "
                + ":parDescricao", Caixa.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        
        
        return listaBairros;
    }
    
}
