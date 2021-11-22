package ca.aiguilleur.frontend.root;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pages.PagesView;
import ca.ntro.app.frontend.RootView;

public interface AiguilleurRootView extends RootView {

	void displayMenuView(MenuView menuView);
	void displayPagesView(PagesView pagesView);

}
