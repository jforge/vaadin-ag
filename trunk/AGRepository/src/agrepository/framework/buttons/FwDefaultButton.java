package agrepository.framework.buttons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;

@SuppressWarnings("serial")
public class FwDefaultButton extends Button {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwDefaultButton.class);

   public FwDefaultButton(String caption) {
      super(caption);
      setClickShortcut(KeyCode.ENTER, null);
      addStyleName("primary");
   }
}
