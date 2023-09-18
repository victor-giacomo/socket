package com.company.swing;

import javax.swing.*;

public class ServerScreen {
    private final JTextArea areaDeMensagens = new JTextArea(8, 60);

    public ServerScreen() {
        JFrame caixaEntrada = new JFrame("Servidor - Caixa de entrada");
        areaDeMensagens.setEditable(false);
        caixaEntrada.getContentPane().add(new JScrollPane(areaDeMensagens), "Center");
        caixaEntrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        caixaEntrada.pack();
        caixaEntrada.setVisible(true);
    }

    public void printMessage(String assunto, String mensagem) {
        areaDeMensagens.append("Assunto: " + assunto + "\n" + "Mensagem: " + mensagem + "\n\n");
    }
}
