/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            while ((inputLine = in.readLine()) != null) {
                outputLine = gameProtocol.processInput(inputLine);
                out.println(outputLine);

            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
