package musicalChairs;

import java.io.IOException;
import java.net.ServerSocket;


/**
 *
 * @author albin
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listeningSocket = true;
        int port = 8080;
        try {
            Thread client = new ServerRun(port);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
