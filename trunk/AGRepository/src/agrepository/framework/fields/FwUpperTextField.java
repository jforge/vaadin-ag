package agrepository.framework.fields;

import agrepository.framework.widgetset.FwUpperLowerTextField;

@SuppressWarnings("serial")
public class FwUpperTextField extends FwUpperLowerTextField {
   public FwUpperTextField() {
      setOnlyLowerLetters(false);
   }
}
