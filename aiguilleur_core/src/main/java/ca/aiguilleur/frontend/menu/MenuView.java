package ca.aiguilleur.frontend.menu;

import ca.aiguilleur.messages.DisplayPong;
import ca.aiguilleur.messages.DisplayQueue;
import ca.ntro.app.frontend.View;

public interface MenuView extends View {

	void installDisplayQueueMessage(DisplayQueue displayQueueMessage);
	void installDisplayPongMessage(DisplayPong displayPongMessage);

}
