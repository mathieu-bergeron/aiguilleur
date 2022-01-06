package ca.aiguilleur.messages;

import ca.ntro.app.messages.Message;

public class MsgDisplayPong extends Message {
	
	private String gameId;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
}
