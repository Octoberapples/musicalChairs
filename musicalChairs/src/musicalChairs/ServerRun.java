package musicalChairs;

import java.net.*;
import java.io.*;

/**
 *
 * @author Nogna
 */
public class ServerRun extends Thread {
    private static int PORT;
    private ServerSocket SERVERSOCKET;

    public ServerRun(int port) throws IOException {
        super("ServerRun");
        this.SERVERSOCKET = new ServerSocket(port);
        this.PORT = port;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for client on port "
                        + SERVERSOCKET.getLocalPort() + "...");
                Socket server = SERVERSOCKET.accept();
                System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());

                DataInputStream in
                        = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out
                        = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Hello to you too " + "\nGoodbye!");
                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
