package agrepository.framework.utilities;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.FwApplication;

public class FwTranslator {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwTranslator.class);
   private ResourceBundle resourceBundle = null;

   public FwTranslator() {
      resourceBundle = ResourceBundle.getBundle(FwApplication.current().getParameters().getString("application.resources"));
   }

   public void refresh() {
      ResourceBundle.clearCache();
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
