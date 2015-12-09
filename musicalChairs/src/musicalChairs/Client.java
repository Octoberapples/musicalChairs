package musicalChairs;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Albin
 */
public class Client {

    static Socket SOCKET;
    static final String SERVER = "localhost"; //"localhost";
    //"192.168.0.31";
    static String SERVER_RESPONSE;
    static Object CLIENT_ACTION = "";
    static private ObjectOutputStream OUT_TO_SERVER;
    static private DataInputStream IN_FROM_SERVER;
    static final int DEFAULT_SOCKET_PORT = 8080; //Kanske vill ha en CommonSTuffClient klass men nog onödigt

    private static void closeConnection() throws IOException {
        if (SOCKET != null) {
            SOCKET.close();
        }
        if (OUT_TO_SERVER != null) {
            OUT_TO_SERVER.close();
        }
        if (IN_FROM_SERVER != null) {
            IN_FROM_SERVER.close();
        }
    }

    private static class UpdateSERVER_RESPONSE extends Thread {

        public void run() {
            while (true) {
                try {
                    SERVER_RESPONSE = (String) messageFromServer();
                    System.out.println(SERVER_RESPONSE);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        try {
            SOCKET = createSocketToServer();
            System.out.println("Socket ---- Success");
            createStreamsToServer();
            System.out.println("Streams ---- Success");
        } catch (IOException e) {

        }
        UpdateSERVER_RESPONSE UpdateResponseFromServer = new UpdateSERVER_RESPONSE();
        //Här uppdateras SERVER_RESPONSE
        UpdateResponseFromServer.start();
        System.out.println(CLIENT_ACTION);
        while (!CLIENT_ACTION.equals("EXIT")) {
            CLIENT_ACTION = ClientInterface.getRequest("test game timer"); 
            System.out.println(SERVER_RESPONSE);
            processMessageFromServer(); //Skriver ut om du vunnit/förlorat/gått vidare/ska sätta dig  osv osv
            sleep(10); //Ser till så vi läser av CLIENT_ACTION
            OUT_TO_SERVER.writeObject(CLIENT_ACTION); //SKICKAR IVÄG TILL SERVERN

        }

        //SÄGER TILL SERVERN "EXIT", att clienten avslutar
        OUT_TO_SERVER.writeObject(CLIENT_ACTION);
        try {
            System.out.println("Closing the connection...");
            closeConnection();
        } catch (IOException e) {
        }
        System.out.println("Success!" + "\n" + "Goodbye!");
        System.exit(0);
    }

    private static Socket createSocketToServer() throws IOException {
        System.out.println("Connecting to " + SERVER + " on port " + DEFAULT_SOCKET_PORT);
        Socket newClientSocket = new Socket(SERVER, DEFAULT_SOCKET_PORT);
        System.out.println("Just connected to " + newClientSocket.getRemoteSocketAddress());
        return newClientSocket;
    }

    private static void createStreamsToServer() throws IOException {
        System.out.println("Trying to create OutputStream...");
        OUT_TO_SERVER = new ObjectOutputStream(SOCKET.getOutputStream());
        OUT_TO_SERVER.flush();
        System.out.println("OutputStream ---- Success");
        System.out.println("Trying to create InputStream...");
        IN_FROM_SERVER = new DataInputStream(SOCKET.getInputStream()); // FASSNAR HÄR
        System.out.println("InputStream ---- Success");

    }

    /*
    private static void messageToServer(Object clientRequest) throws IOException {
        OUT_TO_SERVER.writeObject(clientRequest);
    }
     */
    private static Object messageFromServer() throws IOException, ClassNotFoundException {
        SERVER_RESPONSE = (String) IN_FROM_SERVER.readUTF();
        System.out.println("Server says " + SERVER_RESPONSE);

        if (SERVER_RESPONSE != null) {
            return SERVER_RESPONSE;
        }
        return ("NO MESSAGE FROM THE SERVER");

    }

    /**
     *
     *
     *
     */
    private static void processMessageFromServer() throws IOException {
        while (SERVER_RESPONSE != null) {
            switch (SERVER_RESPONSE) {
                case ("WINNER"):
                    System.out.println("You are the winner!");
                    break;
                case ("LOSER"):
                    System.out.println("You are a noob and YOU'RE OUT!");
                    break;
                case ("ADVANCED"):
                    System.out.println("You advanced to the next round");
                    break;
                case ("FORCE START"):
                    System.out.println("You are now in que to play");
                    break;
                case ("SIT DOWN"):
                    System.out.println("Sätt dig ner för fan");
                    break;
                case ("GET READY"):
                    System.out.println("Nu smäller de snart");
                    break;
                default:
                    System.out.println("Ingen giltig respons från servern");
            }
        }
    }

}
