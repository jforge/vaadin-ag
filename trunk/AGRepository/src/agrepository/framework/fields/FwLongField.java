package agrepository.framework.fields;

import agrepository.framework.widgetset.FwNumericField;

@SuppressWarnings("serial")
public class FwLongField extends FwNumericField {
   public FwLongField() {
      super();
      setOnlyIntegerValues(true);
   }
}
