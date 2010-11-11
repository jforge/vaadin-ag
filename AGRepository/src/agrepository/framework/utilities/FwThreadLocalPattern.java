package agrepository.framework.utilities;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.FwApplication;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;

@SuppressWarnings("serial")
public class FwThreadLocalPattern implements ApplicationContext.TransactionListener, Serializable {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwThreadLocalPattern.class);
   private static ThreadLocal<FwApplication> current = new ThreadLocal<FwApplication>();
   private FwApplication application;

   public FwThreadLocalPattern(FwApplication application) {
      this.application = application;
      application.getContext().addTransactionListener(this);
   }

   @Override
   public void transactionStart(Application application, Object transactionData) {
      if ((this.application == application) && (current != null)) {
         current.set((FwApplication) application);
      }
   }

   @Override
   public void transactionEnd(Application application, Object transactionData) {
      if ((this.application == application) && (current != null)) {
         current.set(null);
      }
   }

   public static FwApplication current() {
      return current.get();
   }
}
