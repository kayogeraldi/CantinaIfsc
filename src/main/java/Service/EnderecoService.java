package Service;

import java.util.List;
import DAO.DAOEndereco;
import model.bo.Bairro;
import model.bo.Endereco;

public class EnderecoService {

     public static void adicionar(Endereco objeto) {
        DAOEndereco.getInstance().create(objeto);
    }

    public static List<Endereco> carregar() {
        return DAOEndereco.getInstance().retrieve();
    }

    public static Endereco carregar(int parPK) {
        return DAOEndereco.getInstance().retrieve(parPK);
    }

    public static List<Endereco> carregar(String parString) {
        return DAOEndereco.getInstance().retrieve(parString);
    }
    
    public static List<Endereco> carregar(String par, String parString) {
        return DAOEndereco.getInstance().retrieve(par, parString);
    }

    public static void atualizar(Endereco objeto) {
        DAOEndereco.getInstance().update(objeto);
    }

    public static void remover(Endereco objeto) {
        DAOEndereco.getInstance().delete(objeto);
    }
}

