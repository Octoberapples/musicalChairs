package musicalChairs;


class ServerGameProtocol {

    // gets a String from client and uses it
    public static Object handleClientInput(Object clientResponse) {
        String serverResponse;
        if (clientResponse instanceof String) {

            switch ((String) clientResponse) {
                case "FORCE START":
                    System.out.println("A client forcestarted the game");
                    serverResponse = "A client forcestarted the game";
                    return serverResponse;
                default:
                    System.out.println("A client joined the game");
                    serverResponse = "FORCE START";
                    return serverResponse;
            }

        } else {
            return "CLIENT_CORRUPTED";
        }
    }

}
