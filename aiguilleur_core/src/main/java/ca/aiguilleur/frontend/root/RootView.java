package ca.aiguilleur.frontend.root;

import ca.aiguilleur.frontend.pong.GameView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.ntro.app.frontend.View;

public interface RootView extends View {

	void installQueueView(QueueView queueView);
	void installGameView(GameView pongView);

}
