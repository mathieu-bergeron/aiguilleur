package fx;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxMain extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		String xmlPath = "/root.xml";
		
		URL xmlFile = FxMain.class.getResource(xmlPath);
		
		if(xmlFile == null) {
			throw new RuntimeException("Not found " + xmlPath);
		}
		
		FXMLLoader loader = new FXMLLoader(xmlFile);
		
		Parent parent = loader.load();
		
		Scene scene = new Scene(parent, 400, 600);
		
		Object ctrl = loader.getController();
		
		primaryStage.setScene(scene);

		primaryStage.show();

	}

}
