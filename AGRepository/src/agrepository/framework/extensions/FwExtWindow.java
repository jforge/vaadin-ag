package agrepository.framework.extensions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.utilities.FwUIBuilder;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class FwExtWindow extends Window {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwExtWindow.class);
   private static final long serialVersionUID = -3030706738593291963L;
   protected FwUIBuilder uiBuilder;
   protected VerticalLayout rootLayout;

   public FwExtWindow() {
      this(true);
   }

   public FwExtWindow(boolean initUI) {
      rootLayout = new VerticalLayout();
      setContent(rootLayout);
      uiBuilder = new FwUIBuilder(this);
      if (initUI) {
         createUI();
      }
   }

   public void createUI() {
   }
}
