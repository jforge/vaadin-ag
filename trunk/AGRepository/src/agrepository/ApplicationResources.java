package agrepository;

import java.util.ListResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ApplicationResources extends ListResourceBundle {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(ApplicationResources.class);
   private Object[][] contents = {
            {
                     "application.title", "Application Generator Repository"
            }, {
                     "error.unhandledException", "Unhandled exception"
            }, {
                     "error.missingWindow", "User messages require a window instance"
            },
   };

   @Override
   public Object[][] getContents() {
      return contents;
   }
}
