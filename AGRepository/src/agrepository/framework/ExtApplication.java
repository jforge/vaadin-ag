package agrepository.framework;

import java.io.File;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import agrepository.framework.utilities.Configuration;
import agrepository.framework.utilities.ThreadLocalPattern;
import agrepository.framework.utilities.Translator;
import agrepository.framework.utilities.UIBuilder;
import agrepository.framework.utilities.UserMessages;

import com.vaadin.Application;
import com.vaadin.terminal.Terminal;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

public abstract class ExtApplication extends Application {
   private static final Logger LOG = Logger.getLogger(ExtApplication.class);
   private static final long serialVersionUID = 5473629791987132392L;
   private ThreadLocalPattern threadLocal;
   private Configuration configuration;
   private Translator translator;
   private UIBuilder uiBuilder;
   private UserMessages userMessages;
   private File temporaryDirectory;

   @Override
   public void init() {
      threadLocal = new ThreadLocalPattern(this);
      threadLocal.transactionStart(this, null);
      configuration = new Configuration(this);
      setTheme(configuration.get("application.theme"));
      setLocale(new Locale(configuration.get("application.locale")));
      translator = new Translator(this);
      ExtWindow window = new ExtWindow();
      window.setCaption(translator.get("application.title"));
      setMainWindow(window);
      userMessages = new UserMessages(this);
      createUI();
   }

   public WebApplicationContext getWebApplicationContext() {
      return (WebApplicationContext) getContext();
   }

   public HttpSession getHttpSession() {
      return getWebApplicationContext().getHttpSession();
   }

   public ServletContext getServletContext() {
      return getHttpSession().getServletContext();
   }

   public File getBaseDirectory() {
      return getContext().getBaseDirectory();
   }

   public File getTempDirectory() {
      if (temporaryDirectory == null) {
         temporaryDirectory = new File(getBaseDirectory(), getServletContext().getInitParameter("temporaryDirectory"));
      }
      return temporaryDirectory;
   }

   @Override
   public String getVersion() {
      return String.format("%s [%s]", configuration.get("application.name"), configuration.get("application.version"));
   }

   public Configuration getConfiguration() {
      return configuration;
   }

   public Translator getTranslator() {
      return translator;
   }

   public UIBuilder getUIBuilder() {
      return uiBuilder;
   }

   public UserMessages getUserMessages() {
      return userMessages;
   }

   @Override
   public void close() {
      super.close();
      if (threadLocal != null) {
         threadLocal.transactionEnd(this, null);
         threadLocal = null;
      }
   }

   public static ExtApplication current() {
      return ThreadLocalPattern.current();
   }

   @Override
   public Terminal.ErrorListener getErrorHandler() {
      return new Terminal.ErrorListener() {
         private static final long serialVersionUID = 1L;

         @Override
         public void terminalError(Terminal.ErrorEvent event) {
            userMessages.error("Unhandled Exception", event.getThrowable());
         }
      };
   }

   @Override
   public void terminalError(Terminal.ErrorEvent event) {
      super.terminalError(event);
      userMessages.error("Unhandled Exception", event.getThrowable());
   }

   public void refresh() {
      configuration.refresh();
      translator.refresh();
   }

   public abstract void createUI();
}
