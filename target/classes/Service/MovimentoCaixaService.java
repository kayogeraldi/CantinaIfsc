/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.DAOMovimentoCaixa;
import java.util.List;
import model.bo.MovimentoCaixa;


public class MovimentoCaixaService {
    public static void adicionar(MovimentoCaixa objeto) {
            DAOMovimentoCaixa.getInstance().create(objeto);
        }
        
        public static List<MovimentoCaixa> carregar(){
            return DAOMovimentoCaixa.getInstance().retrieve();
        }
        
        public static MovimentoCaixa carregar(int parPK) {
            return DAOMovimentoCaixa.getInstance().retrieve(parPK);
        }
        
        public static List<MovimentoCaixa> carregar(String parString) {
            return DAOMovimentoCaixa.getInstance().retrieve(parString);
        }
        
        public static void atualizar(MovimentoCaixa objeto){
            DAOMovimentoCaixa.getInstance().update(objeto);
        }
        
        public static void remover(MovimentoCaixa objeto){
            DAOMovimentoCaixa.getInstance().delete(objeto);
        }
}
