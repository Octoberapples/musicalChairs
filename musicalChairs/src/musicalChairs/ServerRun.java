package musicalChairs;

import java.net.*;
import java.io.*;

/**
 *
 * @author Nogna och lite tim
 */
public class ServerRun extends Thread {

    private static int PORT; //Kanske onödig
    private final ServerSocket SERVERSOCKET;
    Socket[] CLIENT;
    private static int TOTAL_AMOUNT_OF_PLAYERS;

    public ServerRun(int port) throws IOException {
        super("ServerRun");
        this.SERVERSOCKET = new ServerSocket(port);
        this.PORT = port;
        CLIENT = new Socket[5];
        TOTAL_AMOUNT_OF_PLAYERS = 0;
    }

    /**
     * gets player IP and Port
     */
    public void getPlayerInfo(int i) {
        InetAddress clientIP = CLIENT[i].getInetAddress();
        int clientPort = CLIENT[i].getPort();
        System.out.println("TEST BA IP " + clientIP + "PORTEN OCKSÅ " + clientPort);
    }

    /**
     * Sets up connection with client
     *
     * @throws IOException
     */
    public void setUpConnection() throws IOException {
        InetAddress IP = InetAddress.getLocalHost();

        System.out.println("Waiting for client on port " + SERVERSOCKET.getLocalPort() + " and IP "
                + IP.getHostAddress());
        CLIENT[TOTAL_AMOUNT_OF_PLAYERS] = SERVERSOCKET.accept();
        System.out.println("Just connected to client with IP " + CLIENT[TOTAL_AMOUNT_OF_PLAYERS].getInetAddress().getHostAddress() + " on port " + CLIENT[TOTAL_AMOUNT_OF_PLAYERS].getPort());
        TOTAL_AMOUNT_OF_PLAYERS++;
    }

    /**
     * Gets connection with clients, Opens data streams, in and out,
     */
    public void run() {
        while (true) {
            try {
                setUpConnection();
                DataInputStream in = new DataInputStream(CLIENT[(TOTAL_AMOUNT_OF_PLAYERS - 1)].getInputStream());//skapar connection in
                DataOutputStream out = new DataOutputStream(CLIENT[(TOTAL_AMOUNT_OF_PLAYERS - 1)].getOutputStream());//skapar connection ut
                //ServerGameProtocol.playRound(out);
                String serverResponse = in.readUTF();
                System.out.println(serverResponse + " <- skickades from " + CLIENT[(TOTAL_AMOUNT_OF_PLAYERS - 1)].getRemoteSocketAddress());
                out.writeUTF("Det här skickade du till mig: " + serverResponse
                        + "\n" + " Här är din socket som jag använder: " + CLIENT[0].getRemoteSocketAddress()
                        + "\n" + "Så här många spelare: " + TOTAL_AMOUNT_OF_PLAYERS);
                System.out.println(TOTAL_AMOUNT_OF_PLAYERS);
                //CLIENT[0].close();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

}
