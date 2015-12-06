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

    static Socket SOCKET;
    static final String SERVER = "localhost";
    static final int DEFAULT_SOCKET_PORT = 8080; //Kanske vill ha en CommonSTuffClient klass men nog onödigt

    private static void messageToServer(String clientRequest) throws IOException {
        OutputStream outToServer = SOCKET.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(clientRequest);
    }

    private static void messageToServer(String clientRequest, long totalResponseTime) throws IOException {
        OutputStream outToServer = SOCKET.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);
        out.writeUTF(clientRequest + "\n" + totalResponseTime);
    }

    private static String messageFromServer() throws IOException {
        InputStream inFromServer = SOCKET.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        System.out.println("Server says " + in.readUTF());
        if (in.readUTF() != null) {
            return in.readUTF();
        }
        return ("NO MESSAGE FROM THE SERVER");

    }

    private static Socket connectToServer() throws IOException {
        System.out.println("Connecting to " + SERVER + " on port " + DEFAULT_SOCKET_PORT);
        Socket newClientSocket = new Socket(SERVER, DEFAULT_SOCKET_PORT);
        System.out.println("Just connected to " + newClientSocket.getRemoteSocketAddress());
        return newClientSocket;
    }

    public static void main(String[] args) throws IOException {
        SOCKET = connectToServer();
        Socket clientSocket;
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
        //ClientInterface.printChoices("Join Game");
        while (true) {
            try {
                clientSocket = connectToServer();
                ClientInterface.joinOrExit(clientSocket); //tror jag vill ta in outputstreamen i denna men vet inte hur //för att testa funktionen bara
                String clientInput = ClientInterface.getRequest();
                messageToServer(clientInput);
                String ServerResponse = messageFromServer();
                processMessageFromServer(ServerResponse);
                clientSocket.close();
                break;
            } catch (IOException e) {
            }

        }
        System.out.println("Thanks for playing!");
        madeby();
    }

    /**
     *
     *
     *
     */
    private static void processMessageFromServer(String serverResponse) throws IOException {
        switch (serverResponse) {
            case ("WINNER"):
                System.out.println("You are the winner!");
                break;
            case ("LOSER"):
                System.out.println("You are a noob and YOU'RE OUT!");
                break;
            case ("ADVANCED"):
                System.out.println("You advanced to the next round");
                break;
            case ("Force Start"):
                System.out.println("You are now in que to play");
                break;
            case ("Sit Down"):
                System.out.println("Sätt dig ner för fan");
                long startTimer = System.currentTimeMillis();
                String clientInput = ClientInterface.getRequest();
                long stopTimer = System.currentTimeMillis();
                long totalResponseTime = stopTimer - startTimer;
                messageToServer(clientInput, totalResponseTime);
                break;
            case ("Get Ready"):
                System.out.println("Nu smäller de snart");
                messageToServer("READY");
                break;
        }

    }

//TODO Fix safe input och flytta till ClientInterface
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
