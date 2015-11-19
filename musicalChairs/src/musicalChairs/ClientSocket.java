/*
 * Socket client skeleton
 */
package musicalChairs;

import java.net.*;
import java.io.*;
import java.io.OutputStream;

class ClientSocket extends ClientInterface {

    private String[] args;
    String server = args[1];
    int port = Integer.parseInt(args[2]);
    
    /**
     * 
     * @param input_lg
     * @param server
     * @param port
     * @return 
     */

    public static String runCommand(String input_lg, String server, int port) {
        //String test_Request = "";
        //String test_server = "asa_Dahl@halsamorsan.com";
        //int test_port = 1337;

        String ans = "";

        try {
            Socket echoSocket = new Socket(server, port);
            ObjectOutputStream out
                    = new ObjectOutputStream(echoSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            out.writeObject(input_lg);

            ans = in.readLine();

        } catch (Exception e) {
        }

        return ans;

    }

}
