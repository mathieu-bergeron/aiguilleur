package ca.aiguilleur.frontend.root;

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
		//createMenuView(to);
		createQueueView(to);
		createPongView(to);

		installRootView(to);
		//installMenuView(to);
		installQueueView(to);
		installPongView(to);
		
		/* TODO
		to.define(taskGroup(view(RootView.class)))
		  .addSubTask(createRootView(to))
		  .addSubTask(installRootView(to));
		  */
		
		
	}

	private static void showWindow(FrontendTaskCreator to) {

		to.implement(task("showWindow"))
		
		  .waitFor(window())
		  
		  .thenExecute(inputs -> {
			  
			  Window window = inputs.get(window());
			  
			  window.resize(600, 400);
			  window.show();

		  });
	}

	private static void createRootView(FrontendTaskCreator to) {
		
		to.create(view(RootView.class))
		
		  .waitFor(viewLoader(RootView.class))

		  .thenExecute(inputs -> {
		    	   
			  ViewLoader<RootView> viewLoader = inputs.get(viewLoader(RootView.class));
			  
			  RootView rootView = viewLoader.createView();

			  return rootView;
		  });
	}

	private static void createMenuView(FrontendTaskCreator to) {
		to.create(view(MenuView.class))
		
		  .waitFor(viewLoader(MenuView.class))
		
		  .thenExecute(inputs -> {
			   
			   ViewLoader<MenuView> viewLoader = inputs.get(viewLoader(MenuView.class));

			   MenuView menuView = viewLoader.createView();
			   
			   return menuView;
		   });
	}

	private static void createQueueView(FrontendTaskCreator to) {
		to.create(view(QueueView.class))
		
		  .waitFor(viewLoader(QueueView.class))
		
		  .thenExecute(inputs -> {
			   
			   ViewLoader<QueueView> viewLoader = inputs.get(viewLoader(QueueView.class));

			   QueueView queueView = viewLoader.createView();
			   
			   return queueView;
		   });
	}

	private static void createPongView(FrontendTaskCreator to) {
		to.create(view(PongView.class))

		  .waitFor(viewLoader(PongView.class))
		
		  .thenExecute(inputs -> {
			   
			   ViewLoader<PongView> viewLoader = inputs.get(viewLoader(PongView.class));

			   PongView pongView = viewLoader.createView();
			   
			   return pongView;
		   });
	}

	private static void installRootView(FrontendTaskCreator to) {
		
		to.implement(task("installRootView"))

		  .waitFor(window())

		  .waitFor(view(RootView.class))

		  .thenExecute(inputs -> {
		    	   
			  Window   window   = inputs.get(window());
			  RootView rootView = inputs.get(view(RootView.class));

			  window.installRootView(rootView);
		  });
	}

	private static void installMenuView(FrontendTaskCreator to) {

		to.implement(task("installMenuView"))

		  .waitFor(view(RootView.class))

		  .waitFor(view(MenuView.class))

		  .thenExecute(inputs -> {
		    	   
			  RootView rootView = inputs.get(view(RootView.class));
			  MenuView menuView = inputs.get(view(MenuView.class));

			  rootView.installMenuView(menuView);
		  });
	}

	private static void installQueueView(FrontendTaskCreator to) {

		to.implement(task("installQueueView"))

		  .waitFor(view(RootView.class))

		  .waitFor(view(QueueView.class))

		  .thenExecute(inputs -> {
		    	   
			  RootView rootView   = inputs.get(view(RootView.class));
			  QueueView queueView = inputs.get(view(QueueView.class));

			  rootView.installQueueView(queueView);
		  });
	}

	private static void installPongView(FrontendTaskCreator to) {

		to.implement(task("installPongView"))

		  .waitFor(view(RootView.class))

		  .waitFor(view(PongView.class))

		  .thenExecute(inputs -> {
		    	   
			  RootView rootView = inputs.get(view(RootView.class));
			  PongView pongView = inputs.get(view(PongView.class));

			  rootView.installPongView(pongView);
		  });
	}
}
