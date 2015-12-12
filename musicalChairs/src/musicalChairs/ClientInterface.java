package musicalChairs;

import java.io.IOException;
import java.util.Scanner;
import static musicalChairs.Client.SERVER_RESPONSE;


public class ClientInterface {

    
    // Just nu gör den här funktionen att den skriver ut en rad när man har blivit connectad
    // Skriv HEJ eller PLAY för att få ett svar från servern som sedan processeras av funktionen
    // nedanför
    public static Object getRequest(String stringFromClient) {
      
                System.out.println( "\n" + "Welcome to a game of " + stringFromClient + ". Please wait until all of the" +
                        " players have arrived." + "\n" +" Meanwhile write PLAY or HEJ in the terminal.");             
                Scanner sc = new Scanner(System.in);
                String PLAY = sc.nextLine();
               
        return PLAY;
    }
    
    //Processerar server response
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
                System.out.println(
                 "\t" +   "            i______i" + "\n" +
                 "\t" +   "            I______I" + "\n" +
                 "\t" +   "            I      I" + "\n" +
                 "\t" +   " SIT DOWN!  I______I" + "\n" +
                 "\t" +   "           /      /I" + "\n" +
                 "\t" +   "          (______( I" + "\n" +
                 "\t" +   "          I I    I I" + "\n" +
                 "\t" +   "          I      I  ");
                break;
            case ("GET READY"):
                System.out.println("\n" + 
                
                "                            ┈┏━┓┈┈┈┈┈┏╯┈┈┈┈┏╯┈" + "\n" +
                " The music is playing...    ┈┣━┫┈┈┈┈┈┣╯┈┈┈┈┣╯┈" + "\n" +
                "                            ╭┫╭┫┈┈┃┈╭┫┈┈┃┈╭┫┈┈" + "\n" +
                "                            ╰╯╰╯┈╭┫┈╰╯┈╭┫┈╰╯┈┈" + "\n" +
                "                            ┈┈┈┈┈╰╯┈┈┈┈╰╯┈┈┈┈┈");
                
                break;
            case ("You wanna play a game?"):
                System.out.println("\n" + "Du har lyckats igen du är så jävla bra!");
                break;
            default:
                System.out.println("No valid response from the server");
        }
    }
    
  
   
}
