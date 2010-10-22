package agrepository.framework.utilities;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import agrepository.framework.ExtApplication;

public class Translator implements Serializable {
   private static final Logger LOG = Logger.getLogger(Translator.class);
   private static final long serialVersionUID = 6987591647465154990L;
   private ResourceBundle resourceBundle = null;

   public Translator(ExtApplication application) {
      resourceBundle = ResourceBundle.getBundle(application.getConfiguration().get("application.resources"));
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
      return Utils.format(resourceBundle.getString(key), arguments);
   }
}
