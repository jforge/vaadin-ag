package agrepository.framework.dialogs;

import agrepository.framework.FwApplication;
import agrepository.framework.FwWindow;
import agrepository.framework.utilities.FwParameters;
import agrepository.framework.utilities.FwTranslator;

@SuppressWarnings("serial")
public class FwLogoutDialog extends FwWindow {
   private FwApplication application;
   @SuppressWarnings("unused")
   private FwTranslator translator;
   @SuppressWarnings("unused")
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
