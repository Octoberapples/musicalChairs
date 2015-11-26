package musicalChairs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Albin
 */
public class Client {

    ClientInterface CLIENTINTERFACE = new ClientInterface();
    static final String SERVER = "localhost";
    static final int DEFAULT_SOCKET_PORT = 8080; //Kanske vill ha en CommonSTuffClient klass men nog onödigt

    private static void messageToServer(Socket client, String clientRequest) throws IOException {
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(clientRequest);
    }

    private static void messageFromServer(Socket client) throws IOException {
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        System.out.println("Server says " + in.readUTF());
    }

    private static Socket connectToServer() throws IOException {
        System.out.println("Connecting to " + SERVER + " on port " + DEFAULT_SOCKET_PORT);
        Socket newClientSocket = new Socket(SERVER, DEFAULT_SOCKET_PORT);
        System.out.println("Just connected to " + newClientSocket.getRemoteSocketAddress());
        return newClientSocket;
    }

    public static void main(String[] args) throws IOException {
        Socket client;

        /*
        if (args.length <= 1) {
            System.out.println("Usage: java musicalChairs [server name] [socket port]");
            System.exit(0);
        }
        //server = args[0];
        try {
            DEFAULT_SOCKET_PORT = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Could not parse given port number. Using defaults.");
        }
         */
        while (true) {
            try {
                String clientRequest = Integer.toString(ClientInterface.getRequest());
                client = connectToServer();
                messageToServer(client, clientRequest);
                messageFromServer(client);
                client.close();
                break;
            } catch (IOException e) {
            }

        }
        //System.out.println("Thanks for playing!");
        //madeby();

        /**
         * Loopar tills man inte vill spela mer while (cont) {
         * playGame(clientChoices, clientRequest, server, socket_port); cont =
         * askContinue("Exit game?: "); }
         */
    }

    /**
     * @param clientChoices What the server told the client to do
     * @param clientRequest what the client want to do
     * @param server the server we are going to use
     * @param socket_port which port we are using
     *
     * private static void playGame(String clientChoices, int clientRequest,
     * String server, int socket_port) { boolean cont = true; while (cont) {
     * ClientInterface.printChoices(clientChoices); // Printar clientens val
     *
     * long startTime = System.currentTimeMillis(); //Start timer clientRequest
     * = ClientInterface.getRequest(); //Kollar vad clienten vill göra if
     * (clientRequest == 2) { //kollar om man vill leave game i rundan, kanske
     * onödigt cont = false; } else { long stopTime =
     * System.currentTimeMillis(); //stoppa timer long clientResponseTime =
     * stopTime - startTime; //räknar ut response tiden för clienten
     * clientChoices = ClientSocket.runCommand(clientResponseTime, server,
     * socket_port); // Skickar timer till servern. och uppdaterar ClientChoices
     * } switch (clientChoices) { case "WINNER": System.out.println("You are the
     * winner, congratulations!"); cont = !(askContinue("Do you wanna sign up
     * for a new game?")); break; case "ADVANCED": System.out.println("You are
     * in the next round!"); break; case "LOSER": System.out.println("You're
     * out"); cont = !(askContinue("Do you wanna sign up for a new game?"));
     * break; } }
     *
     * }
     */
//TODO Fix safe input
    private static boolean askContinue(String phrase) {
        System.out.println(phrase + "(y/n)");
        Scanner sc = new Scanner(System.in);
        switch (sc.nextLine().charAt(0)) {
            case 'y':
                sc.close();
                return false;
        }
        sc.close();
        return true;
    }

    private static void madeby() {
        System.out.println("Made by:");
        System.out.println("Albin Sundqvist");
        System.out.println("Tim Kulich");
        System.out.println("Linnea Dahl");
        System.out.println("Markus Norström");
    }

}
