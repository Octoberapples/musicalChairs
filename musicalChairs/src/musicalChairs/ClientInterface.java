package musicalChairs;

import java.util.Scanner;

public class ClientInterface {
	
	
	/**
	 * 
	 * @param choices what we get from the server
	 * @return the client request
	 */

	public String getRequest(String[] choices) {
		int tmp_req;
		Scanner sc = new Scanner(System.in);
		do{
			CheckIfInt(sc, choices.length);
			tmp_req=sc.nextInt();
			
		} while (validInt(tmp_req, choices.length));
		
		return	choices[tmp_req];
	}
	
	/**
	 * 
	 * @param Input from the player
	 * @param length the length of the choice array
	 */
	
    private static void CheckIfInt(Scanner Input, int length) {
        while (!Input.hasNextInt()) {
            System.out.println("Plz a number between 1 and " + length + ": ");
            Input.next();
        }
    }
	
    private boolean validInt(int tmp_req, int length) {
    	return (tmp_req <= 1 && tmp_req >= length);
	}


    /*
    Printthe requests that are availiblefor the client.
    */
	private void printRequest(String[] request) {
	           for (int i = 0; i < request.length; i++) {
                       System.out.println(request[i].toString()); //Kanske måste göra egen toString
            }
	}
}
