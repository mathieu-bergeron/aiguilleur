package ca.aiguilleur.frontend.root;


import ca.ntro.app.frontend.Window;
import ca.ntro.app.frontend.controllers.tasks.FrontendTaskCreator;
import ca.ntro.app.frontend.controllers.tasks.ViewLoader;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTaskCreator.*;
import static ca.ntro.app.frontend.controllers.tasks.TaskInputs.*;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.messages.MsgAddAppointment;

public class AiguilleurRootController {

	public static void createTasks(FrontendTaskCreator to) {

		showWindow(to);
		createRootView(to);
		createMenuView(to);

	}

	private static void showWindow(FrontendTaskCreator to) {

		to.implement(task("showWindow"))
		
		  .waitFor(window())
		  
		  .thenExecute((inputs, notify) -> {
			  
			  Window window = inputs.get(window());
			  
			  window.resize(600, 400);
			  window.show();
		  });
	}
		

	private static void createRootView(FrontendTaskCreator to) {
		
		to.create(view(RootView.class))
		
		  .waitFor(window())

		  .waitFor(viewLoader(RootView.class))

		  .thenExecute((inputs, notify) -> {
		    	   
			  Window               window     = inputs.get(window());
			  ViewLoader<RootView> viewLoader = inputs.get(viewLoader(RootView.class));

			  RootView rootView = viewLoader.createView();

			  window.installRootView(rootView);
			  
			  notify.created(rootView);
		  });
	}

	private static void createMenuView(FrontendTaskCreator to) {
		to.create(view(MenuView.class))
		
		  .waitFor(view(RootView.class))

		  .waitFor(viewLoader(MenuView.class))
		
		  .thenExecute((inputs, notify) -> {
			   
			   RootView             rootView   = inputs.get(view(RootView.class));
			   ViewLoader<MenuView> viewLoader = inputs.get(viewLoader(MenuView.class));

			   MenuView menuView = viewLoader.createView();
			   
			   rootView.displayMenuView(menuView);

			   notify.created(menuView);
		   });
	}

}
