package musicalChairs;

import java.util.Scanner;


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
    public static void processMessageFromServer(Object serverResponse) {
        if (serverResponse instanceof String) {

            switch ((String) serverResponse) {
                case "GET READY":
                     System.out.println("\n" + 
                
                "                            ┈┏━┓┈┈┈┈┈┏╯┈┈┈┈┏╯┈" + "\n" +
                " The music is playing...    ┈┣━┫┈┈┈┈┈┣╯┈┈┈┈┣╯┈" + "\n" +
                "                            ╭┫╭┫┈┈┃┈╭┫┈┈┃┈╭┫┈┈" + "\n" +
                "                            ╰╯╰╯┈╭┫┈╰╯┈╭┫┈╰╯┈┈" + "\n" +
                "                            ┈┈┈┈┈╰╯┈┈┈┈╰╯┈┈┈┈┈");
                    break;
                case "SIT DOWN":
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
                default:
                    System.out.println("Something from the server came back");
                    break;
            }
    }
    
    
    /*
    public static void processMessageFromServer(Object serverResponse)  {
        if ("SIT DOWN".equals(serverResponse)) {
             System.out.println(
                 "\t" +   "            i______i" + "\n" +
                 "\t" +   "            I______I" + "\n" +
                 "\t" +   "            I      I" + "\n" +
                 "\t" +   " SIT DOWN!  I______I" + "\n" +
                 "\t" +   "           /      /I" + "\n" +
                 "\t" +   "          (______( I" + "\n" +
                 "\t" +   "          I I    I I" + "\n" +
                 "\t" +   "          I      I  ");
             
        }if ("GET READY".equals(serverResponse)) {
             System.out.println("\n" + 
                
                "                            ┈┏━┓┈┈┈┈┈┏╯┈┈┈┈┏╯┈" + "\n" +
                " The music is playing...    ┈┣━┫┈┈┈┈┈┣╯┈┈┈┈┣╯┈" + "\n" +
                "                            ╭┫╭┫┈┈┃┈╭┫┈┈┃┈╭┫┈┈" + "\n" +
                "                            ╰╯╰╯┈╭┫┈╰╯┈╭┫┈╰╯┈┈" + "\n" +
                "                            ┈┈┈┈┈╰╯┈┈┈┈╰╯┈┈┈┈┈");
        }
            
    }
*/
    }
}
