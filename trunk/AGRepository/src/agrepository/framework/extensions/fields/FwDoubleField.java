package agrepository.framework.extensions.fields;

import agrepository.framework.extensions.FwApplication;
import agrepository.framework.widgetset.FwNumericField;

import com.vaadin.data.validator.DoubleValidator;

public class FwDoubleField extends FwNumericField {
   private static final long serialVersionUID = -1362941912435929080L;

   public FwDoubleField() {
      super();
      addValidator(new DoubleValidator(FwApplication.current().getTranslator().get("error.wrongDoubleFormat")));
   }

   @Override
   public Double getValue() {
      Double value = (Double) super.getValue();
      return (value == null) ? null : value;
   }
}
