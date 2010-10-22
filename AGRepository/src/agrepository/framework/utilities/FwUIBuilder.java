package agrepository.framework.utilities;

import java.io.Serializable;

import org.apache.log4j.Logger;

import agrepository.framework.extensions.FwExtApplication;

import com.vaadin.ui.ComponentContainer;

public class FwUIBuilder implements Serializable {
   private static final Logger LOG = Logger.getLogger(FwUIBuilder.class);
   private static final long serialVersionUID = -2607922449734683983L;
   private ComponentContainer container;

   public FwUIBuilder(ComponentContainer container) {
      this.container = container;
   }

   public FwExtApplication getApplication() {
      return FwExtApplication.current();
   }
}
