package musicalChairs;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author albin
 */
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        int id = 0;
        while (true) {
            System.out.println("Waiting for client on port: " + serverSocket.getLocalPort()
                    + " and IP: " + InetAddress.getLocalHost());
            Socket clientSocket = serverSocket.accept();
            ServerClientThread cliThread = new ServerClientThread(clientSocket, id++);
            cliThread.start();
        }
    }
}