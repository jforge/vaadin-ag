package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwUpperLowerTextField;

public class FwLowerTextField extends FwUpperLowerTextField {
   private static final long serialVersionUID = -3171900966802376492L;

   public FwLowerTextField() {
      setOnlyLowerLetters(true);
   }
}
