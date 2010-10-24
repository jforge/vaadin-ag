package agrepository.framework.utilities;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.extensions.FwApplication;

import com.vaadin.ui.ComponentContainer;

public class FwUIBuilder implements Serializable {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwUIBuilder.class);
   private static final long serialVersionUID = -2607922449734683983L;
   private ComponentContainer container;

   public FwUIBuilder(ComponentContainer container) {
      this.container = container;
   }

   public FwApplication getApplication() {
      return FwApplication.current();
   }
}
