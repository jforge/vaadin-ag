package agrepository.framework.dialogs;

import agrepository.framework.FwWindow;

import com.vaadin.event.ShortcutAction.KeyCode;

@SuppressWarnings("serial")
public class FwClosableDialog extends FwWindow {
   public FwClosableDialog() {
      super();
      setClosable(true);
      setCloseShortcut(KeyCode.ENTER, null);
   }
}
