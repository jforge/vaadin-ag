package agrepository.framework.extensions.fields;

import agrepository.framework.extensions.FwApplication;
import agrepository.framework.extensions.validators.FwFloatValidator;
import agrepository.framework.widgetset.FwNumericField;

public class FwFloatField extends FwNumericField {
   private static final long serialVersionUID = 770896047104421529L;

   public FwFloatField() {
      super();
      addValidator(new FwFloatValidator(FwApplication.current().getTranslator().get("error.wrongFloatFormat")));
   }

   @Override
   public Float getValue() {
      Float value = (Float) super.getValue();
      return (value == null) ? null : value;
   }
}
