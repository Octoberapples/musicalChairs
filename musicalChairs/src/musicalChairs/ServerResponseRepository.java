package musicalChairs;

public class ServerResponseRepository {

	public String[] getResponseChoices(String state) {
		switch (state) {
		case "RequestToJoin":
			return getChoicesRequestToJoin(); //DET HÄR CASET ÄR NOG ONÖDIGT

		case "InPlayerQueue":
			return getChoicesInPlayerQueue();
		case "InGame":
			return getChoicesInGame();
		}
		String[] x = new String[0];
		return x;
	}

	private String[] getChoicesInGame() {
		String[] response = new String[2];
		response[0] = ("Sit Down");
		response[1] = ("Leave Game");
		return response;
	}

	private String[] getChoicesInPlayerQueue() {
		String[] response = new String[2];
		response[0] = ("Force start");
		response[1] = ("Leave Game");
		return response;
	}

	private String[] getChoicesRequestToJoin() { //KANSKE ONÖDIG
		String[] response = new String[1];
		response[0] = ("Join Game");
		return response;
	}

}
