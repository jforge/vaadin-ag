package agrepository.framework.extensions;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.utilities.FwParameters;
import agrepository.framework.utilities.FwTranslator;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

public abstract class FwListener implements Button.ClickListener, Serializable {
   private static final Log LOG = LogFactory.getLog(FwListener.class);
   private static final long serialVersionUID = 6444177796751288132L;
   private boolean lock;
   private FwApplication application;
   private FwTranslator translator;
   private FwParameters parameters;

   public FwListener() {
      application = FwApplication.current();
      translator = application.getTranslator();
      parameters = application.getParameters();
   }

   public void unhandledError(Throwable throwable) {
      unhandledError(translator.get("error.unhandledException"), throwable);
   }

   public void unhandledError(String message, Throwable t) {
      FwApplication application = FwApplication.current();
      if ((application != null) && (application.getUserMessages() != null)) {
         application.getUserMessages().error(message, t);
      } else {
         LOG.error(t);
      }
   }

   @Override
   public void buttonClick(ClickEvent event) {
      try {
         execOnce(new FwEvent(event));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   protected synchronized void execOnce(FwEvent event) throws Exception {
      if (lock) {
         return;
      }
      lock = true;
      try {
         exec(event);
      } catch (Exception exception) {
         throw exception;
      } finally {
         lock = false;
      }
   }

   public abstract void exec(FwEvent event) throws Exception;
}
