package musicalChairs;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static musicalChairs.Server.PLAYER_LIST;


//Klass som håller koll på varje enskild tråd 
public class ServerClientThread extends Thread {

    Object CLIENT_CURRENT_ACTION;
    static public ObjectOutputStream STREAM_OUT_TO_CLIENT;
    static public ObjectInputStream STREAM_IN_FROM_CLIENT;
    String SERVER_RESPONSE;
    Socket CLIENTSOCKET;
    long TIMER;
    int CLIENT_ID = -1;
    boolean RUNNING = true;

       //Get the Object input and output stream from the CLIENTSOCKET 
    public ServerClientThread(Socket socket, int clientIndex) throws IOException {
        super("ServerClientThread");
        CLIENTSOCKET = socket;
        this.CLIENT_ID = clientIndex;
        STREAM_IN_FROM_CLIENT = new ObjectInputStream(CLIENTSOCKET.getInputStream());
        STREAM_OUT_TO_CLIENT = new ObjectOutputStream(CLIENTSOCKET.getOutputStream());
    }

 
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
    public static void broadcast() throws IOException {
        for (int i = 0; i < PLAYER_LIST.size(); i++) {
            PLAYER_LIST.get(i).setSERVER_RESPONSE("WINNER");
            while (PLAYER_LIST.get(i) != null) {
                PLAYER_LIST.get(i).sendToClient();
                System.out.println(PLAYER_LIST.get(i).SERVER_RESPONSE);
                //PLAYER_LIST.get(i).sendToClient(PLAYER_LIST.get(i).SERVER_RESPONSE);
            }
        }
    }

    //TODO Fixa så man inte crashar servern om någon dcar 
    public void run() {
        

            while (RUNNING) {
                try {
                    CLIENT_CURRENT_ACTION = STREAM_IN_FROM_CLIENT.readObject();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Client: " + CLIENT_ID + " says :" + CLIENT_CURRENT_ACTION);
                if (CLIENT_CURRENT_ACTION.equals("EXIT")) {
                    RUNNING = false;
                    System.out.println("Stopping communication for client : " + CLIENT_ID);
                } 
                if (CLIENT_CURRENT_ACTION.equals("PLAY")) {
                    RUNNING = true;                
                    System.out.println("The client with ID: " + CLIENT_ID + " Wrote PLAY");
                    setSERVER_RESPONSE("You wanna play a game?");
                    try {
                        sendToClient();
                    } catch (IOException ex) {
                        Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
               
            
        }
    }
}
    


