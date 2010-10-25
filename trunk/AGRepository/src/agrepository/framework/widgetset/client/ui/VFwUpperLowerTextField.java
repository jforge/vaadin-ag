package agrepository.framework.widgetset.client.ui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.VTextField;

public class VFwUpperLowerTextField extends VTextField {
   private boolean onlyLowerLetters;
   private KeyUpHandler keyUpHandler = new KeyUpHandler() {
      @Override
      public void onKeyUp(KeyUpEvent event) {
         int nativeKeyCode = event.getNativeKeyCode();
         int cursorPos = getCursorPos();
         if (!event.isAnyModifierKeyDown() && !event.isAltKeyDown() && !event.isControlKeyDown() && !event.isShiftKeyDown()
                  && !event.isMetaKeyDown() && !event.isShiftKeyDown() && !event.isDownArrow() && !event.isLeftArrow()
                  && !event.isRightArrow() && !event.isUpArrow() && (nativeKeyCode != KeyCodes.KEY_END)
                  && (nativeKeyCode != KeyCodes.KEY_HOME) && (nativeKeyCode != KeyCodes.KEY_DOWN)
                  && (nativeKeyCode != KeyCodes.KEY_UP) && (nativeKeyCode != KeyCodes.KEY_LEFT)
                  && (nativeKeyCode != KeyCodes.KEY_RIGHT) && (nativeKeyCode != KeyCodes.KEY_BACKSPACE)
                  && (nativeKeyCode != KeyCodes.KEY_DELETE) && (nativeKeyCode != KeyCodes.KEY_PAGEDOWN)
                  && (nativeKeyCode != KeyCodes.KEY_PAGEUP) && (nativeKeyCode != KeyCodes.KEY_ENTER)
                  && (nativeKeyCode != KeyCodes.KEY_ESCAPE) && (nativeKeyCode != KeyCodes.KEY_ALT)
                  && (nativeKeyCode != KeyCodes.KEY_CTRL) && (nativeKeyCode != KeyCodes.KEY_SHIFT)
                  && (nativeKeyCode != KeyCodes.KEY_TAB)) {
            if (getValue() != null) {
               if (onlyLowerLetters) {
                  setValue(getValue().toLowerCase());
               } else {
                  setValue(getValue().toUpperCase());
               }
               setCursorPos(cursorPos);
            }
         }
      }
   };

   @Override
   public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
      super.updateFromUIDL(uidl, client);
      if (uidl.hasAttribute("onlyLowerLetters")) {
         onlyLowerLetters = uidl.getBooleanAttribute("onlyLowerLetters");
      }
   }

   public VFwUpperLowerTextField() {
      super();
      addKeyUpHandler(keyUpHandler);
   }
}
