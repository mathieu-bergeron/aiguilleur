package ca.aiguilleur.frontend.root;


import ca.ntro.app.frontend.View;
import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.frontend.Window;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;

import static ca.ntro.app.frontend.tasks.Factory.*;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueView;

public class RootController {

	public static void createTasks(FrontendTaskCreator to) {

		showWindow(to);
		
		createRootView(to);
		createMenuView(to);
		createQueueView(to);
		createPongView(to);

		installRootView(to);
		installMenuView(to);
		installQueueView(to);
		installPongView(to);
	}

	private static void showWindow(FrontendTaskCreator to) {

		to.implement(task("showWindow"))
		
		  .waitFor(window())
		  
		  .thenExecuteBlocking(inputs -> {
			  
			  Window window = inputs.get(window());
			  
			  window.resize(600, 400);
			  window.show();

		  });
	}

	private static <V extends View> void createView(FrontendTaskCreator to, Class<V> viewClass) {

		to.create(view(viewClass))
		
		  .waitFor(viewLoader(viewClass))

		  .thenExecuteBlocking(inputs -> {
		    	   
			  ViewLoader<V> viewLoader = inputs.get(viewLoader(viewClass));

			  V view = viewLoader.createView();
			  
			  return view;
		  });
	}

		

	private static void createRootView(FrontendTaskCreator to) {
		
		to.create(view(RootView.class))
		
		  .waitFor(window())

		  .waitFor(viewLoader(RootView.class))

		  .thenExecuteBlocking(inputs -> {
		    	   
			  Window               window     = inputs.get(window());
			  ViewLoader<RootView> viewLoader = inputs.get(viewLoader(RootView.class));

			  RootView rootView = viewLoader.createView();

			  window.installRootView(rootView);
			  
			  // FIXME: much better for students
			  //        cannot forget that way
			  return rootView;
		  });
	}

	private static void createMenuView(FrontendTaskCreator to) {
		to.create(view(MenuView.class))
		
		  .waitFor(view(RootView.class))

		  .waitFor(viewLoader(MenuView.class))
		
		  .thenExecuteBlocking(inputs -> {
			   
			   RootView             rootView   = inputs.get(view(RootView.class));
			   ViewLoader<MenuView> viewLoader = inputs.get(viewLoader(MenuView.class));

			   MenuView menuView = viewLoader.createView();
			   
			   rootView.installMenuView(menuView);
			   
			   return menuView;
		   });
	}

	private static void createQueueView(FrontendTaskCreator to) {
		to.create(view(QueueView.class))
		
		  .waitFor(view(RootView.class))

		  .waitFor(viewLoader(QueueView.class))
		
		  .thenExecuteBlocking(inputs -> {
			   
			   RootView              rootView   = inputs.get(view(RootView.class));
			   ViewLoader<QueueView> viewLoader = inputs.get(viewLoader(QueueView.class));

			   QueueView queueView = viewLoader.createView();
			   
			   rootView.installQueueView(queueView);

			   return queueView;
		   });
	}

	private static void createPongView(FrontendTaskCreator to) {
		to.create(view(PongView.class))
		
		  .waitFor(view(RootView.class))

		  .waitFor(viewLoader(PongView.class))
		
		  .thenExecuteBlocking(inputs -> {
			   
			   RootView             rootView   = inputs.get(view(RootView.class));
			   ViewLoader<PongView> viewLoader = inputs.get(viewLoader(PongView.class));

			   PongView pongView = viewLoader.createView();
			   
			   rootView.installPongView(pongView);

			   return pongView;
		   });
	}
}
