package ca.aiguilleur.frontend.menu;

import ca.aiguilleur.messages.DisplayPongMessage;
import ca.aiguilleur.messages.DisplayQueueMessage;
import ca.ntro.app.frontend.View;

public interface MenuView extends View {

	void installDisplayQueueMessage(DisplayQueueMessage displayQueueMessage);
	void installDisplayPongMessage(DisplayPongMessage displayPongMessage);

}
