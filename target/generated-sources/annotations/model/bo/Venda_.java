package model.bo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.bo.Carteirinha;
import model.bo.Funcionario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-13T20:57:11")
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, String> horaVenda;
    public static volatile SingularAttribute<Venda, String> observacao;
    public static volatile SingularAttribute<Venda, String> dataVenda;
    public static volatile SingularAttribute<Venda, Float> valorVenda;
    public static volatile SingularAttribute<Venda, Integer> id;
    public static volatile SingularAttribute<Venda, Funcionario> funcionario;
    public static volatile SingularAttribute<Venda, Carteirinha> carteirinha;
    public static volatile SingularAttribute<Venda, String> status;

}