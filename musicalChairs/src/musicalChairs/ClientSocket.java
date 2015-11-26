
package musicalChairs;

import java.net.*;
import java.io.*;

class ClientSocket extends ClientInterface {

    private String[] args;
    String server = args[1];
    int port = Integer.parseInt(args[2]);
    private static Socket socket;

    /**
     * Skickar iväg clientAction och väntar på serverResponse.
     *
     * @param clientAction what the client wants to do.
     * @param server
     * @param socket_port
     * @return String of the choices the client can do.
     */
    public static String runCommand(String clientAction, String server, int socket_port) {

        String ans = null;

        try {
            socket = new Socket(server, socket_port);
            ObjectOutputStream out
                    = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); behöver vi nog inte
            out.writeObject(clientAction);

            ans = in.readLine();

        } catch (Exception e) {
        }

        return ans;

    }

    
    /**
     * Samma som funktionen över förutom att den tar in timer istället för clientAction.
     * Samt skicker iväg timer istället för clientAction.
     */
    public static String runCommand(long timer, String server, int socket_port) {

        String ans = null;

        try {
            Socket echoSocket = new Socket(server, socket_port);
            ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); behöver vi nog inte
            out.writeObject(timer);
            echoSocket.close();
            ans = in.readLine();

        } catch (Exception e) {
        }

        return ans;
    }

}
