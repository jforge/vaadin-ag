package agrepository.framework.extensions.fields;

import agrepository.framework.extensions.FwExtApplication;

import com.vaadin.data.validator.DoubleValidator;

public class FwDoubleField extends FwExtTextField {
   private static final long serialVersionUID = -1362941912435929080L;

   public FwDoubleField() {
      super();
      /*
       * [+-]?[0-9]+(\.$[0-9]+)?([eE][+-]?[0-9]+)?
       */
      addValidator(new DoubleValidator(FwExtApplication.current().getTranslator().get("error.wrongDoubleFormat")));
   }

   @Override
   public Double getValue() {
      Double value = (Double) super.getValue();
      return (value == null) ? null : value;
   }
}
