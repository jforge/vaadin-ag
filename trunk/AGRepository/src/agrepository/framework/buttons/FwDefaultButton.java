package agrepository.framework.buttons;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;

@SuppressWarnings("serial")
public class FwDefaultButton extends Button {
   public FwDefaultButton(String caption) {
      super(caption);
      setClickShortcut(KeyCode.ENTER, null);
      addStyleName("primary");
   }
}
