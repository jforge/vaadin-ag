package agrepository.framework.dialogs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.FwApplication;
import agrepository.framework.utilities.FwParameters;
import agrepository.framework.utilities.FwTranslator;

@SuppressWarnings("serial")
public class FwLogoutDialog extends FwClosableDialog {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwLogoutDialog.class);
   private FwApplication application;
   @SuppressWarnings("unused")
   private FwTranslator translator;
   @SuppressWarnings("unused")
   private FwParameters parameters;

   @Override
   public void createUI() {
      application = FwApplication.current();
      translator = application.getTranslator();
      parameters = application.getParameters();
      super.createUI();
      getRootLayout().setMargin(true);
      setCaption(application.getFullName());
      center();
   }

   public void show() {
      requestRepaint();
      application.getMainWindow().addWindow(this);
   }

   public void hide() {
      setVisible(false);
      application.getMainWindow().removeWindow(this);
      application.close();
   }
}
