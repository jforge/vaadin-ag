package agrepository.framework.extensions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.utilities.FwUIBuilder;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public abstract class FwComponent extends CustomComponent {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwComponent.class);
   private static final long serialVersionUID = -4988692704729896908L;
   protected VerticalLayout rootLayout;
   protected FwUIBuilder uiBuilder;

   public FwComponent() {
      this(true);
   }

   public FwComponent(boolean initUI) {
      rootLayout = new VerticalLayout();
      setCompositionRoot(rootLayout);
      uiBuilder = new FwUIBuilder(rootLayout);
      if (initUI) {
         createUI();
      }
   }

   public abstract void createUI();
}
