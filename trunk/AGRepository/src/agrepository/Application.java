package agrepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.extensions.FwApplication;
import agrepository.framework.extensions.fields.FwFloatField;
import agrepository.framework.extensions.fields.FwIntegerField;
import agrepository.framework.widgetset.FwUpperTextField;

public class Application extends FwApplication {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(Application.class);
   private static final long serialVersionUID = -6919355554494428313L;

   @Override
   public void createUI() {
      FwUpperTextField uFld = new FwUpperTextField();
      uFld.setColumns(25);
      getMainWindow().addComponent(uFld);
      FwIntegerField iFld = new FwIntegerField();
      iFld.setColumns(50);
      getMainWindow().addComponent(iFld);
      FwFloatField fFld = new FwFloatField();
      fFld.setColumns(50);
      getMainWindow().addComponent(fFld);
   }
}
