package agrepository.framework.widgetset;

import agrepository.framework.widgetset.client.ui.VFwUpperTextField;

import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.TextField;

@ClientWidget(VFwUpperTextField.class)
public class FwUpperTextField extends TextField {
   private static final long serialVersionUID = 1756411008735933550L;

   @Override
   public String getValue() {
      return (String) super.getValue();
   }
}
