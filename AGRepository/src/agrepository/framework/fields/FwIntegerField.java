package agrepository.framework.fields;

import agrepository.framework.widgetset.FwNumericField;

@SuppressWarnings("serial")
public class FwIntegerField extends FwNumericField {
   public FwIntegerField() {
      super();
      setOnlyIntegerValues(true);
   }
}
