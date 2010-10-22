package agrepository.framework.extensions;

import org.apache.log4j.Logger;

import agrepository.framework.utilities.FwUIBuilder;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public abstract class FwExtComponent extends CustomComponent {
   private static final Logger LOG = Logger.getLogger(FwExtComponent.class);
   private static final long serialVersionUID = -4988692704729896908L;
   protected VerticalLayout rootLayout;
   protected FwUIBuilder uiBuilder;

   public FwExtComponent() {
      this(true);
   }

   public FwExtComponent(boolean initUI) {
      rootLayout = new VerticalLayout();
      setCompositionRoot(rootLayout);
      uiBuilder = new FwUIBuilder(rootLayout);
      if (initUI) {
         createUI();
      }
   }

   public abstract void createUI();
}
