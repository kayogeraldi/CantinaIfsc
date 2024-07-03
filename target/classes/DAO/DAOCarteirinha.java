package DAO;

import controller.Busca.ControllerCarteirinhaView;
import static controller.Busca.ControllerCarteirinhaView.colunaFiltro;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import model.bo.Carteirinha;
import model.bo.Cliente;
import model.bo.Produto;
import view.Busca.CarteirinhaView;

public class DAOCarteirinha implements InterfaceDAO<Carteirinha> {

    private static DAOCarteirinha instance;
    protected EntityManager entityManager;

    public static DAOCarteirinha getInstance() {
        if (instance == null) {
            instance = new DAOCarteirinha();
        }
        return instance;
    }

    public DAOCarteirinha() {
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
    public void create(Carteirinha objeto) {
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
    public void delete(Carteirinha objeto) {

    }

    @Override
    public void update(Carteirinha objeto) {
        try{
            Carteirinha bairro = entityManager.find(Carteirinha.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    
    public void retrieve(Carteirinha objeto) {

    }

    @Override
    public List<Carteirinha> retrieve() {
        List<Carteirinha> listaBairros;
        listaBairros = entityManager.createQuery("Select b From Carteirinha b", Carteirinha.class).getResultList();
        return listaBairros;
    }

    @Override
    public Carteirinha retrieve(int parPK) {
        return entityManager.find(Carteirinha.class, parPK);
    }

    @Override
    public List<Carteirinha> retrieve(String parString) {
        List<Carteirinha> listaBairros;
        listaBairros = entityManager.createQuery("Select b From Carteirinha b Where b.descricao like "
                + ":parDescricao", Carteirinha.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        
        
        return listaBairros;
    }
}
