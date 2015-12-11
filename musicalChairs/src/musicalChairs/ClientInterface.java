package musicalChairs;

import java.io.IOException;
import java.util.Scanner;
import static musicalChairs.Client.SERVER_RESPONSE;


public class ClientInterface {

    
    public static Object getRequest(String stringFromClient) {
      
                System.out.println( "\n" + "Welcome to a game of " + stringFromClient + ". Please wait until all of the" +
                        " players have arrived." + "\n" +" Meanwhile write PLAY in the terminal.");             
                Scanner sc = new Scanner(System.in);
                String PLAY = sc.nextLine();
               
        return PLAY;
    }
    
     //Processes the messsage from the server, depending on the SERVER_RESPONSE the 
    //different cases states what the client should print. 
    public static void processMessageFromServer() throws IOException {
        switch (SERVER_RESPONSE) {
            case ("WINNER"):
                System.out.println("\n" + "You are the winner!");
                break;
            case ("LOSER"):
                System.out.println("\n" + "You did not get a chair and therefore lost");
                break;
            case ("ADVANCED"):
                System.out.println("\n" + "You advanced to the next round!");
                break;
            case ("SIT DOWN"):
                System.out.println("\n" + "Sit down!" + "\n" +
                    "i______i" + "\n" +
                    "I______I" + "\n" +
                    "I      I" + "\n" +
                    "I______I" + "\n" +
                   "/      /I" + "\n" +
                  "(______( I" + "\n" +
                  "I I    I I" + "\n" +
                  "I      I");
                break;
            case ("GET READY"):
                System.out.println("\n" + "The music is playing..." + "\n" + 
                
                "┈┏━┓┈┈┈┈┈┏╯┈┈┈┈┏╯┈" + "\n" +
                "┈┣━┫┈┈┈┈┈┣╯┈┈┈┈┣╯┈" + "\n" +
                "╭┫╭┫┈┈┃┈╭┫┈┈┃┈╭┫┈┈" + "\n" +
                "╰╯╰╯┈╭┫┈╰╯┈╭┫┈╰╯┈┈" + "\n" +
                "┈┈┈┈┈╰╯┈┈┈┈╰╯┈┈┈┈┈");
                
                break;
            case ("You wanna play a game?"):
                System.out.println("\n" + "Du har lyckats igen du är så jävla bra!");
                break;
            default:
                System.out.println("No valid response from the server");
        }
    }
    
  
   
}
