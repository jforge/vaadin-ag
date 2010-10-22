package agrepository.framework.extensions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.utilities.FwUIBuilder;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public abstract class FwExtComponent extends CustomComponent {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwExtComponent.class);
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
