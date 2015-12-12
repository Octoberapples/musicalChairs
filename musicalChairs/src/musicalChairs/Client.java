package musicalChairs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    static Socket SOCKET;
    static final String SERVER = "localhost";
    static String SERVER_RESPONSE = "";
    static Object CLIENT_ACTION = "";
    public static ObjectOutputStream STREAM_TO_SERVER;
    public static ObjectInputStream STREAM_FROM_SERVER;
    static final int DEFAULT_SOCKET_PORT = 8080; 
   
   
    
    private void run() throws IOException, ClassNotFoundException {
        
         // Make connection and initialize streams
        System.out.println("\n" + "Trying to create a socket");
        SOCKET = new Socket(SERVER, DEFAULT_SOCKET_PORT);
        System.out.println("Successfully created a socket");
        
        
        System.out.println("\n" + "Trying to create InputStream...");
        STREAM_TO_SERVER = new ObjectOutputStream(SOCKET.getOutputStream());
        STREAM_TO_SERVER.flush();
        System.out.println(
                "[CLIENT] ----> [SERVER]" + "\n" +
                "Successfully created a stream to the server");
        
        System.out.println("\n" + "Trying to create OutputStream...");
        STREAM_FROM_SERVER = new ObjectInputStream(SOCKET.getInputStream());
        System.out.println(
                "[SERVER] ----> [CLIENT]" + "\n" +
                "Successfully created a stream from the server");
        
        
        // Process all messages from server
        while (true) {
            CLIENT_ACTION = ClientInterface.getRequest("Musical Chairs"); 
            STREAM_TO_SERVER.writeObject(CLIENT_ACTION);
            SERVER_RESPONSE = (String) STREAM_FROM_SERVER.readObject();
            System.out.println("This is what the client sends: " + CLIENT_ACTION);
            System.out.println("This is the server response: " + SERVER_RESPONSE);
                if (!SERVER_RESPONSE.isEmpty()){
                    ClientInterface.processMessageFromServer();
                }
                if (SERVER_RESPONSE.isEmpty()){
                    System.out.println("Unfortunetly the response from the server was empty");
                }
                else if (SERVER_RESPONSE.equalsIgnoreCase("EXIT")) {
                 STREAM_TO_SERVER.close();
            } 
        }
    }
    
    
      public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.run();
    }
    
}
