/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;
import DAO.DAOBairro;
import model.bo.Bairro;

/**
 *
 * @author aluno
 */
public class BairroService {

    public static void adicionar(Bairro objeto) {
        DAOBairro.getInstance().create(objeto);
        //BairroDAO bairroDAO = new BairroDAO();
        //bairroDAO.create(objeto);
    }

    public static List<Bairro> carregar() {
        return DAOBairro.getInstance().retrieve();
        //BairroDAO bairroDAO = new BairroDAO();
        //return bairroDAO.retrieve();
    }

    public static Bairro carregar(int parPK) {
        return DAOBairro.getInstance().retrieve(parPK);
    }

    public static List<Bairro>  carregar(String parString) {
        return DAOBairro.getInstance().retrieve(parString);
    }

    public static void atualizar(Bairro objeto) {
        DAOBairro.getInstance().update(objeto);
    }

    public static void remover(Bairro objeto) {
        DAOBairro.getInstance().delete(objeto);
    }


     
    
}
