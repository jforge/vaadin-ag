package agrepository.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FwParameters extends Properties {
   private static final Logger LOG = Logger.getLogger(FwParameters.class);
   private static final long serialVersionUID = -6931501892578360133L;
   private ExtApplication application;
   private String parametersFile;

   public FwParameters() {
      parametersFile = ExtApplication.current().getServletContext().getInitParameter("parametersFile");
      refresh();
   }

   public void refresh() {
      clear();
      try {
         load(new FileInputStream(new File(ExtApplication.current().getBaseDirectory(), parametersFile)));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public String get(String key) {
      String value = getProperty(key);
      if (FwUtils.isEmpty(value)) {
         throw new MissingResourceException(
                  FwUtils.format("Parameter %s not exist in configuration file %s", key, parametersFile), null, null);
      }
      return value;
   }

   public int getInt(String key) {
      String value = get(key);
      return Integer.valueOf(value);
   }
}
