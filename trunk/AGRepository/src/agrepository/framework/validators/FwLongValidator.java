package agrepository.framework.validators;

import com.vaadin.data.validator.AbstractStringValidator;

@SuppressWarnings("serial")
public class FwLongValidator extends AbstractStringValidator {
   public FwLongValidator(String errorMessage) {
      super(errorMessage);
   }

   @Override
   protected boolean isValidString(String value) {
      try {
         Long.parseLong(value);
         return true;
      } catch (Exception e) {
         return false;
      }
   }
}
