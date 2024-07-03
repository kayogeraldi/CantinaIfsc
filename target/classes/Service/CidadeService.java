/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;
import DAO.DAOCidade;
import model.bo.Cidade;

/**
 *
 * @author aluno
 */
public class CidadeService {
        public static void adicionar(Cidade objeto) {
            DAOCidade.getInstance().create(objeto);
    }

    public static List<Cidade> carregar() {
        return DAOCidade.getInstance().retrieve();
    }

    public static Cidade carregar (int parPK) {
        return DAOCidade.getInstance().retrieve(parPK);
    }

    public static List<Cidade> carregar(String aux, String parString) {
        return DAOCidade.getInstance().retrieve(aux, parString);
    }

    public static void atualizar(Cidade objeto) {
        DAOCidade.getInstance().update(objeto);
    }

    public static void remover(Cidade objeto) {
        DAOCidade.getInstance().delete(objeto);
    }
}
