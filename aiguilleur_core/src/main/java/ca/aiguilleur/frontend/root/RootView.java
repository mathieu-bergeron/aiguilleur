package ca.aiguilleur.frontend.root;

import ca.aiguilleur.frontend.pong.GameView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.ntro.app.frontend.View;

public interface RootView<VN extends Object> extends View<VN> {

	void installQueueView(QueueView<?> queueView);
	void installGameView(GameView<?> pongView);

	void showQueueView();
	void showGameView();

}
