
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
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8080");
        }
        while (listeningSocket) { 
            Socket clientSocket = serverSocket.accept();
            ServerRun client = new ServerRun(clientSocket);
            client.start();

        }
        serverSocket.close();
    }
}
