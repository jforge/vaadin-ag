package agrepository.framework.extensions.fields;

public class FwUpperTextField extends FwExtTextField {
   private static final long serialVersionUID = -4268448074082280882L;

   @Override
   public String getValue() {
      String value = (String) super.getValue();
      return (value == null) ? null : value.toUpperCase();
   }
}
