package musicalChairs;

public class ServerResponseRepository {

    public String getResponseChoices(String state) {
        switch (state) {
            case "WINNER":
                return getChoicesWinner();
            case "LOSER":
                return getChoicesLoser();
            case "InPlayerQueue":
                return getChoicesInPlayerQueue();
            case "InGame":
                return getChoicesInGame();
        }
        return "";
    }

    private String getChoicesInGame() {
        return "Sit down";
    }

    private String getChoicesInPlayerQueue() {
        return "Force game";
    }

    private String getChoicesWinner() {
        return "WINNER";
    }

    private String getChoicesLoser() {
        return "LOSER";
    }

}
