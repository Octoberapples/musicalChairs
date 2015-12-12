package musicalChairs;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//Klass som håller koll på varje enskild tråd 
public class ServerClientThread extends Thread {

    Object WHAT_THE_CLIENT_SENT;
    static public ObjectOutputStream STREAM_OUT_TO_CLIENT;
    static public ObjectInputStream STREAM_IN_FROM_CLIENT;
    Object SERVER_RESPONSE;
    Socket CLIENTSOCKET;
    boolean RUNNING = true;
    int CLIENT_ID = -1;

    //Konstruerar en ServerCLientThread tråd
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

    public InetAddress getClientIP() {
        InetAddress clientIP = CLIENTSOCKET.getInetAddress();
        return clientIP;
    }

    //Skriver till klienten via en ström
    public void sendToClient(Object obj) throws IOException {
       try {
        System.out.println("Sending this: " + obj + " to Client: " + CLIENT_ID);
        STREAM_OUT_TO_CLIENT.writeObject(obj);
        STREAM_OUT_TO_CLIENT.flush();
       } catch (EOFException e) {
           System.out.println("\n" + "This client: " + CLIENT_ID + "is disconnected");
       }
    }

    //Läser in från strömmen från klienten för att se vad klienten skicka
    public void whatTheClientSent() throws IOException, ClassNotFoundException {
       try {
           
        WHAT_THE_CLIENT_SENT = STREAM_IN_FROM_CLIENT.readObject();
           System.out.println("Stream in from client is: " + WHAT_THE_CLIENT_SENT);
        System.out.println("\n" + "Got this: " + WHAT_THE_CLIENT_SENT + " from Client: " + CLIENT_ID);
        
       } catch (EOFException e) {
           System.out.println("\n" + "This client is disconnected: " + CLIENT_ID);
       }/*catch (StreamCorruptedException ex){
           System.out.println("Invalid type code!");
       }*/
        
    }

    //TODO Fixa så man inte crashar servern om någon dcar 
    //run skriver ut vad klienten skrev och vilken klient det var som skickade
    //Sedan kollar den så att objektet klienten skicka inte var tom, och om 
    //det inte är tomt så går den in i klassen ServerGameProtocol och hämtar
    //vad den ska göra med objektet från handleClientInput
    public void run() {
        try {
            //Create streams for each player
            STREAM_IN_FROM_CLIENT = new ObjectInputStream(CLIENTSOCKET.getInputStream());
            STREAM_OUT_TO_CLIENT = new ObjectOutputStream(CLIENTSOCKET.getOutputStream());
            STREAM_OUT_TO_CLIENT.flush(); 

            // Accept messages from this client and broadcast them.
            // Ignore other clients that cannot be broadcasted to.
            while (true) {
                whatTheClientSent();
                if ((WHAT_THE_CLIENT_SENT == null) || WHAT_THE_CLIENT_SENT.equals("EXIT")) {
                    System.out.println("The client socket is closing");
                    CLIENTSOCKET.close();
                    return;
                }else if(!WHAT_THE_CLIENT_SENT.equals("") || WHAT_THE_CLIENT_SENT instanceof Long) { //måste fixa if-satsen här
                    Object toSendTheClient = ServerGameProtocol.handleClientInput(WHAT_THE_CLIENT_SENT);
                    System.out.println("After the handleClientInput: " + toSendTheClient);
                    sendToClient(toSendTheClient);
                }
                

            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ServerClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
