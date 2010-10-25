package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwNumericField;

public class FwIntegerField extends FwNumericField {
   private static final long serialVersionUID = 4552733966853236825L;

   public FwIntegerField() {
      super();
      setOnlyIntegerValues(true);
   }
}
