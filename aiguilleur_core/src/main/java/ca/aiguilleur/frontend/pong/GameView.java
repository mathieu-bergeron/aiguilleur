package ca.aiguilleur.frontend.pong;

import ca.aiguilleur.frontend.events.EvtShowQueueView;
import ca.ntro.app.frontend.ViewModel;

public interface GameView extends ViewModel {

	void installShowQueueViewEvent(EvtShowQueueView event);

}
