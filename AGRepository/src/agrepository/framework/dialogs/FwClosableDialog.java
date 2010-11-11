package agrepository.framework.dialogs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.FwWindow;

import com.vaadin.event.ShortcutAction.KeyCode;

@SuppressWarnings("serial")
public class FwClosableDialog extends FwWindow {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwClosableDialog.class);

   public FwClosableDialog() {
      super();
      setClosable(true);
      setCloseShortcut(KeyCode.ENTER, null);
   }
}
