package agrepository.framework.extensions.fields;

import agrepository.framework.extensions.FwExtApplication;

import com.vaadin.data.validator.IntegerValidator;

public class FwIntegerField extends FwExtTextField {
   private static final long serialVersionUID = 4552733966853236825L;

   public FwIntegerField() {
      super();
      addValidator(new IntegerValidator(FwExtApplication.current().getTranslator().get("error.wrongIntegerFormat")));
   }

   @Override
   public Object getValue() {
      Integer value = (Integer) super.getValue();
      return (value == null) ? null : value;
   }
}
