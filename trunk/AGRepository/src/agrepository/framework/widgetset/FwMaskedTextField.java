package agrepository.framework.widgetset;

import agrepository.framework.widgetset.client.ui.VFwMaskedTextField;

import com.vaadin.data.Property;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.TextField;

@ClientWidget(VFwMaskedTextField.class)
public class FwMaskedTextField extends TextField {
   private static final long serialVersionUID = 3351884161296775154L;
   private String mask;

   public FwMaskedTextField() {
   }

   public FwMaskedTextField(String string) {
      setCaption(string);
   }

   public FwMaskedTextField(String string, String mask) {
      setCaption(string);
      setMask(mask);
   }

   public FwMaskedTextField(Property dataSource) {
      super(dataSource);
   }

   public FwMaskedTextField(String caption, Property dataSource) {
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
