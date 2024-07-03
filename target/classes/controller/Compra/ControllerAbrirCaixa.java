/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.Compra;

import Service.CaixaService;
import Service.FuncionarioService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.bo.Caixa;
import view.Compra.AbreCaixaView;
import view.Compra.PontoDeVendaView;

public class ControllerAbrirCaixa implements ActionListener {

    AbreCaixaView abreCaixaView;

    public ControllerAbrirCaixa(AbreCaixaView abreCaixaView) {
        this.abreCaixaView = abreCaixaView;
        
        this.abreCaixaView.getBotaoAbrirCaixa().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.abreCaixaView.getBotaoAbrirCaixa()) {
            PontoDeVendaView pontoDeVendaView = new PontoDeVendaView(null, true);
            ControllerPontoDeVenda controllerPontoDeVenda = new ControllerPontoDeVenda(pontoDeVendaView);
            pontoDeVendaView.getIdFuncionario().setText(this.abreCaixaView.getIdFuncionario().getText().toString().trim());
            
            Date dataAtual = new Date();
            SimpleDateFormat dataFormato = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");
            String dataFormatada = dataFormato.format(dataAtual);
            String horaFormatada = horaFormato.format(dataAtual);
            
            Caixa caixa = new Caixa();
            
            caixa.setFuncionario(FuncionarioService.carregar(Integer.valueOf(abreCaixaView.getIdFuncionario().getText())));
            caixa.setDataHoraAbertura(dataAtual.toString());
            caixa.setDataHoraFechamento(dataAtual.toString());
            caixa.setStatus('A');
            caixa.setValorAbertura(0);
            caixa.setValorFechamento(0);
            
            CaixaService.adicionar(caixa);
            
            abreCaixaView.dispose();
            pontoDeVendaView.setVisible(true);
            
        }

    }

}
