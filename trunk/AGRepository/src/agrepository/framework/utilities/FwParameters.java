package agrepository.framework.utilities;

import java.io.File;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.extensions.FwExtApplication;

public class FwParameters extends CompositeConfiguration {
   private static final Log LOG = LogFactory.getLog(FwParameters.class);
   private static final long serialVersionUID = -6931501892578360133L;
   private String parametersFile;

   public FwParameters() {
      parametersFile = FwExtApplication.current().getServletContext().getInitParameter("parametersFile");
      refresh();
   }

   public void refresh() {
      clear();
      addConfiguration(new SystemConfiguration());
      try {
         addConfiguration(new PropertiesConfiguration(new File(FwExtApplication.current().getBaseDirectory(), parametersFile)));
      } catch (ConfigurationException exception) {
         LOG.error(null, exception);
      }
   }
}
