/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.DAOConta;
import java.util.List;
import model.bo.Contas;

/**
 *
 * @author luizf
 */
public class ContaService {
    public static void adicionar(Contas objeto) {
            DAOConta.getInstance().create(objeto);
        }
        
        public static List<Contas> carregar(){
            return DAOConta.getInstance().retrieve();
        }
        
        public static Contas carregar(int parPK) {
            return DAOConta.getInstance().retrieve(parPK);
        }
        public static void atualizar(Contas objeto){
            DAOConta.getInstance().update(objeto);
        }
        
        public static void remover(Contas objeto){
            DAOConta.getInstance().delete(objeto);
        }
}
