package agrepository.framework;

import org.apache.log4j.Logger;

import agrepository.framework.utilities.UIBuilder;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ExtWindow extends Window {
   private static final Logger LOG = Logger.getLogger(ExtWindow.class);
   private static final long serialVersionUID = -3030706738593291963L;
   protected UIBuilder uiBuilder;
   protected VerticalLayout rootLayout;

   public ExtWindow() {
      this(true);
   }

   public ExtWindow(boolean initUI) {
      rootLayout = new VerticalLayout();
      setContent(rootLayout);
      uiBuilder = new UIBuilder(this);
      if (initUI) {
         createUI();
      }
   }

   public void createUI() {
   }
}
