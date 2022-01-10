package ca.aiguilleur.frontend.events;

import ca.ntro.app.frontend.events.Event;
import ca.ntro.app.frontend.events.EventNtro;

public class EvtShowGameView 

       extends EventNtro

       implements Event {
	
	private String gameId;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}


}
