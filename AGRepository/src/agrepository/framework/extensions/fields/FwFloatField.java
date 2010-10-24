package agrepository.framework.extensions.fields;

import agrepository.framework.extensions.FwExtApplication;
import agrepository.framework.extensions.validators.FwFloatValidator;

public class FwFloatField extends FwExtTextField {
   private static final long serialVersionUID = 770896047104421529L;

   public FwFloatField() {
      super();
      addValidator(new FwFloatValidator(FwExtApplication.current().getTranslator().get("error.wrongFloatFormat")));
   }

   @Override
   public Float getValue() {
      Float value = (Float) super.getValue();
      return (value == null) ? null : value;
   }
}
