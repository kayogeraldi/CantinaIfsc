/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Compra;

import Service.CaixaService;
import Service.CarteirinhaService;
import Service.ClienteService;
import Service.ContaService;
import Service.FuncionarioService;
import Service.ItemVendaService;
import Service.MovimentacaoEstoqueService;
import Service.MovimentoCaixaService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bo.ItemVenda;
import model.bo.Produto;
import model.bo.Venda;
import view.Compra.PontoDeVendaView;
import Service.ProdutoService;
import Service.VendaService;
import controller.Busca.ControllerClienteView;
import java.text.SimpleDateFormat;
import view.Busca.ClienteView;
import java.util.Date;
import model.bo.Caixa;
import model.bo.Carteirinha;
import model.bo.Cliente;
import model.bo.Compra;
import model.bo.Contas;
import model.bo.Fornecedor;
import model.bo.Funcionario;
import model.bo.MovimentacaoEstoque;
import model.bo.MovimentoCaixa;

/**
 *
 * @author eduar
 */
public class ControllerPontoDeVenda implements ActionListener {

    PontoDeVendaView pontoDeVendaView;
    public static int codigo = 0;
    private Venda venda;
    public ControllerPontoDeVenda(PontoDeVendaView pontoDeVendaView) {
        this.pontoDeVendaView = pontoDeVendaView;
        //this.pontoDeVendaView.getJMenuBar().setVisible(false);
        this.venda = new Venda();
        this.pontoDeVendaView.getPassaInfo().addActionListener(this);
        this.pontoDeVendaView.getCancelaItem().addActionListener(this);
        this.pontoDeVendaView.getFechaCaixa().addActionListener(this);
        this.pontoDeVendaView.getLerCodigoBarra().addActionListener(this);
        this.pontoDeVendaView.getClienteButton().addActionListener(this);
        this.pontoDeVendaView.getFinalizaVenda().addActionListener(this);

        utilities.Utilities.ativaDesativa(true, this.pontoDeVendaView.getjPanelMeio());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel tabela = (DefaultTableModel) this.pontoDeVendaView.getTabelaListaProduto().getModel();
        if (e.getSource() == this.pontoDeVendaView.getFinalizaVenda()) {

            JOptionPane.showMessageDialog(pontoDeVendaView, "Função do F5");

            Date dataAtual = new Date();
            SimpleDateFormat dataFormato = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");
            String dataFormatada = dataFormato.format(dataAtual);
            String horaFormatada = horaFormato.format(dataAtual);

            this.pontoDeVendaView.getDataDeEmissao().setText(dataFormatada);
            this.pontoDeVendaView.getHoraDeEmissao().setText(horaFormatada);

            if (this.pontoDeVendaView.getTabelaListaProduto().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não é possivel finalizar a venda!");

            } else {

                ItemVenda itemVenda = new ItemVenda();
                Funcionario funcionario = new Funcionario();
                Produto produto = new Produto();
                Carteirinha carteirinha = new Carteirinha();
                MovimentoCaixa mov = new MovimentoCaixa();
                Contas conta = new Contas();
                Fornecedor fornecedor = new Fornecedor();
                
                funcionario = FuncionarioService.carregar(Integer.valueOf(pontoDeVendaView.getIdFuncionario().getText()));
                carteirinha = CarteirinhaService.carregar(Integer.valueOf(pontoDeVendaView.getIdCliente().getText()));
                
                System.out.println(carteirinha.getId());
                
                venda.setFuncionario(funcionario);
                venda.setCarteirinha(carteirinha);
                
                itemVenda.setProduto(produto);
                itemVenda.setVenda(venda);
                
                venda.setObservacao(this.pontoDeVendaView.getObservacao().getText().toString().trim());

                mov.setDataHoraMovimento(dataFormatada);
                mov.setFlagTipoMovimento('V');
                mov.setStatus('C');
                mov.setValorMovimento(Float.valueOf(pontoDeVendaView.getTotal().getText()));
                
                
                conta.setDataHoraEmissao(dataFormatada);
                conta.setDataQuitacao(dataFormatada);
                conta.setDataVencimento(dataFormatada);
                conta.setFlagTipoConta('C');
                conta.setMovimentoCaixa(mov);
                
                if (this.pontoDeVendaView.getStatus().getSelectedIndex() == 0) {
                    venda.setStatus(this.pontoDeVendaView.getStatus().getSelectedItem().toString().replace("[V] Venda", "V").trim());
                } else {
                    venda.setStatus(this.pontoDeVendaView.getStatus().getSelectedItem().toString().replace("[C] Cancelado", "C").trim());
                }
                
                venda.setDataVenda(this.pontoDeVendaView.getDataDeEmissao().getText().trim());
                venda.setHoraVenda(this.pontoDeVendaView.getHoraDeEmissao().getText().trim());
                venda.setCarteirinha(carteirinha);
                venda.setValorVenda(Float.valueOf(pontoDeVendaView.getTotal().getText()));
                VendaService.adicionar(venda);
                
                this.pontoDeVendaView.getIDVenda().setText(Integer.toString(venda.getId()));
                
                
                for (int i = 0; i < tabela.getRowCount(); i++) {
                    MovimentacaoEstoque movimentaEestoque = new MovimentacaoEstoque();
           
                    itemVenda.setQtdProduto(Integer.valueOf(tabela.getValueAt(i, 3).toString()));
                    itemVenda.setValorUnitario(Float.parseFloat(tabela.getValueAt(i, 2).toString()));
                    
                    itemVenda.setProduto(ProdutoService.carregar(Integer.parseInt(tabela.getValueAt(i, 0).toString())));
                     
                    itemVenda.setStatus(tabela.getValueAt(i, 4).toString());
                    
                    
                    itemVenda.setVenda(venda);
                   // itemVenda.setId(Integer.parseInt(this.pontoDeVendaView.getIDVenda().getText().trim().toString()));
                    
                   // itemVenda.getVenda().getCarteirinha().setId(Integer.parseInt((this.pontoDeVendaView.getIdCliente().getText())));
                   
                    
                    movimentaEestoque.setDataHoraMovimento(dataFormatada);
                    movimentaEestoque.setFlagTipoMovimento('S');
                    movimentaEestoque.setQtdMovimentada(Float.valueOf(tabela.getValueAt(i, 3).toString()));
                    movimentaEestoque.setStatus('C');
                    movimentaEestoque.setFuncionario(funcionario);
                    movimentaEestoque.setProduto(ProdutoService.carregar(Integer.parseInt(tabela.getValueAt(i, 0).toString())));
                    MovimentacaoEstoqueService.adicionar(movimentaEestoque);
                    
                    ItemVendaService.adicionar(itemVenda);
                    
                }
                
                List<Caixa> listaCaixa = new ArrayList();
                listaCaixa = CaixaService.carregar();
                
                Caixa caixa = listaCaixa.get(listaCaixa.size()-1);

                mov.setCaixa(caixa);
                mov.setValorMovimento(Float.valueOf(pontoDeVendaView.getTotal().getText()));
                
                conta.setValorEmitido(Float.valueOf(pontoDeVendaView.getTotal().getText()));
                conta.setValorQuitado(Float.valueOf(pontoDeVendaView.getTotal().getText()));
                
                MovimentoCaixaService.adicionar(mov);
                conta.setVenda(venda);
                ContaService.adicionar(conta);
                pontoDeVendaView.getCodigoBarra().setText("");
                tabela.setRowCount(0);
            }

        } else if (e.getSource() == this.pontoDeVendaView.getLerCodigoBarra()) {

            if (this.pontoDeVendaView.getCodigoBarra().getText().equalsIgnoreCase("")) {

                JOptionPane.showMessageDialog(null, "Insira um codigo de barra, para adicionar um produto !");

            } else {

                if (this.pontoDeVendaView.getCodigoBarra().getText().length() < 13) {
                    JOptionPane.showMessageDialog(null, "Codigo de Barras invalido!");
                } else if (this.pontoDeVendaView.getCodigoBarra().getText().length() == 13) {
                    this.pontoDeVendaView.getQuantidade().setText("1");

                    Produto produto = new Produto();
                    produto = ProdutoService.carregarProduto(this.pontoDeVendaView.getCodigoBarra().getText().trim());

                    this.pontoDeVendaView.getProdutoNome1().setText(produto.getDescricao());
                    this.pontoDeVendaView.getValorUnitario().setText(produto.getPreco() + "");

                } else {
                    int tamanho = this.pontoDeVendaView.getCodigoBarra().getText().length(); //PEGA O TAMANHO DO CODIGO DE BARRA

                    //  String[] teste = this.pontoDeVendaView.getCodigoBarra().getText().split("x", 3);
                    String quantidade = this.pontoDeVendaView.getCodigoBarra().getText().substring(14,tamanho);

                    this.pontoDeVendaView.getQuantidade().setText(quantidade);
                    
                    Produto produto = new Produto();
                    produto = ProdutoService.carregarProduto(this.pontoDeVendaView.getCodigoBarra().getText().substring(0,13).trim());
                    
                    this.pontoDeVendaView.getCodigoBarra().setText(produto.getCodigoBarra());
                    this.pontoDeVendaView.getProdutoNome1().setText(produto.getDescricao());
                    this.pontoDeVendaView.getValorUnitario().setText(produto.getPreco() + "");

                }

            }

        } else if (e.getSource() == this.pontoDeVendaView.getPassaInfo()) {

            if (this.pontoDeVendaView.getProdutoNome1().getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Nenhum item selecionado!");

            } else {
                int tamanho = this.pontoDeVendaView.getCodigoBarra().getText().length();
                Produto produto = new Produto();
                produto = ProdutoService.carregarProduto(this.pontoDeVendaView.getCodigoBarra().getText().substring(0, 13).trim());

                tabela.addRow(new Object[]{
                    produto.getId(),
                    produto.getDescricao(),
                    this.pontoDeVendaView.getValorUnitario().getText(),
                    this.pontoDeVendaView.getQuantidade().getText(),
                    "A"

                });

                utilities.Utilities.limpaComponentes(true, this.pontoDeVendaView.getjPanelMeio());

                this.pontoDeVendaView.getCodigoBarra().setEnabled(true);
                this.pontoDeVendaView.getProdutoNome1().setEnabled(false);
                this.pontoDeVendaView.getNomeCliente().setEnabled(false);
                this.pontoDeVendaView.getIdCliente().setEnabled(false);
                this.pontoDeVendaView.getHoraDeEmissao().setEnabled(false);
                this.pontoDeVendaView.getDataDeEmissao().setEnabled(false);
                this.pontoDeVendaView.getQuantidade().setEnabled(false);
                this.pontoDeVendaView.getValorUnitario().setEnabled(true);
                this.pontoDeVendaView.getTotal().setEnabled(false);
                this.pontoDeVendaView.getIdFuncionario().setEnabled(false);
                this.pontoDeVendaView.getIDVenda().setEnabled(false);

                if (this.pontoDeVendaView.getTabelaListaProduto().getRowCount() != 0) {

                    float resultado = 0;

                    for (int i = 0; i < tabela.getRowCount(); i++) {
                        // Obter o status da coluna 4 e o valor da coluna 2
                        Object statusObj = tabela.getValueAt(i, 4);
                        Object valorObj = tabela.getValueAt(i, 2);
                        Object qtdObj = tabela.getValueAt(i, 3);
                        // Verificar se os objetos são do tipo esperado antes de fazer o cast
                        if (statusObj instanceof String) {
                            String status = (String) statusObj;
                            
                            Funcionario funcionario = FuncionarioService.carregar(Integer.valueOf(this.pontoDeVendaView.getIdFuncionario().getText()));
                            
                            venda.setFuncionario(funcionario);
                            /*
                            produto = ProdutoService.carregarProduto(tabela.getValueAt(i, 0).toString());
                            
                            ItemVenda itemVenda = new ItemVenda();
                            itemVenda.setQtdProduto((Float.valueOf((String)tabela.getValueAt(i, 3))));
                            itemVenda.setValorUnitario( Float.valueOf((String)tabela.getValueAt(i, 2)));
                            itemVenda.setStatus((String) tabela.getValueAt(i, 4));
                            itemVenda.setProduto(produto);
                            itemVenda.setVenda(venda);
                            
                            ItemVendaService.adicionar(itemVenda);
                            */
                            // Tenta converter o valor diretamente para float
                            try {
                                int quantidade = Integer.parseInt(qtdObj.toString());
                                float valor = Float.parseFloat(valorObj.toString());

                                valor = valor * quantidade;
                                // Verificar o status e calcular o valor
                                if ("A".equalsIgnoreCase(status)) {

                                    resultado += valor;
                                } else if ("C".equalsIgnoreCase(status)) {
                                    resultado -= valor;
                                }
                            } catch (NumberFormatException ea) {
                                JOptionPane.showMessageDialog(null, "Erro ao converter valor para float.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Tipo de status não é String.");
                        }
                    }

                    this.pontoDeVendaView.getTotal().setText(String.valueOf(resultado));
                }
            }

        } else if (e.getSource()
                == this.pontoDeVendaView.getCancelaItem()) {

            if (tabela.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não é possivel cancelar itens, a tabela esta vazia!!!");
            } else {
                tabela.setValueAt('C', this.pontoDeVendaView.getTabelaListaProduto().getSelectedRow(), 4);

                float resultado = 0;

                for (int i = 0; i < tabela.getRowCount(); i++) {
                    // Obter o status da coluna 4 e o valor da coluna 2
                    Object qtdObj = tabela.getValueAt(i, 3);
                    Object statusObj = tabela.getValueAt(i, 4);
                    Object valorObj = tabela.getValueAt(i, 2);
                    // Verificar se os objetos são do tipo esperado antes de fazer o cast
                    if (statusObj instanceof String) {
                        String status = (String) statusObj;
                        // Tenta converter o valor diretamente para float
                        try {
                            int quantidade = Integer.parseInt(qtdObj.toString());
                            float valor = Float.parseFloat(valorObj.toString());
                            // Verificar o status e calcular o valor

                            valor = quantidade * valor;

                            if ("A".equalsIgnoreCase(status)) {
                                resultado += valor;
                            } else if ("C".equalsIgnoreCase(status)) {
                                resultado -= valor;
                            }
                        } catch (NumberFormatException ea) {
                            JOptionPane.showMessageDialog(null, "Erro ao converter valor para float.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Tipo de status não é String.");
                    }
                }

                this.pontoDeVendaView.getTotal().setText(String.valueOf(resultado));

                JOptionPane.showMessageDialog(null, "Produto Cancelado!");
            }

        } else if (e.getSource()
                == this.pontoDeVendaView.getFechaCaixa()) {

           int result = JOptionPane.showConfirmDialog(null, "Vai fechar o caixa?");
            
            if(result == JOptionPane.YES_OPTION){
                List<Caixa> ListaCaixa = new ArrayList();
                ListaCaixa = CaixaService.carregar();
                Caixa caixa = ListaCaixa.get(ListaCaixa.size() - 1);
                caixa.setStatus('F');
                
                List<MovimentoCaixa> listaMov = new ArrayList();
                listaMov = MovimentoCaixaService.carregar();
                
                float aux = 0;
                for(MovimentoCaixa mov : listaMov){
                    if(mov.getCaixa().getId() == ListaCaixa.get(ListaCaixa.size() - 1).getId()){
                        aux += mov.getValorMovimento();
                    }
                }
                
                caixa.setValorFechamento(aux);
                CaixaService.atualizar(caixa);
                this.pontoDeVendaView.dispose();
            }
            
            
            

        } else if (e.getSource()
                == this.pontoDeVendaView.getClienteButton()) {
            ClienteView clienteView = new ClienteView(null, true);
            ControllerClienteView controllerClienteView = new ControllerClienteView(clienteView);
            clienteView.setVisible(true);

            this.pontoDeVendaView.getNomeCliente().setText(controllerClienteView.clienteNome);
            this.pontoDeVendaView.getIdCliente().setText(Integer.toString(controllerClienteView.clienteID));
        }

    }

}
