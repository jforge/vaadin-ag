package agrepository.framework.utilities;

import java.io.Serializable;

import org.apache.log4j.Logger;

import agrepository.framework.ExtApplication;

import com.vaadin.ui.ComponentContainer;

public class UIBuilder implements Serializable {
   private static final Logger LOG = Logger.getLogger(UIBuilder.class);
   private static final long serialVersionUID = -2607922449734683983L;
   private ComponentContainer container;

   public UIBuilder(ComponentContainer container) {
      this.container = container;
   }

   public ExtApplication getApplication() {
      return ExtApplication.current();
   }
}
