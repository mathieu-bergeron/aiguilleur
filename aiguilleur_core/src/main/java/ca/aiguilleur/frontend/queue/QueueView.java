package ca.aiguilleur.frontend.queue;

import ca.aiguilleur.frontend.events.EvtShowGameView;
import ca.ntro.app.frontend.ViewModel;

public interface QueueView extends ViewModel {
	
	void installShowGameViewEvent(EvtShowGameView event);


}
