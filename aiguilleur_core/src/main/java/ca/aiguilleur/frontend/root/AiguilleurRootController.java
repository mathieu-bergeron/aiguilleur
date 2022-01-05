package ca.aiguilleur.frontend.root;


import ca.ntro.app.frontend.Window;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;
import ca.ntro.app.frontend.controllers.tasks.ViewLoader;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;
import static ca.ntro.app.frontend.controllers.tasks.TaskInputs.*;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.messages.MsgAddAppointment;

public class AiguilleurRootController {

	public static void createTasks(FrontendTasks inOrder) {
		
		// Sem02: show window (empty)
		
		// install splash view
		// show window
		
		// connect to server
		// switch to root view
		// resize window
		
		// install Root/Menu/Queue views
		// show window

		// install PongView (can be after)

		createRootView(inOrder);
		showMenuView(inOrder);

	}

	private static void createRootView(FrontendTasks to) {
		
		to.create(view(AiguilleurRootView.class))
		
		  .waitFor(window())

		  .waitFor(viewLoaderFor(AiguilleurRootView.class))

		  .waitFor(message(MsgAddAppointment.class))
		
		  .thenExecute((inputs, notify) -> {
		    	   
			  Window window = inputs.get(window());
			  ViewLoader<AiguilleurRootView> viewLoader = inputs.get(viewLoaderFor(AiguilleurRootView.class));

			  AiguilleurRootView rootView = viewLoader.createView();

			  window.resize(600,400);
			  window.installRootView(rootView);
			  window.show();
			   
			  notify.created(rootView);

		  })
		  
		  .onCancelDo(() -> {
			  
			  
		  })
		  
		  .onFailureDo(exception -> {
			  
			  
		  })
		  
		  .getTask();
	}

	private static void createMenuView(FrontendTasks to) {
		to.create(view(MenuView.class))
		
		  .waitFor(view(AiguilleurRootView.class))
		  
		  .waitFor(viewLoaderFor(MenuView.class))
		
		  .thenDo((inputs, notify) -> {
			   
			   AiguilleurRootView rootView = inputs.getView(AiguilleurRootView.class);
			   ViewLoader<MenuView> viewLoader = inputs.getViewLoader(MenuView.class);

			   MenuView menuView = viewLoader.createView();
			   
			   rootView.displayMenuView(menuView);

			   notify.created(menuView);

		   });
		       
	}

	private static void blahBlah(FrontendTasks to) {
		to.setCondition("blahBlah")
		
		  .waitFor(view(AiguilleurRootView.class))
		  
		  .waitFor(viewLoaderFor(MenuView.class))
		
		  .thenDo((inputs, notify) -> {
			   
			   notify.conditionIs(true);
		   });
		       
	}
}
