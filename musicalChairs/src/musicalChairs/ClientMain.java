/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicalChairs;

/**
 *
 * @author Nogna
 */
public class ClientMain {

    public static void main(String[] args) {
        String server = "";
        int socket_port = 4242; //Kanske vill ha en CommonSTuffClient klass

        if (args.length <= 1) {
            System.out.println("Usage: java Tester [server name] [socket port]");
            System.exit(0);
        }
        
        server = args[0];
        try {
            socket_port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Could not parse given port number. Using defaults.");
        }
        String socket_lg = ClientSocket.runCommand("Join Game", server, socket_port);
        while (socket_lg != "WINNER" || socket_lg !="LOSER"){
            
            
            
            
        }
        
        
        
        
    }
}
