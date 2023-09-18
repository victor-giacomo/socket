package com.company.service;

import com.company.swing.ServerScreen;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// Thread responsável por receber as mensagens do(s) cliente(s)
public class ReceptorMensagens extends Thread {

    private final ServerScreen screen;
    private final Socket socket;
    private final int clientNumber;

    public ReceptorMensagens(Socket socket, int clientNumber, ServerScreen screen) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        this.screen = screen;
        log("Nova conexão com cliente# " + clientNumber + " em " + socket);
    }

    // Executa a Thread
    public void run() {
        try {

            // Recebe o valor enviado pelo cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true) {
                // Transforma o conteudo recebido em String
                String input = in.readLine();

                // Utiliza o JSON para formatar o conteúdo da mensagem recebida
                JSONArray conteudoMensagem = new JSONArray(input);
                String assunto = conteudoMensagem.getJSONObject(0).get("assunto").toString();
                String mensagem = conteudoMensagem.getJSONObject(1).get("mensagem").toString();

                // Exibe a mensagem na interface do servidor
                screen.printMessage(assunto, mensagem);
            }

        } catch (IOException e) {
            log("Erro ao tratar dados do cliente# " + clientNumber + ": " + e);
        } catch (Exception e) {
            log("Error não esperado: " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log("Não foi possível encerrar o socket");
            }
            log("Conexão com o cliente# " + clientNumber + " encerrada.");
        }
    }

    // Exibe o log no console
    private void log(String message) {
        System.out.println(message);
    }
}
