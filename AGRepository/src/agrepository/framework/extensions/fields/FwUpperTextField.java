package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwUpperLowerTextField;

@SuppressWarnings("serial")
public class FwUpperTextField extends FwUpperLowerTextField {
   public FwUpperTextField() {
      setOnlyLowerLetters(false);
   }
}
