package agrepository.framework.extensions;

import org.apache.log4j.Logger;

import agrepository.framework.utilities.FwUIBuilder;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class FwExtWindow extends Window {
   private static final Logger LOG = Logger.getLogger(FwExtWindow.class);
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
