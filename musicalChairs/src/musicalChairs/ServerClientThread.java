package musicalChairs;

import java.net.*;
import java.io.*;

/**
 *
 * @author Nogna och lite tim
 */
public class ServerClientThread extends Thread {

    Object CLIENT_CURRENT_ACTION;
    boolean CLIENTSTATE;
    Socket CLIENTSOCKET;
    long TIMER;
    int CLIENT_ID = -1;
    boolean RUNNING = true;

    public ServerClientThread(Socket socket, int clientIndex) throws IOException {
        super("ServerClientThread");
        CLIENTSOCKET = socket;
        CLIENT_ID = clientIndex;
        CLIENTSTATE = false;
    }

    /*
     return client portnumber
     */
    public int getClientPort() {
        int clientPort = CLIENTSOCKET.getPort();
        return clientPort;
    }

    public void changeState(boolean newState) {
        CLIENTSTATE = newState;
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
            ObjectInputStream in = new ObjectInputStream(CLIENTSOCKET.getInputStream());
            DataOutputStream out = new DataOutputStream(CLIENTSOCKET.getOutputStream());
            while (RUNNING) {
                CLIENT_CURRENT_ACTION = in.readObject();
                System.out.println("Client: "+ CLIENT_ID +" says :" + CLIENT_CURRENT_ACTION);
                if (CLIENT_CURRENT_ACTION.equals("EXIT")) {
                    RUNNING = false;
                    CLIENTSTATE = false;
                    System.out.print("Stopping communication for client : " + CLIENT_ID);
                } else if(CLIENT_CURRENT_ACTION instanceof String){
                    String serverResponse = (String)ServerGameProtocol.handleClientInput(CLIENT_CURRENT_ACTION);
                    out.writeUTF(serverResponse);
                }
               /*
                VILL NOG HA NÅNTING OM LONG CASE MEN TROR INTE DE BEHÖVS
                */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
