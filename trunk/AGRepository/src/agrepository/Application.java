package agrepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.FwApplication;
import agrepository.framework.FwEvent;
import agrepository.framework.FwListener;

import com.vaadin.ui.Button;

@SuppressWarnings("serial")
public class Application extends FwApplication {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(Application.class);

   @Override
   public void createUI() {
      Button button = new Button(getFullName());
      button.addListener(new FwListener() {
         private static final long serialVersionUID = -574714189942945415L;

         @Override
         public void execute(FwEvent event) throws Exception {
            close();
         }
      });
      getMainWindow().addComponent(button);
   }
}
