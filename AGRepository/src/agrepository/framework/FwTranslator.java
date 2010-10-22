package agrepository.framework;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class FwTranslator implements Serializable {
   private static final Logger LOG = Logger.getLogger(FwTranslator.class);
   private static final long serialVersionUID = 6987591647465154990L;
   private ResourceBundle resourceBundle = null;

   public FwTranslator() {
      resourceBundle = ResourceBundle.getBundle(FwExtApplication.current().getParameters().get("application.resources"));
   }

   public void refresh() {
   }

   public String get(String key) {
      String value = null;
      try {
         value = resourceBundle.getString(key);
      } catch (MissingResourceException e) {
         value = "!" + key + "!";
      }
      return value;
   }

   public String get(String key, Object... arguments) {
      return FwUtils.format(resourceBundle.getString(key), arguments);
   }
}
