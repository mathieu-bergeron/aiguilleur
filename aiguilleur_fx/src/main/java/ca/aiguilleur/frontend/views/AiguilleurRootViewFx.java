package ca.aiguilleur.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pages.PagesView;
import ca.aiguilleur.frontend.root.RootView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AiguilleurRootViewFx implements RootView, Initializable {
	
	@FXML
	Pane menuContainer;

	@FXML
	Pane pageContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		pageContainer.getChildren().add(new Button("Hello world!"));
	}

	@Override
	public void displayMenuView(MenuView menuView) {
		
	}

	@Override
	public void displayPagesView(PagesView pagesView) {
		
	}
}

