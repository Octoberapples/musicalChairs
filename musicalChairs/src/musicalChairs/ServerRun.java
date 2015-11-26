
package musicalChairs;

import java.net.*;
import java.io.*;

/**
 *
 * @author Nogna
 */
public class ServerRun extends Thread {

    private Socket socket = null;

    public ServerRun(Socket socket) {
        super("ServerRun");
        this.socket = socket;
    }

    public void run() {

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            String inputLine, outputLine;
            ServerGameProtocol gameProtocol = new ServerGameProtocol();
            inputLine = in.readLine();
            //while ((inputLine = in.readLine()) != null) {
                outputLine = gameProtocol.processInput(inputLine);
                out.println("hej");

            //}
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
