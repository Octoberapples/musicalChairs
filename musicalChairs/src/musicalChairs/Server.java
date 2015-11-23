/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicalChairs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author albin
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listeningSocket = true;
        try {
            serverSocket = new ServerSocket(4242);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4242");
        }
        while (listeningSocket) {
            Socket clientSocket = serverSocket.accept();
            ServerRun game = new ServerRun(clientSocket);
            game.start();
        }
        serverSocket.close();
    }
}
