package agrepository.framework.extensions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.utilities.FwUIBuilder;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class FwWindow extends Window {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwWindow.class);
   private static final long serialVersionUID = -3030706738593291963L;
   protected FwUIBuilder uiBuilder;
   protected VerticalLayout rootLayout;

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

   /*
    * BUTTONS
    */
   public Button button() {
      return uiBuilder.button();
   }

   public Button button(String caption) {
      return uiBuilder.button(caption);
   }

   public Button button(String caption, ClickListener listener) {
      return uiBuilder.button(caption, listener);
   }
}
