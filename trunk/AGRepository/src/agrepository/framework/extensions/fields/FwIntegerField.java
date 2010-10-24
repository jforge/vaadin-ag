package agrepository.framework.extensions.fields;

import agrepository.framework.extensions.FwApplication;
import agrepository.framework.widgetset.FwNumericField;

import com.vaadin.data.validator.IntegerValidator;

public class FwIntegerField extends FwNumericField {
   private static final long serialVersionUID = 4552733966853236825L;

   public FwIntegerField() {
      super();
      addValidator(new IntegerValidator(FwApplication.current().getTranslator().get("error.wrongIntegerFormat")));
   }

   @Override
   public Object getValue() {
      Integer value = (Integer) super.getValue();
      return (value == null) ? null : value;
   }
}
