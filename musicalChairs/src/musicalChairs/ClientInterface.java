package musicalChairs;


public class ClientInterface {

    
    public static Object getRequest(String stringFromClient) {
      
                System.out.println("Welcome to a game of " + stringFromClient + ". Please wait until all of the" +
                        " players have arrived");
        
        return "EXIT";
    }
  
   
}
