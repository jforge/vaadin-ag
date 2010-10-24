package agrepository.framework.extensions.fields;

import agrepository.framework.extensions.FwApplication;
import agrepository.framework.extensions.validators.FwFloatValidator;
import agrepository.framework.widgetset.FwNumericField;

public class FwFloatField extends FwNumericField {
   private static final long serialVersionUID = 770896047104421529L;

   public FwFloatField() {
      super();
      setOnlyIntegers(false);
      addValidator(new FwFloatValidator(FwApplication.current().getTranslator().get("error.wrongFloatFormat")));
   }
}
