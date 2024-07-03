/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.DAOAbreCaixa;
import java.util.List;
import model.bo.Caixa;

    
public class CaixaService {
    public static void adicionar(Caixa objeto) {
            DAOAbreCaixa.getInstance().create(objeto);
        }
        
        public static List<Caixa> carregar(){
            return DAOAbreCaixa.getInstance().retrieve();
        }
        
        public static Caixa carregar(int parPK) {
            return DAOAbreCaixa.getInstance().retrieve(parPK);
        }
        public static void atualizar(Caixa objeto){
            DAOAbreCaixa.getInstance().update(objeto);
        }
        
        public static void remover(Caixa objeto){
            DAOAbreCaixa.getInstance().delete(objeto);
        }
}


