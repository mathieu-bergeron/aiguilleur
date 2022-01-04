package ca.aiguilleur.frontend.menu;

import ca.aiguilleur.messages.MsgDisplayPong;
import ca.aiguilleur.messages.MsgDisplayQueue;
import ca.ntro.app.frontend.View;

public interface MenuView extends View {

	void installDisplayQueueMessage(MsgDisplayQueue displayQueueMessage);
	void installDisplayPongMessage(MsgDisplayPong displayPongMessage);

}
