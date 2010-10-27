package agrepository.framework.extensions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.utilities.FwUIBuilder;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class FwWindow extends Window {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwWindow.class);
   private static final long serialVersionUID = -3030706738593291963L;
   private FwUIBuilder uiBuilder;
   private VerticalLayout rootLayout;

   public FwWindow() {
      this(true);
   }

   public FwWindow(boolean initUI) {
      rootLayout = new VerticalLayout();
      setContent(rootLayout);
      uiBuilder = new FwUIBuilder(this);
      if (initUI) {
         createUI();
      }
   }

   public void createUI() {
   }

   public FwUIBuilder getUiBuilder() {
      return uiBuilder;
   }

   public VerticalLayout getRootLayout() {
      return rootLayout;
   }
}
