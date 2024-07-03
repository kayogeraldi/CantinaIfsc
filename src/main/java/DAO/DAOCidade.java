/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import static controller.Busca.ControllerCidadeView.colunaFiltro;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import model.bo.Cidade;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOCidade implements InterfaceDAO<Cidade> {

    private static DAOCidade instance;
    protected EntityManager entityManager;

    public static DAOCidade getInstance() {
        if (instance == null) {
            instance = new DAOCidade();
        }
        return instance;
    }

    public DAOCidade() {
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
    public void create(Cidade objeto) {
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
    public List<Cidade> retrieve() {
        List<Cidade> listaCidades;
        listaCidades = entityManager.createQuery("Select c From Cidade c", Cidade.class).getResultList();
        return listaCidades;
    }

    public Cidade retrieve(int parPK) {
        return entityManager.find(Cidade.class, parPK);

    }

    @Override
    public List<Cidade> retrieve(String parString) {
        return null;
    }

    public List<Cidade> retrieve(String nomeParametro, String parString) {

        List<Cidade> listaCidades;
        listaCidades = entityManager.createQuery("Select c From Cidade c Where c." + nomeParametro + "  like "
                + ":parDescricao", Cidade.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        return listaCidades;
    }

    @Override
    public void update(Cidade objeto) {
        try {
            Cidade cidade = entityManager.find(Cidade.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(cidade);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Cidade objeto) {
    }

}