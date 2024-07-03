/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
import model.bo.Cidade;

public class DAOEndereco implements InterfaceDAO<Endereco> {

    private static DAOEndereco instance;
    protected EntityManager entityManager;

    public static DAOEndereco getInstance() {
        if (instance == null) {
            instance = new DAOEndereco();
        }
        return instance;
    }

    public DAOEndereco() {
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
    public void create(Endereco objeto) {
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
    public List<Endereco> retrieve() {
        List<Endereco> listaEnderecos;
        listaEnderecos = entityManager.createQuery("Select e From Endereco e", Endereco.class).getResultList();
        return listaEnderecos;
    }

    public Endereco retrieve(int parPK) {
        Endereco endereco = entityManager.find(Endereco.class, parPK);
        return endereco;
    }

    @Override
    public List<Endereco> retrieve(String parString) {
        return null;
    }

    public List<Endereco> retrieve(String nomeParametro, String parString) {
        List<Endereco> listaeEnderecos;
        listaeEnderecos = entityManager.createQuery("Select e From Endereco e Where e." + nomeParametro + "  like "
                + ":parDescricao", Endereco.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        System.out.println(listaeEnderecos.toString());
        return listaeEnderecos;
    }

    @Override
    public void update(Endereco objeto) {
        try {
            Endereco endereco = entityManager.find(Endereco.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Endereco objeto) {
    }

}

