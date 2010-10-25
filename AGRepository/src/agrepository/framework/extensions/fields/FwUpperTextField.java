package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwUpperLowerTextField;

public class FwUpperTextField extends FwUpperLowerTextField {
   private static final long serialVersionUID = -4268448074082280882L;

   public FwUpperTextField() {
      setOnlyLowerLetters(false);
   }
}
