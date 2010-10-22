package agrepository;

import java.util.ListResourceBundle;

import org.apache.log4j.Logger;

public class Resources extends ListResourceBundle {
   private static final Logger LOG = Logger.getLogger(Resources.class);

   @Override
   public Object[][] getContents() {
      return contents;
   }

   static final Object[][] contents = {
      {
               "application.title", "Application Generator Repository"
      }
   };
}
