package musicalChairs;

import java.net.*;
import java.io.*;

/**
 *
 * @author Nogna och lite tim
 */
public class ServerRun extends Thread {

    Socket clientSocket;
    int clientID = -1;
    boolean running = true;

    public ServerRun(Socket socket, int clientIndex) throws IOException {
        super("ServerRun");
        clientSocket = socket;
        clientID = clientIndex;
    }

    /*
     return client portnumber
     */
    public int getClientPort() {
        int clientPort = clientSocket.getPort();
        return clientPort;
    }

    /**
     * return client IP
     */
    public InetAddress getClientIP() {
        InetAddress clientIP = clientSocket.getInetAddress();
        return clientIP;
    }


    /**
     * Gets connection with clients, Opens data streams, in and out,
     */
    public void run() {
        System.out.println("Accepted Client : ID - " + clientID + " : Address - "
                + getClientIP()+" : Portnumber - "+ getClientPort());
        try {
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            while (running) {
                String clientCommand = in.readUTF();
                System.out.println("Client Says :" + clientCommand);
                
                if (clientCommand.equalsIgnoreCase("quit")) {
                    running = false;
                    System.out.print("Stopping communication for client : " + clientID);
                } else {
                    out.writeUTF(clientCommand);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}