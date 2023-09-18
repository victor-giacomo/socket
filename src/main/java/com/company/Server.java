package com.company;

import com.company.service.ReceptorMensagens;
import com.company.swing.ServerScreen;

import java.net.ServerSocket;

public class Server {

    // Inicia o servidor e exibe a caixa de entrada
    public static void main(String[] args) throws Exception {
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        System.out.println("O servidor está rodando...");
        try {
            ServerScreen screen = new ServerScreen();
            while (true) {
                // Inicia uma nova Thread para receber a mensagem do cliente
                new ReceptorMensagens(listener.accept(), clientNumber++, screen).start();
            }
        } finally {
            listener.close();
        }
    }

}