package model.bo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.bo.Compra;
import model.bo.MovimentoCaixa;
import model.bo.Venda;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-13T20:57:11")
@StaticMetamodel(Contas.class)
public class Contas_ { 

    public static volatile SingularAttribute<Contas, String> dataHoraEmissao;
    public static volatile SingularAttribute<Contas, Compra> compra;
    public static volatile SingularAttribute<Contas, String> observacao;
    public static volatile SingularAttribute<Contas, Float> valorDesconto;
    public static volatile SingularAttribute<Contas, Float> valorAcrescimo;
    public static volatile SingularAttribute<Contas, Float> valorQuitado;
    public static volatile SingularAttribute<Contas, Venda> venda;
    public static volatile SingularAttribute<Contas, String> dataVencimento;
    public static volatile SingularAttribute<Contas, MovimentoCaixa> movimentoCaixa;
    public static volatile SingularAttribute<Contas, Integer> id;
    public static volatile SingularAttribute<Contas, Character> flagTipoConta;
    public static volatile SingularAttribute<Contas, String> dataQuitacao;
    public static volatile SingularAttribute<Contas, Float> valorEmitido;
    public static volatile SingularAttribute<Contas, Character> status;

}