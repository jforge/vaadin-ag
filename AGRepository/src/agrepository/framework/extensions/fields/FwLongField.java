package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwNumericField;

public class FwLongField extends FwNumericField {
   private static final long serialVersionUID = -2686799683530515644L;

   public FwLongField() {
      super();
      setOnlyIntegerValues(true);
   }
}
