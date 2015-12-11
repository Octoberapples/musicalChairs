package musicalChairs;

import java.io.IOException;
import java.io.ObjectInputStream;
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
    static final String SERVER = "localhost"; //"localhost" eller så kan man skriva in en annan IP 
    static String SERVER_RESPONSE = "";
    static Object CLIENT_ACTION = "";
    static public ObjectOutputStream STREAM_OUT_TO_SERVER;
    static public ObjectInputStream STREAM_IN_FROM_SERVER;
    static final int DEFAULT_SOCKET_PORT = 8080; //Kanske vill ha en CommonSTuffClient klass men nog onödigt

    /*
    Closes the correct socket depending on the state
    */
    private static void closeConnection() throws IOException {
        if (SOCKET != null) {
            SOCKET.close();
        }
        if (STREAM_OUT_TO_SERVER != null) {
            STREAM_OUT_TO_SERVER.close();
        }
        if (STREAM_IN_FROM_SERVER != null) {
            STREAM_IN_FROM_SERVER.close();
        }
    }

    private static class UpdateSERVER_RESPONSE extends Thread {

        public void run() {
            while (true) {
                try {
                    messageFromServer();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    public static void main(String[] args) throws Exception, IOException, ClassNotFoundException, InterruptedException {
        try {
            SOCKET = createSocketToServer();
            System.out.println("Socket ---- Success");
            createStreamsToServer();
            System.out.println("Streams ---- Success");
            if (STREAM_IN_FROM_SERVER.readObject() == "START"){
            STREAM_OUT_TO_SERVER.writeObject(ClientInterface.sitDown());
            }
        } catch (IOException e) {

        }
        /*
        UpdateSERVER_RESPONSE UpdateResponseFromServer = new UpdateSERVER_RESPONSE();
        //Här uppdateras SERVER_RESPONSE
        UpdateResponseFromServer.start();
        System.out.println(CLIENT_ACTION);
        while (!CLIENT_ACTION.equals("EXIT")) {
            String lastServerResponse = null;
            if (SERVER_RESPONSE != lastServerResponse) {

                CLIENT_ACTION = ClientInterface.getRequest("test game timer");
                processMessageFromServer(); //Skriver ut om du vunnit/förlorat/gått vidare/ska sätta dig  osv osv
                sleep(10); //Ser till så vi läser av CLIENT_ACTION
                STREAM_OUT_TO_SERVER.writeObject(CLIENT_ACTION); //SKICKAR IVÄG TILL SERVERN
                lastServerResponse = SERVER_RESPONSE;
            }*/
        

        //SÄGER TILL SERVERN "EXIT", att clienten avslutar (aka det som händer när du trycker 2 för exit 
        STREAM_OUT_TO_SERVER.writeObject(CLIENT_ACTION);
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
        STREAM_OUT_TO_SERVER = new ObjectOutputStream(SOCKET.getOutputStream());
        STREAM_OUT_TO_SERVER.flush();
        System.out.println("OutputStream ---- Success");
        System.out.println("Trying to create InputStream...");
        STREAM_IN_FROM_SERVER = new ObjectInputStream(SOCKET.getInputStream());
        System.out.println("InputStream ---- Success");

    }

    /*
    private static void messageToServer(Object clientRequest) throws IOException {
        OUT_TO_SERVER.writeObject(clientRequest);
    }
     */
    private static void messageFromServer() throws IOException, ClassNotFoundException {
        String tmp_SERVER_RESPONSE = null;
        while (tmp_SERVER_RESPONSE == null) {
            tmp_SERVER_RESPONSE = (String) STREAM_IN_FROM_SERVER.readObject();
            System.out.println("Server says " + tmp_SERVER_RESPONSE);
            SERVER_RESPONSE = tmp_SERVER_RESPONSE;

        }

    }

    /**
     *
     *
     *
     */
    private static void processMessageFromServer() throws IOException {
        switch (SERVER_RESPONSE) {
            case ("WINNER"):
                System.out.println("You are the winner!");
                break;
            case ("LOSER"):
                System.out.println("You did not get a chair and therefore lost");
                break;
            case ("ADVANCED"):
                System.out.println("You advanced to the next round");
                break;
            case ("FORCE START"):
                System.out.println("You are now in que to play"); //<-- DOCK HÄR STAVADE DU FEL PÅ QUEUE IGEN ALBIN 
                break;
            case ("SIT DOWN"):
                System.out.println("Sit down!" + "\n" +
                    "i______i" + "\n" +
                    "I______I" + "\n" +
                    "I      I" + "\n" +
                    "I______I" + "\n" +
                   "/      /I" + "\n" +
                  "(______( I" + "\n" +
                  "I I    I I" + "\n" +
                  "I      I");
                break;
            case ("GET READY"):
                System.out.println("The music is playing" + "\n" + 
                
                "┈┏━┓┈┈┈┈┈┏╯┈┈┈┈┏╯┈" + "\n" +
                "┈┣━┫┈┈┈┈┈┣╯┈┈┈┈┣╯┈" + "\n" +
                "╭┫╭┫┈┈┃┈╭┫┈┈┃┈╭┫┈┈" + "\n" +
                "╰╯╰╯┈╭┫┈╰╯┈╭┫┈╰╯┈┈" + "\n" +
                "┈┈┈┈┈╰╯┈┈┈┈╰╯┈┈┈┈┈");
                
                break;
            case (""):
                System.out.println("In player queue"); // HAHA LINNEA JAG STAVADE RÄTT
                break;
            default:
                System.out.println("No valid response from the server");

        }
    }

}
