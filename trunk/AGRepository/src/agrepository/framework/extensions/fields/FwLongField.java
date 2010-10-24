package agrepository.framework.extensions.fields;

import agrepository.framework.extensions.FwExtApplication;
import agrepository.framework.extensions.validators.FwLongValidator;

public class FwLongField extends FwExtTextField {
   private static final long serialVersionUID = -2686799683530515644L;

   public FwLongField() {
      super();
      addValidator(new FwLongValidator(FwExtApplication.current().getTranslator().get("error.wrongLongFormat")));
   }

   @Override
   public Long getValue() {
      Long value = (Long) super.getValue();
      return (value == null) ? null : value;
   }
}
