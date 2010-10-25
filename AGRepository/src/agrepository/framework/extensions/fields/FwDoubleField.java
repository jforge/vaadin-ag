package agrepository.framework.extensions.fields;

import agrepository.framework.widgetset.FwNumericField;

public class FwDoubleField extends FwNumericField {
   private static final long serialVersionUID = -1362941912435929080L;

   public FwDoubleField() {
      super();
      setOnlyIntegerValues(false);
   }
}
