package agrepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.extensions.FwApplication;

public class Application extends FwApplication {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(Application.class);
   private static final long serialVersionUID = -6919355554494428313L;

   @Override
   public void createUI() {
      button(String.format("%s - %s", getVersion(), getTranslator().get("application.title")));
   }
}
