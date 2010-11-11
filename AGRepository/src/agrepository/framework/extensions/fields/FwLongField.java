package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwNumericField;

@SuppressWarnings("serial")
public class FwLongField extends FwNumericField {
   public FwLongField() {
      super();
      setOnlyIntegerValues(true);
   }
}
