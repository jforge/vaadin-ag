package agrepository.framework.extensions.validators;

import com.vaadin.data.validator.AbstractStringValidator;

@SuppressWarnings("serial")
public class FwFloatValidator extends AbstractStringValidator {
   public FwFloatValidator(String errorMessage) {
      super(errorMessage);
   }

   @Override
   protected boolean isValidString(String value) {
      try {
         Float.parseFloat(value);
         return true;
      } catch (Exception e) {
         return false;
      }
   }
}
