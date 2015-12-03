package musicalChairs;



public class ServerResponseRepository {

    public String getResponseChoices(String state) {
        switch (state) {
            case "WINNER":
                return getChoicesWinner();
            case "LOSER":
                return getChoicesLoser();
            case "IN_PLAYER_QUEUE":
                return getChoicesInPlayerQueue();
            case "ADVANCED":
                return getChoicesAdvanced();
            case "SIT_DOWN":
                return getChoicesSitDown();
            case "GET_READY":
                return getChoicesGetReady();
        }
        return "";
    }


    private String getChoicesInPlayerQueue() {
        return "Force Start";
    }

    private String getChoicesWinner() {
        return "WINNER";
    }

    private String getChoicesLoser() {
        return "LOSER";
    }

    private String getChoicesAdvanced() {
        return "ADVANCED";
    }

    private String getChoicesSitDown() {
        return "Sit Down";
    }

    private String getChoicesGetReady() {
        return "Get Ready";
    }

}
