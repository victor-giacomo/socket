package com.company;

import com.company.swing.ClientScreen;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void conectarNoServidor(ClientScreen screen) throws IOException {

        /* O usuário informa o endereço ip do servidor: */
        String serverAddress = JOptionPane.showInputDialog(
                screen.getFrame(),
                "Entre com o endereço ip do servidor:",
                "Bem vindo ao programa de envio de mensagens",
                JOptionPane.QUESTION_MESSAGE, null, null, "127.0.0.1").toString();

        // Endereço ip local = "127.0.0.1";

        try {
            Socket socket = new Socket(serverAddress, 9898);
            // Atributos de entrada e saída de dados
            screen.setOutput( new PrintWriter(socket.getOutputStream(), true) );
        }catch (UnknownHostException e){
            JOptionPane.showMessageDialog(screen.getFrame(),
                    "Não foi possível conectar no servidor com esse endereço ip.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }catch (ConnectException ce){
            JOptionPane.showMessageDialog(screen.getFrame(), "Conexão recusada. Verifique o servidor.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws Exception {
        ClientScreen screen = new ClientScreen();
        screen.show();

        Client client = new Client();
        client.conectarNoServidor(screen);
    }
}