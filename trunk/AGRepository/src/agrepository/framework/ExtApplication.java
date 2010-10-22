package agrepository.framework;

import java.io.File;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vaadin.Application;
import com.vaadin.terminal.Terminal;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

public abstract class ExtApplication extends Application {
   private static final Logger LOG = Logger.getLogger(ExtApplication.class);
   private static final long serialVersionUID = 5473629791987132392L;
   private ThreadLocalPattern threadLocal;
   private FwParameters parameters;
   private Translator translator;
   private UIBuilder uiBuilder;
   private UserMessages userMessages;
   private File temporaryDirectory;

   @Override
   public void init() {
      threadLocal = new ThreadLocalPattern(this);
      threadLocal.transactionStart(this, null);
      parameters = new FwParameters();
      setTheme(parameters.get("application.theme"));
      setLocale(new Locale(parameters.get("application.locale")));
      translator = new Translator();
      ExtWindow window = new ExtWindow();
      window.setCaption(translator.get("application.title"));
      setMainWindow(window);
      userMessages = new UserMessages();
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
      return String.format("%s [%s]", parameters.get("application.name"), parameters.get("application.version"));
   }

   public FwParameters getParameters() {
      return parameters;
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
            userMessages.error(translator.get("error.unhandledException"), event.getThrowable());
         }
      };
   }

   @Override
   public void terminalError(Terminal.ErrorEvent event) {
      super.terminalError(event);
      userMessages.error(translator.get("error.unhandledException"), event.getThrowable());
   }

   public void refresh() {
      parameters.refresh();
      translator.refresh();
   }

   public abstract void createUI();
}
