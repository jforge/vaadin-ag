package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwNumericField;

@SuppressWarnings("serial")
public class FwDoubleField extends FwNumericField {
   public FwDoubleField() {
      super();
      setOnlyIntegerValues(false);
   }
}
