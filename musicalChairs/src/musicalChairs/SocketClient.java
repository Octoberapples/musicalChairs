/*
 * Socket client skeleton
 */

import java.net.*;
import java.io.*;
import java.io.OutputStream;

class SocketClient extends InterfaceClient{

	
	private String[] args;
	String server = args[1];
	int port = Integer.parseInt(args[2]);


    public static String runCommand(String input_lg, String server, int port) {
    	String test_Request = "";
    	String test_server = "asa_Dahl@halsamorsan.com";
    	int test_port = 1337;
    	
    	String ans = "";
    	
    	try {
    		Socket echoSocket = new Socket(test_server, test_port);
    		ObjectOutputStream out = 
    				new ObjectOutputStream(echoSocket.getOutputStream());
    		BufferedReader in =	
    				new BufferedReader(
    						new InputStreamReader(echoSocket.getInputStream()));
    		BufferedReader stdIn = 
    				new BufferedReader(
    						new InputStreamReader(System.in));
    		out.writeObject(test_Request);
    		
    		ans = in.readLine();
    		
    		}
    	

    	catch(Exception e){}
    	
    	return ans;
    	
	
    }
	

	


}
