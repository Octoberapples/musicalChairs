package musicalChairs;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static musicalChairs.Server.PLAYER_LIST;


//Klass som håller koll på varje enskild tråd 
public class ServerClientThread extends Thread {

    Object WHAT_THE_CLIENT_SENT;
    static public ObjectOutputStream STREAM_OUT_TO_CLIENT;
    static public ObjectInputStream STREAM_IN_FROM_CLIENT;
    String SERVER_RESPONSE;
    Socket CLIENTSOCKET;
    boolean RUNNING = true;
    int CLIENT_ID = -1;
    
    
    //Constructs a ServerCLientThread thread
    public ServerClientThread(Socket CLIENTSOCKET, int clientIndex) {
            this.CLIENTSOCKET = CLIENTSOCKET;
            this.CLIENT_ID = clientIndex;
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
    
    public void whatTheClientSent() throws IOException, ClassNotFoundException {
     WHAT_THE_CLIENT_SENT = STREAM_IN_FROM_CLIENT.readObject();
     System.out.println("Got this: "+WHAT_THE_CLIENT_SENT+ " from Client: "+ CLIENT_ID);
    }

    //TODO Fixa så man inte crashar servern om någon dcar 
    public void run() {
                try {
                    //Create streams for each player
                    STREAM_IN_FROM_CLIENT = new ObjectInputStream(CLIENTSOCKET.getInputStream());
                    STREAM_OUT_TO_CLIENT = new ObjectOutputStream(CLIENTSOCKET.getOutputStream());
                    System.out.println("THREAD SAYS " + Server.PLAYER_LIST.size());
    
                // Accept messages from this client and broadcast them.
                // Ignore other clients that cannot be broadcasted to.
                while(true) {    
                whatTheClientSent();
                System.out.println("Client: " + CLIENT_ID + " says :" + WHAT_THE_CLIENT_SENT);
                if (!WHAT_THE_CLIENT_SENT.equals("")) {
                    ServerGameProtocol.handleClientInput(WHAT_THE_CLIENT_SENT);
                } 
                if (WHAT_THE_CLIENT_SENT.equals("PLAY")) {               
                    System.out.println("The client with ID: " + CLIENT_ID + " Wrote PLAY");
                    setSERVER_RESPONSE("GET READY");
                    try {
                        sendToClient();
                    } catch (IOException ex) {
                        Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }
                
                } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
             }finally {
                // This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                //remove it from the list
               
                try {
                    CLIENTSOCKET.close();
                } catch (IOException e) {
                }
                        
               }
        }
     }    



