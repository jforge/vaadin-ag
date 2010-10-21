package agrepository;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class AGRepositoryApplication extends Application {
   private static final long serialVersionUID = -6919355554494428313L;

   @Override
   public void init() {
      Window mainWindow = new Window("Application Generator Repository");
      Label label = new Label("Hello to Application Generator Repository!");
      mainWindow.addComponent(label);
      setMainWindow(mainWindow);
   }
}
