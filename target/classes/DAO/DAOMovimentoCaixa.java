/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.MovimentoCaixa;

/**
 *
 * @author luizf
 */
public class DAOMovimentoCaixa {
    private static DAOMovimentoCaixa instance;
    protected EntityManager entityManager;
    
     public static DAOMovimentoCaixa getInstance(){
        if(instance==null){
            instance = new DAOMovimentoCaixa();
        }
        return instance;
    }
    
    public DAOMovimentoCaixa(){
        entityManager = getEntityManager();
    }
    
    private EntityManager getEntityManager(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_Cantina");
        
        if(entityManager == null){
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
    
    
    public void create(MovimentoCaixa objeto) {
        try{
                entityManager.getTransaction().begin();
                entityManager.persist(objeto);
                entityManager.getTransaction().commit();
            }catch(Exception ex){
                ex.printStackTrace();
                entityManager.getTransaction().rollback();
           }    
    }

    
    public List<MovimentoCaixa> retrieve() {
        List<MovimentoCaixa> listaItemVendas;
        listaItemVendas = entityManager.createQuery("Select i From MovimentoCaixa i",MovimentoCaixa.class).getResultList();
        return listaItemVendas;
    }

 
    public MovimentoCaixa retrieve(int parPK) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<MovimentoCaixa> retrieve(String parString) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void update(MovimentoCaixa objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public void delete(MovimentoCaixa objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
