package musicalChairs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
TODO-list
1. Se till att servern inte crashar när en client disconnectar
2. Gör så man kan skriva i alla klienterna utan att de hakar upp sig
3. Fixa så att globala tillstånd uppdateras i realtid 
Bra sida (kanske bra till rapporten): http://makemobiapps.blogspot.se/p/multiple-client-server-chat-programming.html
Nämner bla att stream socket är TCP och datagram socket är UDP
*/


//Client är klassen som kör klienten och processerar meddelandena från servern
//mha ClientinterFace där funktionen processMessageFromServer finns
public class Client {

    static Socket SOCKET;
    static final String SERVER = "localhost";
    static Object SERVER_RESPONSE;
    static Object CLIENT_ACTION = "";
    public static ObjectOutputStream STREAM_TO_SERVER;
    public static ObjectInputStream STREAM_FROM_SERVER;
    static final int DEFAULT_SOCKET_PORT = 8080; 
   
   
    
    // run är funktionen som connectar till servern  och sedan går den in i en 
    // loop där den processeserar meddelanden från servern
    private void run() throws IOException, ClassNotFoundException {
        
         // Skapar en socket
        System.out.println("\n" + "Trying to create a socket");
        SOCKET = new Socket(SERVER, DEFAULT_SOCKET_PORT);
        System.out.println("Successfully created a socket");
        
        // Initialiserar en ström mellan klienten och servern
        System.out.println("\n" + "Trying to create InputStream...");
        STREAM_TO_SERVER = new ObjectOutputStream(SOCKET.getOutputStream());
        STREAM_TO_SERVER.flush();
        System.out.println(
                "[CLIENT] ----> [SERVER]" + "\n" +
                "Successfully created a stream to the server");
        
        // Intitialiserar en ström mellan servern och klienten
        System.out.println("\n" + "Trying to create OutputStream...");
        STREAM_FROM_SERVER = new ObjectInputStream(SOCKET.getInputStream());
        System.out.println(
                "[SERVER] ----> [CLIENT]" + "\n" +
                "Successfully created a stream from the server");
        
        
        // Processerar meddelandena från servern
        while (true) {
            CLIENT_ACTION = ClientInterface.getRequest("Musical Chairs"); 
            STREAM_TO_SERVER.writeObject(CLIENT_ACTION);
            SERVER_RESPONSE =  STREAM_FROM_SERVER.readObject();
            System.out.println("This is what the client sent: " + CLIENT_ACTION);
            System.out.println("This is the server response: " + SERVER_RESPONSE);
                if (!"".equals(SERVER_RESPONSE)){
                    ClientInterface.processMessageFromServer(SERVER_RESPONSE);
                }
                if ("".equals(SERVER_RESPONSE)){
                    System.out.println("Unfortunetly the response from the server was empty");
                }
                if ("EXIT".equals(SERVER_RESPONSE)) {
                 STREAM_TO_SERVER.close();
            } 
        }
    }
    
        // main funktionen kör klienten
      public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.run();
    }
    
}
