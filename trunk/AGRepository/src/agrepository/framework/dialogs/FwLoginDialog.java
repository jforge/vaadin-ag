package agrepository.framework.dialogs;

import javax.servlet.http.Cookie;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.FwApplication;
import agrepository.framework.FwEvent;
import agrepository.framework.FwListener;
import agrepository.framework.FwWindow;
import agrepository.framework.buttons.FwDefaultButton;
import agrepository.framework.utilities.FwParameters;
import agrepository.framework.utilities.FwTranslator;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class FwLoginDialog extends FwWindow {
   private static final Log LOG = LogFactory.getLog(FwLoginDialog.class);
   private FwApplication application;
   private FwTranslator translator;
   private FwParameters parameters;
   private Form form;
   private TextField tfUsername;
   private TextField tfPassword;

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
      tfUsername = new TextField(translator.get("login.label.username"));
      tfUsername.setNullRepresentation("");
      tfUsername.setRequired(true);
      tfUsername.focus();
      form.addField("username", tfUsername);
      tfPassword = new TextField(translator.get("login.label.password"));
      tfPassword.setRequired(true);
      tfPassword.setSecret(true);
      form.addField("password", tfPassword);
      VerticalLayout footerLayout = new VerticalLayout();
      form.setFooter(footerLayout);
      FwDefaultButton button = new FwDefaultButton(translator.get("login.label.enter"));
      button.addListener(new FwListener() {
         private static final long serialVersionUID = 1159486070944237738L;

         @Override
         public void execute(FwEvent event) throws Exception {
            if (checkUser(DigestUtils.md5Hex(tfUsername.getValue().toString()),
                     DigestUtils.md5Hex(tfPassword.getValue().toString()))) {
               hide();
            } else {
               // TODO: prikazivanje gresaka
            }
         }
      });
      footerLayout.addComponent(button);
      footerLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);
      addComponent(form);
   }

   public boolean checkUser(String username, String password) {
      LOG.debug("username: " + username);
      LOG.debug("password: " + password);
      // TODO: proveriti u bazi korisnika i lozinku sa MD5 podacima
      // TODO: ako je ok i ako je autologin onda staviti u kolacic oba podatka
      // TODO: ako je ok i ako nije autologin onda pobrisati kolacic
      return false;
   }

   public void show() {
      Cookie[] cookies = application.getHttpServletRequest().getCookies();
      String autologinKey = null;
      String usernameAutologin = null;
      String passwordAutologin = null;
      if (cookies != null) {
         for (Cookie cookie : cookies) {
            if (cookie.getName().equals(parameters.getString("cookie.autologinKey"))) {
               autologinKey = cookie.getValue();
               break;
            }
         }
         if (autologinKey != null) {
            usernameAutologin = autologinKey.substring(1, 32);
            passwordAutologin = autologinKey.substring(32);
         }
      }
      if (checkUser(usernameAutologin, passwordAutologin)) {
         hide();
      } else {
         application.getMainWindow().addWindow(this);
      }
   }

   public void hide() {
      LOG.debug("start");
      if (isVisible()) {
         setVisible(false);
         application.getMainWindow().removeWindow(this);
      }
      application.createUI();
      LOG.debug("end");
   }
}
