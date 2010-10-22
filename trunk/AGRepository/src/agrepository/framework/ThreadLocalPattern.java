package agrepository.framework;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;

public class ThreadLocalPattern implements ApplicationContext.TransactionListener, Serializable {
   private static final Logger LOG = Logger.getLogger(ThreadLocalPattern.class);
   private static final long serialVersionUID = -2613987107755677286L;
   private static ThreadLocal<ExtApplication> current = new ThreadLocal<ExtApplication>();
   private ExtApplication application;

   public ThreadLocalPattern(ExtApplication application) {
      this.application = application;
      application.getContext().addTransactionListener(this);
   }

   @Override
   public void transactionStart(Application application, Object transactionData) {
      if ((this.application == application) && (current != null)) {
         current.set((ExtApplication) application);
      }
   }

   @Override
   public void transactionEnd(Application application, Object transactionData) {
      if ((this.application == application) && (current != null)) {
         current.set(null);
      }
   }

   public static ExtApplication current() {
      return current.get();
   }
}
