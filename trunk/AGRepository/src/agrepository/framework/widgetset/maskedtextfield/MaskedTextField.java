package agrepository.framework.widgetset.maskedtextfield;

import agrepository.framework.widgetset.maskedtextfield.client.ui.VMaskedTextField;

import com.vaadin.data.Property;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.TextField;

@ClientWidget(VMaskedTextField.class)
public class MaskedTextField extends TextField {
   private static final long serialVersionUID = 3351884161296775154L;
   private String mask;

   public MaskedTextField() {
   }

   public MaskedTextField(String string) {
      setCaption(string);
   }

   public MaskedTextField(String string, String mask) {
      setCaption(string);
      setMask(mask);
   }

   public MaskedTextField(Property dataSource) {
      super(dataSource);
   }

   public MaskedTextField(String caption, Property dataSource) {
      super(caption, dataSource);
   }

   public void setMask(String mask) {
      this.mask = mask;
      requestRepaint();
   }

   @Override
   public void paintContent(PaintTarget target) throws PaintException {
      super.paintContent(target);
      if (mask != null) {
         target.addAttribute("mask", mask);
      }
   }
}
