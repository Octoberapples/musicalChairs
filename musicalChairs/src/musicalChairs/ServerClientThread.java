package musicalChairs;

import java.net.*;
import java.io.*;

/**
 *
 * @author Nogna och lite tim
 */
public class ServerClientThread extends Thread {
    String CLIENTSTATE;
    Socket CLIENTSOCKET;
    int CLIENT_ID = -1;
    boolean RUNNING = true;

    public ServerClientThread(Socket socket, int clientIndex) throws IOException {
        super("ServerClientThread");
        CLIENTSOCKET = socket;
        CLIENT_ID = clientIndex;
        CLIENTSTATE = "IN_PLAYER_QUEUE";
    }

    /*
     return client portnumber
     */
    public int getClientPort() {
        int clientPort = CLIENTSOCKET.getPort();
        return clientPort;
    }

    /**
     * return client IP
     */
    public InetAddress getClientIP() {
        InetAddress clientIP = CLIENTSOCKET.getInetAddress();
        return clientIP;
    }

    /**
     * Gets connection with clients, Opens data streams, in and out,
     */
    public void run() {
        System.out.println("Accepted Client : ID - " + CLIENT_ID + " : Address - "
                + getClientIP() + " : Portnumber - " + getClientPort());
        try {
            DataInputStream in = new DataInputStream(CLIENTSOCKET.getInputStream());
            DataOutputStream out = new DataOutputStream(CLIENTSOCKET.getOutputStream());
            while (RUNNING) {
                String clientCommand = in.readUTF();
                System.out.println("Client Says :" + clientCommand);
                if (clientCommand.equalsIgnoreCase("EXIT")) {
                    RUNNING = false;
                    System.out.print("Stopping communication for client : " + CLIENT_ID);
                } else {
                    String serverResponse = ServerGameProtocol.handleClientInput(clientCommand, CLIENTSTATE);
                    System.out.println(serverResponse);
                    out.writeUTF(serverResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
