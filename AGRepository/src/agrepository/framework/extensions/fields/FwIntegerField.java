package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwNumericField;

@SuppressWarnings("serial")
public class FwIntegerField extends FwNumericField {
   public FwIntegerField() {
      super();
      setOnlyIntegerValues(true);
   }
}
