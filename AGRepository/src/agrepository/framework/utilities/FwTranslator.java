package agrepository.framework.utilities;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.extensions.FwExtApplication;

public class FwTranslator implements Serializable {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwTranslator.class);
   private static final long serialVersionUID = 6987591647465154990L;
   private ResourceBundle resourceBundle = null;

   public FwTranslator() {
      resourceBundle = ResourceBundle.getBundle(FwExtApplication.current().getParameters().getString("application.resources"));
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
