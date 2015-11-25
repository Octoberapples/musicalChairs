/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicalChairs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Nognaaaa
 */
public class Client {

    public static void main(String[] args) throws IOException {

        String server = "localhost";
        int clientRequest = 0;
        int socket_port = 8080; //Kanske vill ha en CommonSTuffClient klass men nog onödigt
        boolean cont = true;
        /*
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
         */
        while (cont) {
            Socket socket = new Socket(server, socket_port);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("server says:" + br.readLine());
            
            BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
            String userInput = userInputBR.readLine();
            
            out.println(userInput);

            System.out.println("server says:" + br.readLine());

            if ("exit".equalsIgnoreCase(userInput)) {
                socket.close();
                break;
            }
        }
    

 

    /*
         Loopar tills man inte vill spela mer
     */
 /*
        while (cont) {

            playGame(clientChoices, clientRequest, server, socket_port); //Hela rundan
            cont = askContinue("Exit game?: ");
        }
     */
 /*
         Spelet klart
     */
    System.out.println (

    "Thanks for playing!");
    madeby();

}

/**
 *
 *
 * @param clientChoices What the server told the client to do
 * @param clientRequest what the client want to do
 * @param server the server we are going to use
 * @param socket_port which port we are using
 */
/*
    private static void playGame(String clientChoices, int clientRequest, String server, int socket_port) {
        boolean cont = true;
        while (cont) {
            ClientInterface.printChoices(clientChoices);    // Printar clientens val

            long startTime = System.currentTimeMillis(); //Start timer
            clientRequest = ClientInterface.getRequest();  //Kollar vad clienten vill göra
            if (clientRequest == 2) { //kollar om man vill leave game i rundan, kanske onödigt
                cont = false;
            } else {
                long stopTime = System.currentTimeMillis();     //stoppa timer
                long clientResponseTime = stopTime - startTime; //räknar ut response tiden för clienten
                clientChoices = ClientSocket.runCommand(clientResponseTime, server, socket_port); // Skickar timer till servern. och uppdaterar ClientChoices
            }
            switch (clientChoices) {
                case "WINNER":
                    System.out.println("You are the winner, congratulations!");
                    cont = !(askContinue("Do you wanna sign up for a new game?"));
                    break;
                case "ADVANCED":
                    System.out.println("You are in the next round!");
                    break;
                case "LOSER":
                    System.out.println("You're out");
                    cont = !(askContinue("Do you wanna sign up for a new game?"));
                    break;
            }
        }

    }
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
