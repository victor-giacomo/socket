package com.company.swing;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;

public class ClientScreen {

    private final JFrame frame = new JFrame("Cliente Mensagem");
    private final JTextField assuntoField = new JTextField(40);
    private final JTextArea mensagemField = new JTextArea(8, 60);

    private PrintWriter output;

    public ClientScreen() {
        // Monta a tela exibida na execução do Client
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(createComponentWithLabel("Assunto:     ", assuntoField), "North");
        panelPrincipal.add(createComponentWithLabel("Mensagem:", mensagemField), "Center");

        JButton botaoEnviar = new JButton("Enviar");
        panelPrincipal.add(botaoEnviar, "South");
        frame.getContentPane().add(panelPrincipal);

        // Configura o Listener do botão enviar
        // Executa a ação do botão Enviar
        botaoEnviar.addActionListener(event -> {
            try{
                // Utiliza o JSON para formatar o conteúdo da mensagem
                JSONObject assuntoJSON = new JSONObject();
                assuntoJSON.put("assunto", assuntoField.getText());

                JSONObject mensagemJSON = new JSONObject();
                mensagemJSON.put("mensagem", mensagemField.getText());

                JSONArray conteudoEnvioJSON = new JSONArray();
                conteudoEnvioJSON.put(assuntoJSON);
                conteudoEnvioJSON.put(mensagemJSON);

                // Envia conteúdo para o servidor
                output.println(conteudoEnvioJSON);

                // Limpa o conteúdo das mensagens enviadas
                assuntoField.setText("");
                mensagemField.setText("");
            } catch (Exception e) {
                System.out.println("Erro: " + e);
            }
        });
    }

    public void show() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    // Metétodo responsável por adicionar um label + componente na tela
    private JPanel createComponentWithLabel(String label, Component comp) {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel(label, JLabel.RIGHT), BorderLayout.WEST);
        p.add(comp, BorderLayout.CENTER);
        return p;
    }

    public JFrame getFrame() {
        return frame;
    }
}
