/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicalChairs;

import com.sun.webkit.Timer;
import java.util.Scanner;

/**
 *
 * @author Nogna
 */
public class ClientMain {

    public static void main(String[] args) {
        String server, clientRequest = "";
        //ClientInterface client = new ClientInterface();
        int socket_port = 4242; //Kanske vill ha en CommonSTuffClient klass men nog onödigt
        boolean cont = true;
        
        if (args.length <= 1) {
            System.out.println("Usage: java musicalChairs [server name] [socket port]");
            System.exit(0);
        }

        server = args[0];
        try {
            socket_port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Could not parse given port number. Using defaults.");
        }

        String[] clientChoices = ClientSocket.runCommand("Join Game", server, socket_port); //När man startar programmet så joinar man automatiskt.

        /*
        Loopar tills man inte vill spela mer
         */
        while (cont) {
            playGame(clientChoices, clientRequest, server, socket_port); //Hela rundan
            cont = askContinue();
        }
        /*
        Spelet klart
         */
        System.out.println("Thanks for playing!");
        madeby();

    }

    private static void playGame(String[] clientChoices, String clientRequest, String server, int socket_port) {
        boolean cont = true;
        Timer timer;
        while (clientChoices[0] != "WINNER" || clientChoices[0] != "LOSER" || cont!=true) { //Kollar om man har vunnit eller förlorat
            ClientInterface.printChoices(clientChoices);    // Printar clientens val
            timer.start(); //Start timer
            clientRequest = ClientInterface.getRequest(clientChoices);  //Kollar vad clienten vill göra
            if (clientRequest == "Leave Game") { //kollar om man vill leave game i rundan, kanske onödigt
                timer.stop();
                cont =false;
            }else{
            timer.stop(); //stoppa timer
            clientChoices = ClientSocket.runCommand(timer, server, socket_port); // Skickar timer till servern.
            }
        }
    }

    private static boolean askContinue() {
        System.out.println("Exit Game?: (y/n)");
        Scanner sc = new Scanner(System.in);
        switch (sc.nextLine().charAt(0)) {
            case 'y':
                return false;
        }

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
