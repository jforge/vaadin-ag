package agrepository.framework.widgetset.uppercasetextfield.client.ui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.vaadin.terminal.gwt.client.ui.VTextField;

public class VUpperCaseTextField extends VTextField {
   private KeyUpHandler keyUpHandler = new KeyUpHandler() {
      @Override
      public void onKeyUp(KeyUpEvent event) {
         int nativeKeyCode = event.getNativeKeyCode();
         int curPos = getCursorPos();
         if (!event.isAnyModifierKeyDown() && !event.isMetaKeyDown() && !event.isShiftKeyDown() && !event.isDownArrow()
                  && !event.isLeftArrow() && !event.isRightArrow() && !event.isUpArrow() && (nativeKeyCode != KeyCodes.KEY_END)
                  && (nativeKeyCode != KeyCodes.KEY_HOME) && (nativeKeyCode != KeyCodes.KEY_DOWN)
                  && (nativeKeyCode != KeyCodes.KEY_UP) && (nativeKeyCode != KeyCodes.KEY_LEFT)
                  && (nativeKeyCode != KeyCodes.KEY_RIGHT) && (nativeKeyCode != KeyCodes.KEY_BACKSPACE)
                  && (nativeKeyCode != KeyCodes.KEY_DELETE)) {
            if (getValue() != null) {
               setValue(getValue().toUpperCase());
               setCursorPos(curPos);
            }
         }
      }
   };

   public VUpperCaseTextField() {
      super();
      addKeyUpHandler(keyUpHandler);
   }
}
