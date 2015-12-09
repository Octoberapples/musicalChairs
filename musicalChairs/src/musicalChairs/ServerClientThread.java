package musicalChairs;

import java.net.*;
import java.io.*;
import static musicalChairs.Server.PLAYER_LIST;

/**
 *
 * @author Nogna och lite tim
 */
public class ServerClientThread extends Thread {

    Object CLIENT_CURRENT_ACTION;
    static private ObjectOutputStream STREAM_OUT_TO_CLIENT;
    static private ObjectInputStream STREAM_IN_FROM_CLIENT;
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
        STREAM_IN_FROM_CLIENT = new ObjectInputStream(CLIENTSOCKET.getInputStream());
        STREAM_OUT_TO_CLIENT = new ObjectOutputStream(CLIENTSOCKET.getOutputStream());
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

    void setSERVER_RESPONSE(String stringForTheServerResponse) {
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

    public void sendToClient() throws IOException {
            System.out.println("Sending this: "+SERVER_RESPONSE+ " to Client: "+ CLIENT_ID);
            STREAM_OUT_TO_CLIENT.writeObject(SERVER_RESPONSE);
            STREAM_OUT_TO_CLIENT.flush();
        
    }
    
        /*
     Skicka ut att någon är vinnaren typ, eller att spelet börjar
     */
    private static void broadcast() throws IOException {
        for (int i = 0; i < PLAYER_LIST.size(); i++) {
            PLAYER_LIST.get(i).setSERVER_RESPONSE("WINNER");
            while (PLAYER_LIST.get(i) != null) {
                PLAYER_LIST.get(i).sendToClient();
                System.out.println(PLAYER_LIST.get(i).SERVER_RESPONSE);
                //PLAYER_LIST.get(i).sendToClient(PLAYER_LIST.get(i).SERVER_RESPONSE);
            }
        }
    }

    /**
     * TODO Fixa så man inte crashar servern om någon dcar Gets connection with
     * clients, Opens data streams, in and out,
     */
    public void run() {
        try {

            while (RUNNING) {
                CLIENT_CURRENT_ACTION = STREAM_IN_FROM_CLIENT.readObject();
                System.out.println("Client: " + CLIENT_ID + " says :" + CLIENT_CURRENT_ACTION);
                if (CLIENT_CURRENT_ACTION.equals("EXIT")) {
                    RUNNING = false;
                    CLIENTSTATE = false;
                    System.out.println("Stopping communication for client : " + CLIENT_ID);
                } else{
                    TIMER = (long) CLIENT_CURRENT_ACTION;
                    System.out.println(TIMER);
                    //HÄR SKA VI "SKICKA" DET HÄR TILL TYP SERVER SOM KOLLAR VEM SOM VUNNIT
                    //
                    //
                    //
                    //
                    setSERVER_RESPONSE("WINNER");
                    sendToClient();
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
