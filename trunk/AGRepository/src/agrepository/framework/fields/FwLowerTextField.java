package agrepository.framework.fields;

import agrepository.framework.widgetset.FwUpperLowerTextField;

@SuppressWarnings("serial")
public class FwLowerTextField extends FwUpperLowerTextField {
   public FwLowerTextField() {
      setOnlyLowerLetters(true);
   }
}
