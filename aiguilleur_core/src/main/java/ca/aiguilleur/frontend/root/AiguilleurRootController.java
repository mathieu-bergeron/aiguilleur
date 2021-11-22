package ca.aiguilleur.frontend.root;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pages.PagesView;
import ca.ntro.app.frontend.RootController;
import ca.ntro.app.frontend.controllers.tasks.TaskCreator;

public class AiguilleurRootController implements RootController {

	@Override
	public void createTasks(TaskCreator creator) {

		creator.when().viewLoaded()
			   .and().subViewLoaded(MenuView.class)
		       .execute(results -> {
		    	   
		    	   AiguilleurRootView view = results.getView(AiguilleurRootView.class);
		    	   MenuView menuView = results.getView(MenuView.class);

		    	   view.displayMenuView(menuView);
		       });

		creator.when().viewLoaded()
			   .and().subViewLoaded(PagesView.class)
		       .execute(results -> {
		    	   
		    	   AiguilleurRootView view = results.getView(AiguilleurRootView.class);
		    	   PagesView pagesView = results.getView(PagesView.class);

		    	   view.displayPagesView(pagesView);
		       });
		
		
	}

}
