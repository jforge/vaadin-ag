package agrepository.framework.widgetset;

import agrepository.framework.widgetset.client.ui.VFwLowerTextField;

import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.TextField;

@ClientWidget(VFwLowerTextField.class)
public class FwLowerTextField extends TextField {
   private static final long serialVersionUID = -1381048346220320867L;

   @Override
   public String getValue() {
      return (String) super.getValue();
   }
}
