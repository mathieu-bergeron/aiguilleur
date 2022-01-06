package ca.aiguilleur.frontend.root;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.ntro.app.frontend.View;

public interface RootView extends View {

	void installMenuView(MenuView menuView);
	void installQueueView(QueueView queueView);
	void installPongView(PongView pongView);

}
