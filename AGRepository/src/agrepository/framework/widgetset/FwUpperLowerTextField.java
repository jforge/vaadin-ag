package agrepository.framework.widgetset;

import agrepository.framework.widgetset.client.ui.VFwUpperLowerTextField;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.TextField;

@ClientWidget(VFwUpperLowerTextField.class)
public class FwUpperLowerTextField extends TextField {
   private static final long serialVersionUID = 1756411008735933550L;
   private boolean onlyLowerLetters;

   @Override
   public void paintContent(PaintTarget target) throws PaintException {
      super.paintContent(target);
      target.addAttribute("onlyLowerLetters", onlyLowerLetters);
   }

   public boolean isOnlyLowerLetters() {
      return onlyLowerLetters;
   }

   public void setOnlyLowerLetters(boolean onlyLowerLetters) {
      this.onlyLowerLetters = onlyLowerLetters;
      if (getValue() != null) {
         String value = getValue().toString();
         if (this.onlyLowerLetters) {
            setValue(value.toLowerCase());
         } else {
            setValue(value.toUpperCase());
         }
      }
      requestRepaint();
   }
}
