package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwNumericField;

public class FwFloatField extends FwNumericField {
   private static final long serialVersionUID = 770896047104421529L;

   public FwFloatField() {
      super();
      setOnlyIntegerValues(false);
   }
}
