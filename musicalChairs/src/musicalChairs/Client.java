package musicalChairs;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
    static String SERVER_RESPONSE;
    static Object CLIENT_ACTION;
    //"192.168.0.31";
    static final int DEFAULT_SOCKET_PORT = 8080; //Kanske vill ha en CommonSTuffClient klass men nog onödigt

    private static void closeSocket() throws IOException {
        SOCKET.close();
    }

    private static class ClientCheckServerResponse extends Thread {

        public void start() {
            while (true) {
                try {
                    SERVER_RESPONSE = (String) messageFromServer();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        SOCKET = connectToServer();
        ClientCheckServerResponse response = new ClientCheckServerResponse();
        response.start();

        while (!CLIENT_ACTION.equals("2")) {
            System.out.println(SERVER_RESPONSE);
            CLIENT_ACTION = ClientInterface.getRequest();
            sleep(10);
            processMessageFromServer();
            messageToServer(CLIENT_ACTION);

        }
        //response.stop(); // VILL NOG HA DEN HÄR MEN BÄTTRE GREJ
        System.out.println("Closing the connection...");
        closeSocket();
        System.out.println("Succes!" + "\n"+ "Goodbye!");
    }

    private static Socket connectToServer() throws IOException {
        System.out.println("Connecting to " + SERVER + " on port " + DEFAULT_SOCKET_PORT);
        Socket newClientSocket = new Socket(SERVER, DEFAULT_SOCKET_PORT);
        System.out.println("Just connected to " + newClientSocket.getRemoteSocketAddress());
        return newClientSocket;
    }

    private static void messageToServer(Object clientRequest) throws IOException {
        OutputStream outToServer = SOCKET.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outToServer);
        out.writeObject(clientRequest);
    }

    private static Object messageFromServer() throws IOException, ClassNotFoundException {
        InputStream inFromServer = SOCKET.getInputStream();
        ObjectInputStream in = new ObjectInputStream(inFromServer);
        SERVER_RESPONSE = (String) in.readObject();
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
