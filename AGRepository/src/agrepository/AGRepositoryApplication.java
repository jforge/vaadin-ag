package agrepository;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class AGRepositoryApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("AG Repository Application");
		Label label = new Label("Hello Vaadin user");
		mainWindow.addComponent(label);
		setMainWindow(mainWindow);
	}

}
