package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwNumericField;

@SuppressWarnings("serial")
public class FwFloatField extends FwNumericField {
   public FwFloatField() {
      super();
      setOnlyIntegerValues(false);
   }
}
