package agrepository.framework.utilities;

import java.io.File;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.extensions.FwApplication;

public class FwParameters extends CompositeConfiguration {
   private static final Log LOG = LogFactory.getLog(FwParameters.class);
   private String parametersFile;

   public FwParameters() {
      parametersFile = FwApplication.current().getServletContext().getInitParameter("parametersFile");
      refresh();
   }

   public void refresh() {
      clear();
      addConfiguration(new SystemConfiguration());
      try {
         addConfiguration(new PropertiesConfiguration(new File(FwApplication.current().getBaseDirectory(), parametersFile)));
      } catch (ConfigurationException exception) {
         LOG.error(null, exception);
      }
   }
}
