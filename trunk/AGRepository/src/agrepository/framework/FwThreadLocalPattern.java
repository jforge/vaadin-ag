package agrepository.framework;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;

public class FwThreadLocalPattern implements ApplicationContext.TransactionListener, Serializable {
   private static final Logger LOG = Logger.getLogger(FwThreadLocalPattern.class);
   private static final long serialVersionUID = -2613987107755677286L;
   private static ThreadLocal<FwExtApplication> current = new ThreadLocal<FwExtApplication>();
   private FwExtApplication application;

   public FwThreadLocalPattern(FwExtApplication application) {
      this.application = application;
      application.getContext().addTransactionListener(this);
   }

   @Override
   public void transactionStart(Application application, Object transactionData) {
      if ((this.application == application) && (current != null)) {
         current.set((FwExtApplication) application);
      }
   }

   @Override
   public void transactionEnd(Application application, Object transactionData) {
      if ((this.application == application) && (current != null)) {
         current.set(null);
      }
   }

   public static FwExtApplication current() {
      return current.get();
   }
}
