package ca.aiguilleur.frontend.pong;

import ca.ntro.app.frontend.ViewModel;

public interface PongView extends ViewModel {
	
	// PongView is responsible for the live-update the of game world
	// Messages are sent to change the model either for:
	//
	//  1. Some user event
	//  2. Some change in the game conditions (e.g. the ball bounces of the ceiling)
	//
	//
	// In either case, the model is updated with the latest state of the game world

}
