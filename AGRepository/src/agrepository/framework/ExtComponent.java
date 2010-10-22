package agrepository.framework;

import org.apache.log4j.Logger;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

public abstract class ExtComponent extends CustomComponent {
   private static final Logger LOG = Logger.getLogger(ExtComponent.class);
   private static final long serialVersionUID = -4988692704729896908L;
   protected VerticalLayout rootLayout;
   protected UIBuilder uiBuilder;

   public ExtComponent() {
      this(true);
   }

   public ExtComponent(boolean initUI) {
      rootLayout = new VerticalLayout();
      setCompositionRoot(rootLayout);
      uiBuilder = new UIBuilder(rootLayout);
      if (initUI) {
         createUI();
      }
   }

   public abstract void createUI();
}
