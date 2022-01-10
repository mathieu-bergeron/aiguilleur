package ca.aiguilleur.frontend.root;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.frontend.Window;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptor;

import static ca.ntro.app.frontend.tasks.Factory.*;

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

	private static TypedFrontendTaskDescriptor<RootView> createRootView(FrontendTaskCreator to) {
		
		return to.create(view(RootView.class))
		
		  .waitFor(viewLoader(RootView.class))

		  .thenExecute(inputs -> {
		    	   
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
			   
			   return queueView;
		   });
	}

	private static void createGameView(FrontendTaskCreator to) {
		to.create(view(GameView.class))

		  .waitFor(viewLoader(GameView.class))
		
		  .thenExecute(inputs -> {
			   
			   ViewLoader<GameView> viewLoader = inputs.get(viewLoader(GameView.class));

			   GameView pongView = viewLoader.createView();
			   
			   return pongView;
		   });
	}

	private static TypedFrontendTaskDescriptor<?> installRootView(FrontendTaskCreator to) {
		
		return to.implement(task("installRootView"))

		  .waitFor(window())

		  .waitFor(view(RootView.class))

		  .thenExecute(inputs -> {
		    	   
			  Window   window   = inputs.get(window());
			  RootView rootView = inputs.get(view(RootView.class));

			  window.installRootView(rootView);

		  }).getTask();
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

	private static void installGameView(FrontendTaskCreator to) {

		to.implement(task("installPongView"))

		  .waitFor(view(RootView.class))

		  .waitFor(view(GameView.class))

		  .thenExecute(inputs -> {
		    	   
			  RootView rootView = inputs.get(view(RootView.class));
			  GameView pongView = inputs.get(view(GameView.class));

			  rootView.installGameView(pongView);
		  });
	}
}
