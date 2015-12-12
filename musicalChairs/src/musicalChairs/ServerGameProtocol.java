package musicalChairs;

//Klassen där vi hanterar det vi får från klienten och skickar tillbaka
//ett serverResponse
class ServerGameProtocol {

    public static Object handleClientInput(Object clientResponse) {
        Object serverResponse = null;
            if("PLAY".equals(clientResponse)) {
                System.out.println("What the server does with the clientResponse:'A client wants to play'");
                serverResponse = "GET READY";
                       
            }else if("HEJ".equals(clientResponse)) {
                System.out.println("What the server does with the clientResponse:'A client wants to play'");
                serverResponse = "SIT DOWN";
                       
            }
            if(clientResponse instanceof Long){
                    System.out.println("Fick timestampen");
                    serverResponse = "WINNER";
                }
            
            return serverResponse;
    }   

}
