package ca.aiguilleur.frontend.root;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pages.PagesView;
import ca.ntro.app.frontend.RootController;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

public class AiguilleurRootController implements RootController {

	@Override
	public void createTasks(FrontendTasks creator) {

		creator.when(viewCreated(AiguilleurRootView.class))
			   .and(viewLoaded(MenuView.class))
		       .execute(results -> {
		    	   
		    	   AiguilleurRootView view = results.getDisplayedView(AiguilleurRootView.class);
		    	   MenuView menuView = results.getViewLoader(MenuView.class).createView();

		    	   view.displayMenuView(menuView);
		       });

		creator.when(viewCreated(AiguilleurRootView.class))
			   .and(viewLoaded(PagesView.class))
		       .execute(results -> {
		    	   
		    	   AiguilleurRootView view = results.getDisplayedView(AiguilleurRootView.class);
		    	   PagesView pagesView = results.getViewLoader(PagesView.class).createView();

		    	   view.displayPagesView(pagesView);
		       });
	}

}
