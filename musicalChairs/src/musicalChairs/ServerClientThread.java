package musicalChairs;

import java.net.*;
import java.io.*;

/**
 *
 * @author Nogna och lite tim
 */
public class ServerClientThread extends Thread {

    Object CLIENT_CURRENT_ACTION;
    static private ObjectOutputStream OUT_TO_CLIENT;
    static private ObjectInputStream IN_FROM_CLIENT;
    String SERVER_RESPONSE;
    boolean CLIENTSTATE;
    Socket CLIENTSOCKET;
    long TIMER;
    int CLIENT_ID = -1;
    boolean RUNNING = true;

    public ServerClientThread(Socket socket, int clientIndex) throws IOException {
        super("ServerClientThread");
        CLIENTSOCKET = socket;
        this.CLIENT_ID = clientIndex;
        CLIENTSTATE = false;
        IN_FROM_CLIENT = new ObjectInputStream(CLIENTSOCKET.getInputStream());
        OUT_TO_CLIENT = new ObjectOutputStream(CLIENTSOCKET.getOutputStream());
    }

    /*
     return client portnumber
     */
    public int getClientPort() {
        int clientPort = CLIENTSOCKET.getPort();
        return clientPort;
    }

    int getClientID() {
        return CLIENT_ID;
    }
    
    void setSERVER_RESPONSE(String stringForTheServerResponse){
        this.SERVER_RESPONSE = stringForTheServerResponse;
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
    
    public void sendToClient(String string_to_client) throws IOException{
        OUT_TO_CLIENT.writeObject(string_to_client);
    }

    /**
     * TODO Fixa så man inte crashar servern om någon dcar Gets connection with
     * clients, Opens data streams, in and out,
     */
    public void run() {
        System.out.println("Accepted Client : ID - " + CLIENT_ID + " : Address - "
                + getClientIP() + " : Portnumber - " + getClientPort());
        try {

            while (RUNNING) {
                CLIENT_CURRENT_ACTION = IN_FROM_CLIENT.readObject();
                System.out.println("Client: " + CLIENT_ID + " says :" + CLIENT_CURRENT_ACTION);
                if (CLIENT_CURRENT_ACTION.equals("EXIT")) {
                    RUNNING = false;
                    CLIENTSTATE = false;
                    System.out.println("Stopping communication for client : " + CLIENT_ID);
                } else if (CLIENT_CURRENT_ACTION instanceof Long) {
                    TIMER = (long) CLIENT_CURRENT_ACTION;
                }
                while (OUT_TO_CLIENT !=null){
                sendToClient(SERVER_RESPONSE);
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
