package agrepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.extensions.FwApplication;
import agrepository.framework.extensions.FwEvent;
import agrepository.framework.extensions.FwListener;

import com.vaadin.ui.Button;

public class Application extends FwApplication {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(Application.class);
   private static final long serialVersionUID = -6919355554494428313L;

   @Override
   public void createUI() {
      Button button = new Button(getFullName());
      button.addListener(new FwListener() {
         private static final long serialVersionUID = -574714189942945415L;

         @Override
         public void exec(FwEvent event) throws Exception {
            close();
         }
      });
      getMainWindow().addComponent(button);
   }
}
