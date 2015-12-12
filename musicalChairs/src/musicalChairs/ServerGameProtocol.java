package musicalChairs;

//Klassen där vi hanterar det vi får från klienten och skickar tillbaka
//ett serverResponse
class ServerGameProtocol {

    public static Object handleClientInput(Object clientResponse) {
        Object serverResponse = null;
            if("PLAY".equals(clientResponse)) {
                System.out.println("What the server does with the clientResponse:" +
                            "\n" +  "'A client wants to play'");
                serverResponse = "GET READY";
                       
            }if("HEJ".equals(clientResponse)) {
                System.out.println("What the server does with the clientResponse:" +
                            "\n" +  "'A client wants to play'");
                serverResponse = "SIT DOWN";
                       
            }
            
            return serverResponse;
    }   

}
