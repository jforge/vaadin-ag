package agrepository.framework.dialogs;

import agrepository.framework.extensions.FwApplication;
import agrepository.framework.extensions.FwWindow;
import agrepository.framework.utilities.FwParameters;
import agrepository.framework.utilities.FwTranslator;

public class FwLogoutDialog extends FwWindow {
   private static final long serialVersionUID = 7039738281673779753L;
   private FwApplication application;
   private FwTranslator translator;
   private FwParameters parameters;

   public void show() {
      application.getMainWindow().addWindow(this);
   }

   public void hide() {
      setVisible(false);
      application.getMainWindow().removeWindow(this);
      application.close();
   }
}
