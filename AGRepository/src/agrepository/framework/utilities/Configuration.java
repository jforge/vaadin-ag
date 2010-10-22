package agrepository.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;

import org.apache.log4j.Logger;

import agrepository.framework.ExtApplication;

public class Configuration extends Properties {
   private static final Logger LOG = Logger.getLogger(Configuration.class);
   private static final long serialVersionUID = -6931501892578360133L;
   private ExtApplication application;
   private String configurationFile;

   public Configuration(ExtApplication application) {
      this.application = application;
      configurationFile = application.getServletContext().getInitParameter("configurationFile");
      refresh();
   }

   public void refresh() {
      clear();
      try {
         load(new FileInputStream(new File(application.getBaseDirectory(), configurationFile)));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public String get(String key) {
      String value = getProperty(key);
      if (Utils.isEmpty(value)) {
         throw new MissingResourceException(Utils.format("Parameter %s not exist in configuration file %s", key,
                  configurationFile), null, null);
      }
      return value;
   }
}
