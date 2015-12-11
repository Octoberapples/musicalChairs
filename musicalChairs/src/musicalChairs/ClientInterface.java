package musicalChairs;

import java.util.Scanner;


public class ClientInterface {

    
    public static Object getRequest(String stringFromClient) {
      
                System.out.println("Welcome to a game of " + stringFromClient + ". Please wait until all of the" +
                        " players have arrived." + "\n" +" Meanwhile write PLAY in the terminal.");             
                Scanner sc = new Scanner(System.in);
                String PLAY = sc.nextLine();
               
        return PLAY;
    }
  
   
}
