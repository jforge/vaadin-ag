package agrepository.framework;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.utilities.FwParameters;
import agrepository.framework.utilities.FwTranslator;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public abstract class FwListener implements Button.ClickListener, Serializable {
   private static final Log LOG = LogFactory.getLog(FwListener.class);
   private boolean lock;
   private FwApplication application;
   private FwTranslator translator;
   @SuppressWarnings("unused")
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
         executeOnce(new FwEvent(event));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   protected synchronized void executeOnce(FwEvent event) throws Exception {
      if (lock) {
         return;
      }
      lock = true;
      try {
         execute(event);
      } catch (Exception exception) {
         throw exception;
      } finally {
         lock = false;
      }
   }

   public abstract void execute(FwEvent event) throws Exception;
}
