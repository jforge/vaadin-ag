package agrepository.framework.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class FwUIBuilder {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwUIBuilder.class);
   private ComponentContainer container;

   public FwUIBuilder(ComponentContainer container) {
      this.container = container;
   }

   public void add(Component component) {
      if ((component != null) && !(component instanceof Window)) {
         container.addComponent(component);
      } else if (component instanceof Window) {
         Window parentWindow = container.getWindow();
         if ((parentWindow != null) && (parentWindow.getParent() != null)) {
            parentWindow = parentWindow.getParent().getWindow();
         }
         parentWindow.addWindow((Window) component);
      }
   }

   /*
    * BUTTONS
    */
   public Button button() {
      Button c = new Button();
      c.setImmediate(true);
      add(c);
      return c;
   }

   public Button button(String caption) {
      Button c = button();
      c.setCaption(caption);
      return c;
   }

   public Button button(String caption, Button.ClickListener listener) {
      Button c = button(caption);
      c.addListener(listener);
      return c;
   }
}
