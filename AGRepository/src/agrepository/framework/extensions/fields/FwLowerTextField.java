package agrepository.framework.extensions.fields;


public class FwLowerTextField extends FwExtTextField {
   private static final long serialVersionUID = -3171900966802376492L;

   @Override
   public String getValue() {
      String value = (String) super.getValue();
      return (value == null) ? null : value.toLowerCase();
   }
}
