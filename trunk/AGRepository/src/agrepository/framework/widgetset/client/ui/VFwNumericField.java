package agrepository.framework.widgetset.client.ui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.VTextField;

public class VFwNumericField extends VTextField {
   private char separatorChar;
   private boolean onlyIntegerValues;
   private boolean specialKeyDown;
   private boolean hasSeparator;
   private boolean hasPositiveSign;
   private boolean hasNegativeSign;
   private KeyDownHandler keyDownHandler = new KeyDownHandler() {
      @Override
      public void onKeyDown(KeyDownEvent event) {
         int nativeKeyCode = event.getNativeKeyCode();
         specialKeyDown = event.isAnyModifierKeyDown() || event.isAltKeyDown() || event.isControlKeyDown()
                  || event.isShiftKeyDown() || event.isMetaKeyDown() || event.isShiftKeyDown() || event.isDownArrow()
                  || event.isLeftArrow() || event.isRightArrow() || event.isUpArrow() || (nativeKeyCode == KeyCodes.KEY_END)
                  || (nativeKeyCode == KeyCodes.KEY_HOME) || (nativeKeyCode == KeyCodes.KEY_DOWN)
                  || (nativeKeyCode == KeyCodes.KEY_UP) || (nativeKeyCode == KeyCodes.KEY_LEFT)
                  || (nativeKeyCode == KeyCodes.KEY_RIGHT) || (nativeKeyCode == KeyCodes.KEY_BACKSPACE)
                  || (nativeKeyCode == KeyCodes.KEY_DELETE) || (nativeKeyCode == KeyCodes.KEY_PAGEDOWN)
                  || (nativeKeyCode == KeyCodes.KEY_PAGEUP) || (nativeKeyCode == KeyCodes.KEY_ENTER)
                  || (nativeKeyCode == KeyCodes.KEY_ESCAPE) || (nativeKeyCode == KeyCodes.KEY_ALT)
                  || (nativeKeyCode == KeyCodes.KEY_CTRL) || (nativeKeyCode == KeyCodes.KEY_SHIFT)
                  || (nativeKeyCode == KeyCodes.KEY_TAB);
      }
   };
   private KeyPressHandler keyPressHandler = new KeyPressHandler() {
      @Override
      public void onKeyPress(KeyPressEvent event) {
         char charCode = event.getCharCode();
         if ((('0' <= charCode) && (charCode <= '9')) || (charCode == '+') || (charCode == '-') || (charCode == separatorChar)) {
            if (!onlyIntegerValues && hasSeparator && (charCode == separatorChar)) {
               event.preventDefault();
            }
            if ((getCursorPos() != 0) && ((charCode == '+') || (charCode == '-'))) {
               event.preventDefault();
            }
            if ((hasPositiveSign || hasNegativeSign) && ((charCode == '+') || (charCode == '-'))) {
               event.preventDefault();
            }
         } else if (!specialKeyDown) {
            event.preventDefault();
         }
      }
   };
   private KeyUpHandler keyUpHandler = new KeyUpHandler() {
      @Override
      public void onKeyUp(KeyUpEvent event) {
         if (getValue() != null) {
            String sign = getValue().substring(0, 1);
            hasPositiveSign = "+".equals(sign);
            hasNegativeSign = "-".equals(sign);
            hasSeparator = !onlyIntegerValues && getValue().contains(Character.toString(separatorChar));
         } else {
            hasPositiveSign = false;
            hasNegativeSign = false;
            hasSeparator = false;
         }
      }
   };

   @Override
   public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
      super.updateFromUIDL(uidl, client);
      if (uidl.hasAttribute("separatorChar")) {
         if (uidl.getStringAttribute("separatorChar") != null) {
            separatorChar = uidl.getStringAttribute("separatorChar").toCharArray()[0];
         }
      }
      if (uidl.hasAttribute("onlyIntegerValues")) {
         onlyIntegerValues = uidl.getBooleanAttribute("onlyIntegerValues");
      }
   }

   public VFwNumericField() {
      super();
      addKeyDownHandler(keyDownHandler);
      addKeyPressHandler(keyPressHandler);
      addKeyUpHandler(keyUpHandler);
   }
}
