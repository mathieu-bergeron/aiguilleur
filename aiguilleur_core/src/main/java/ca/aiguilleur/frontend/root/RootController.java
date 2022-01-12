package ca.aiguilleur.frontend.root;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptor;
import ca.ntro.app.services.Window;

import static ca.ntro.app.frontend.tasks.Factory.*;

import ca.aiguilleur.frontend.events.EvtShowGameView;
import ca.aiguilleur.frontend.events.EvtShowQueueView;
import ca.aiguilleur.frontend.pong.GameView;
import ca.aiguilleur.frontend.queue.QueueView;

public class RootController {
	
	public static void createTasks(FrontendTaskCreator to) {

		showWindow(to);
		
		/*
		 * FIXME: installRootView(to) creates a new currentTask
	     *        (forgets taskGroup's currentTask
		 * 
		 * TODO:  the getTask() descriptor 
		 *        must remember all the waitFor previous tasks
		 *        (these will be pushed inside the taskGroup)
		 */
		//to.define(taskGroup(view(RootView.class)))
		  //.addSubTask(createRootView(to))
		  //.addSubTask(installRootView(to));
		
		createRootView(to);
		installRootView(to);
		
		createQueueView(to);
		installQueueView(to);

		createGameView(to);
		installGameView(to);
		
		showQueueView(to);
		showGameView(to);
	}

	private static void showWindow(FrontendTaskCreator to) {

		to.implement(task("showWindow"))
		
		  .waitFor(window())
		  
		  .thenExecute(inputs -> {

			  Window window = inputs.get(window());
			  
			  //window.resize(600, 400);
			  window.fullscreen(true);
			  window.show();
		  });
	}

	private static TypedFrontendTaskDescriptor<RootView> createRootView(FrontendTaskCreator to) {
		
		return to.create(view(RootView.class))
		
		  .waitFor(viewLoader(RootView.class))

		  .thenExecute(inputs -> {

			  System.out.println("createRootView");
		    	   
			  ViewLoader<RootView> viewLoader = inputs.get(viewLoader(RootView.class));
			  
			  RootView rootView = viewLoader.createView();

			  return rootView;

		  }).getTask();
	}

	private static void createQueueView(FrontendTaskCreator to) {
		to.create(view(QueueView.class))
		
		  .waitFor(viewLoader(QueueView.class))
		
		  .thenExecute(inputs -> {
			   
			   ViewLoader<QueueView> viewLoader = inputs.get(viewLoader(QueueView.class));

			   QueueView queueView = viewLoader.createView();
			   
			   queueView.installShowGameViewEvent(NtroApp.events().newEvent(EvtShowGameView.class));

			   return queueView;
		   });
	}

	private static void createGameView(FrontendTaskCreator to) {
		to.create(view(GameView.class))

		  .waitFor(viewLoader(GameView.class))
		
		  .thenExecute(inputs -> {

			  System.out.println("createGameView");
			   
			   ViewLoader<GameView> viewLoader = inputs.get(viewLoader(GameView.class));

			   GameView gameView = viewLoader.createView();

			   gameView.installShowQueueViewEvent(NtroApp.events().newEvent(EvtShowQueueView.class));
			   
			   return gameView;
		   });
	}

	private static TypedFrontendTaskDescriptor<?> installRootView(FrontendTaskCreator to) {
		
		return to.implement(task("installRootView"))

		  .waitFor(window())

		  .waitFor(view(RootView.class))

		  .thenExecute(inputs -> {
			  
			  System.out.println("installRootView");
		    	   
			  Window   window   = inputs.get(window());
			  RootView rootView = inputs.get(view(RootView.class));

			  window.installRootView(rootView);

		  }).getTask();
	}

	private static void installQueueView(FrontendTaskCreator to) {

		to.implement(task("installQueueView"))

		  .waitFor(task("installRootView"))

		  .waitFor(view(QueueView.class))

		  .thenExecute(inputs -> {
		    	   
			  RootView rootView   = inputs.get(view(RootView.class));
			  QueueView queueView = inputs.get(view(QueueView.class));

			  rootView.installQueueView(queueView);
		  });
	}

	private static void installGameView(FrontendTaskCreator to) {

		to.implement(task("installGameView"))

		  .waitFor(task("installRootView"))

		  .waitFor(view(GameView.class))

		  .thenExecute(inputs -> {
		    	   
			  RootView rootView = inputs.get(view(RootView.class));
			  GameView pongView = inputs.get(view(GameView.class));

			  rootView.installGameView(pongView);
		  });
	}

	private static void showQueueView(FrontendTaskCreator to) {

		to.implement(task("showQueueView"))

		  .waitFor(task("installQueueView"))

		  .waitFor(event(EvtShowQueueView.class))

		  .thenExecute(inputs -> {
			  
			  RootView rootView = inputs.get(view(RootView.class));
			  
			  rootView.showQueueView();
		  });
	}

	private static void showGameView(FrontendTaskCreator to) {

		to.implement(task("showGameView"))

		  .waitFor(task("installGameView"))

		  .waitFor(event(EvtShowGameView.class))

		  .thenExecute(inputs -> {
			  
			  RootView rootView = inputs.get(view(RootView.class));
			  
			  rootView.showGameView();
		  });
	}
}
