package agrepository.framework.dialogs;

import javax.servlet.http.Cookie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.extensions.FwApplication;
import agrepository.framework.extensions.FwEvent;
import agrepository.framework.extensions.FwListener;
import agrepository.framework.extensions.FwWindow;
import agrepository.framework.utilities.FwParameters;
import agrepository.framework.utilities.FwTranslator;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FwLoginDialog extends FwWindow {
   private static final Log LOG = LogFactory.getLog(FwLoginDialog.class);
   private static final long serialVersionUID = 8560087121218645594L;
   private FwApplication application;
   private FwTranslator translator;
   private FwParameters parameters;
   private String usernameAutologin;
   private String passwordAutologin;
   private Form form;
   private TextField username;
   private TextField password;

   @Override
   public void createUI() {
      application = FwApplication.current();
      translator = application.getTranslator();
      parameters = application.getParameters();
      super.createUI();
      getRootLayout().setMargin(true);
      setCaption(application.getFullName());
      setClosable(false);
      setResizable(false);
      center();
      form = new Form();
      form.getLayout().setMargin(true);
      form.setCaption(translator.get("login.title"));
      username = new TextField(translator.get("login.label.username"));
      username.setNullRepresentation("");
      username.setValue(usernameAutologin);
      username.setRequired(true);
      form.addField("username", username);
      password = new TextField(translator.get("login.label.password"));
      password.setRequired(true);
      password.setSecret(true);
      form.addField("password", password);
      VerticalLayout footerLayout = new VerticalLayout();
      form.setFooter(footerLayout);
      Button button = new Button(translator.get("login.label.enter"));
      button.addListener(new FwListener() {
         private static final long serialVersionUID = 1159486070944237738L;

         @Override
         public void exec(FwEvent event) throws Exception {
            try {
               checkUser(username.getValue().toString(), password.getValue().toString());
               hide();
            } catch (Exception exception) {
               LOG.error(null, exception);
            }
         }
      });
      footerLayout.addComponent(button);
      footerLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);
      addComponent(form);
   }

   public void checkUser(String username, String password) throws Exception {
      // TODO: proveriti u bazi korisnika
      throw new Exception();
   }

   public void show() {
      Cookie[] cookies = application.getHttpServletRequest().getCookies();
      if (cookies != null) {
         for (Cookie cookie : cookies) {
            if (cookie.getName().equals(parameters.getString("cookie.usernameKey"))) {
               usernameAutologin = cookie.getValue();
            } else if (cookie.getName().equals(parameters.getString("cookie.passwordKey"))) {
               passwordAutologin = cookie.getValue();
            }
         }
      }
      try {
         checkUser(usernameAutologin, passwordAutologin);
         hide();
      } catch (Exception exception) {
         LOG.error(null, exception);
         application.getMainWindow().addWindow(this);
      }
   }

   public void hide() {
      setVisible(false);
      application.getMainWindow().removeWindow(this);
      application.createUI();
   }
}
