package agrepository.framework.validators;

import java.text.MessageFormat;

import agrepository.framework.FwApplication;

import com.vaadin.data.validator.AbstractValidator;

@SuppressWarnings("serial")
public class FwMinMaxValidator extends AbstractValidator {
   private Object minValue;
   private Object maxValue;
   private boolean validMinValue;
   private boolean validMaxValue;

   public FwMinMaxValidator(String errorMessage) {
      super(errorMessage);
   }

   public FwMinMaxValidator() {
      super(null);
   }

   @Override
   public void validate(Object value) throws InvalidValueException {
      if (!isValid(value)) {
         String message = null;
         if (!validMinValue && !validMaxValue) {
            message = MessageFormat
                     .format(FwApplication.current().getTranslator().get("error.invalidMinMax"), minValue, maxValue);
         } else if (!validMinValue) {
            message = MessageFormat.format(FwApplication.current().getTranslator().get("error.invalidMin"), minValue);
         } else if (!validMaxValue) {
            message = MessageFormat.format(FwApplication.current().getTranslator().get("error.invalidMax"), maxValue);
         }
         throw new InvalidValueException(message);
      }
   }

   @Override
   public boolean isValid(Object value) {
      return (value == null) || (checkMinValue(value) && checkMaxValue(value));
   }

   public boolean checkMinValue(Object value) {
      validMinValue = (minValue == null) || ((minValue != null) && ((Double) minValue <= (Double) value));
      return validMinValue;
   }

   public boolean checkMaxValue(Object value) {
      validMaxValue = (maxValue == null) || ((maxValue != null) && ((Double) maxValue <= (Double) value));
      return validMaxValue;
   }

   public Object getMinValue() {
      return minValue;
   }

   public void setMinValue(Object minValue) {
      this.minValue = minValue;
   }

   public Object getMaxValue() {
      return maxValue;
   }

   public void setMaxValue(Object maxValue) {
      this.maxValue = maxValue;
   }
}
