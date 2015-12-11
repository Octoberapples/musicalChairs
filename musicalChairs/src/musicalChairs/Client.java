package musicalChairs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


 //Client klassen, håller koll på klienten och skapar strömmen samt socket till servern.
 
public class Client {

    static Socket SOCKET;
    static final String SERVER = "localhost"; //"localhost" eller så kan man skriva in en annan IP 
    static String SERVER_RESPONSE = "";
    static Object CLIENT_ACTION = "";
    static public ObjectOutputStream STREAM_OUT_TO_SERVER;
    static public ObjectInputStream STREAM_IN_FROM_SERVER;
    static final int DEFAULT_SOCKET_PORT = 8080; 

   
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

        
        @Override
        public void run() {
            while (true) {
                try {
                    messageFromServer();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception, IOException, ClassNotFoundException, InterruptedException {
        
        //skapar socket och streamen till servern
        try {
            SOCKET = createSocketToServer();
            System.out.println("Socket ---- Success");
            createStreamsToServer();
            System.out.println("Streams ---- Success");
        } catch (IOException e) {

        }
        
         //Här uppdateras SERVER_RESPONSE genom att använda sig av updateSERVER_RESPONSE klassen 
        
        UpdateSERVER_RESPONSE UpdateResponseFromServer = new UpdateSERVER_RESPONSE();
        UpdateResponseFromServer.start();
        System.out.println(CLIENT_ACTION);
            String lastServerResponse = null;
            if (SERVER_RESPONSE != lastServerResponse) {
                CLIENT_ACTION = ClientInterface.getRequest("Musical Chairs");     
                sleep(100); //Ser till så vi läser av CLIENT_ACTION
                //processMessageFromServer(); //Skriver ut om du vunnit/förlorat/gått vidare/ska sätta dig  osv osv
                STREAM_OUT_TO_SERVER.writeObject(CLIENT_ACTION); //SKICKAR IVÄG TILL SERVERN
               System.out.println("This is what the client sends: " + CLIENT_ACTION);
               System.out.println("This is the server response: " + SERVER_RESPONSE);
                processMessageFromServer();
                lastServerResponse = SERVER_RESPONSE;
            
       }  
      }

    //Creates the socket to the server
    private static Socket createSocketToServer() throws IOException {
        System.out.println("Connecting to " + SERVER + " on port " + DEFAULT_SOCKET_PORT);
        Socket newClientSocket = new Socket(SERVER, DEFAULT_SOCKET_PORT);
        System.out.println("Just connected to " + newClientSocket.getRemoteSocketAddress());
        return newClientSocket;
    }
    
    //Get the Object input and output stream from the SOCKET
    private static void createStreamsToServer() throws IOException {
        System.out.println("Trying to create OutputStream...");
        STREAM_OUT_TO_SERVER = new ObjectOutputStream(SOCKET.getOutputStream());
        STREAM_OUT_TO_SERVER.flush();
        System.out.println("OutputStream ---- Success");
        System.out.println("Trying to create InputStream...");
        STREAM_IN_FROM_SERVER = new ObjectInputStream(SOCKET.getInputStream());
        System.out.println("InputStream ---- Success");

    }
     
    //Reads the stream (string) from the server and updates the SERVER_RESPONSE
    private static void messageFromServer() throws IOException, ClassNotFoundException {
        String tmp_SERVER_RESPONSE = null;
        while (tmp_SERVER_RESPONSE == null) {
            tmp_SERVER_RESPONSE = (String) STREAM_IN_FROM_SERVER.readObject();
            System.out.println("Server says: " + tmp_SERVER_RESPONSE);
            SERVER_RESPONSE = tmp_SERVER_RESPONSE;

        }

    }
    
    //Processes the messsage from the server, depending on the SERVER_RESPONSE the 
    //different cases states what the client should print. 
    private static void processMessageFromServer() throws IOException {
        switch (SERVER_RESPONSE) {
            case ("WINNER"):
                System.out.println("You are the winner!");
                break;
            case ("LOSER"):
                System.out.println("You did not get a chair and therefore lost");
                break;
            case ("ADVANCED"):
                System.out.println("You advanced to the next round!");
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
            case ("You wanna play a game?"):
                System.out.println("The music is playing..." + "\n" + 
                
                "┈┏━┓┈┈┈┈┈┏╯┈┈┈┈┏╯┈" + "\n" +
                "┈┣━┫┈┈┈┈┈┣╯┈┈┈┈┣╯┈" + "\n" +
                "╭┫╭┫┈┈┃┈╭┫┈┈┃┈╭┫┈┈" + "\n" +
                "╰╯╰╯┈╭┫┈╰╯┈╭┫┈╰╯┈┈" + "\n" +
                "┈┈┈┈┈╰╯┈┈┈┈╰╯┈┈┈┈┈");
                
                break;
            default:
                System.out.println("No valid response from the server");

        }
    }
    
    /*
      //SÄGER TILL SERVERN "EXIT", att clienten avslutar (aka det som händer när du trycker 2 för exit) 
        STREAM_OUT_TO_SERVER.writeObject(CLIENT_ACTION);
        try {
            System.out.println("Closing the connection...");
            closeConnection();
        } catch (IOException e) {
        }
        System.out.println("The connection was successfully closed." + "\n" + "Goodbye!");
        System.exit(0);
        }
*/
}
