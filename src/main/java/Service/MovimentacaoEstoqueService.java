/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.DAOMovimentaEstoque;
import java.util.List;
import model.bo.MovimentacaoEstoque;


public class MovimentacaoEstoqueService {
    public static void adicionar(MovimentacaoEstoque objeto) {
            DAOMovimentaEstoque.getInstance().create(objeto);
        }
        
        public static List<MovimentacaoEstoque> carregar(){
            return DAOMovimentaEstoque.getInstance().retrieve();
        }
        
        public static MovimentacaoEstoque carregar(int parPK) {
            return DAOMovimentaEstoque.getInstance().retrieve(parPK);
        }
        public static void atualizar(MovimentacaoEstoque objeto){
            DAOMovimentaEstoque.getInstance().update(objeto);
        }
        
        public static void remover(MovimentacaoEstoque objeto){
            DAOMovimentaEstoque.getInstance().delete(objeto);
        }
}
