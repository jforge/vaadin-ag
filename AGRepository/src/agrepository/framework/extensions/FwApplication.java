package agrepository.framework.extensions;

import java.io.File;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.jdbc.OracleConnection;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.utilities.FwParameters;
import agrepository.framework.utilities.FwThreadLocalPattern;
import agrepository.framework.utilities.FwTranslator;
import agrepository.framework.utilities.FwUIBuilder;
import agrepository.framework.utilities.FwUserData;
import agrepository.framework.utilities.FwUserMessages;
import agrepository.framework.utilities.FwUtils;

import com.vaadin.Application;
import com.vaadin.terminal.Terminal;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;

public abstract class FwApplication extends Application implements HttpServletRequestListener {
   private static final Log LOG = LogFactory.getLog(FwApplication.class);
   private static final long serialVersionUID = 5473629791987132392L;
   private FwThreadLocalPattern threadLocal;
   private FwParameters parameters;
   private FwTranslator translator;
   private FwUIBuilder uiBuilder;
   private FwUserMessages userMessages;
   private File temporaryDirectory;
   private HttpServletRequest request;
   private HttpServletResponse response;
   private String usernameAutologin;
   private String passwordAutologin;

   @Override
   public void init() {
      threadLocal = new FwThreadLocalPattern(this);
      threadLocal.transactionStart(this, null);
      parameters = new FwParameters();
      setTheme(parameters.getString("application.theme"));
      setLocale(new Locale(parameters.getString("application.locale")));
      translator = new FwTranslator();
      FwWindow window = new FwWindow();
      window.setCaption(translator.get("application.title"));
      setMainWindow(window);
      userMessages = new FwUserMessages();
      uiBuilder = new FwUIBuilder(window);
      showLoginDialog();
      createUI();
   }

   @Override
   public void onRequestStart(HttpServletRequest request, HttpServletResponse response) {
      this.request = request;
      this.response = response;
   }

   @Override
   public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {
      this.request = null;
      this.response = null;
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
         temporaryDirectory = new File(getBaseDirectory(), parameters.getString("temporaryDirectory"));
      }
      return temporaryDirectory;
   }

   @Override
   public String getVersion() {
      return String.format("%s [%s]", parameters.getString("application.name"), parameters.getString("application.version"));
   }

   public FwParameters getParameters() {
      return parameters;
   }

   public FwTranslator getTranslator() {
      return translator;
   }

   public FwUIBuilder getUIBuilder() {
      return uiBuilder;
   }

   public FwUserMessages getUserMessages() {
      return userMessages;
   }

   public OracleConnection getConnection() {
      OracleConnection connection = null;
      if (getUserData() != null) {
         connection = getUserData().getConnection();
      }
      return connection;
   }

   public FwUserData getUserData() {
      return (FwUserData) getUser();
   }

   @Override
   public void close() {
      if (getUserData() != null) {
         OracleConnection connection = getUserData().getConnection();
         if (connection != null) {
            try {
               connection.rollback();
               connection.close();
            } catch (SQLException exception) {
               LOG.error(exception);
            }
         }
      }
      super.close();
      if (threadLocal != null) {
         threadLocal.transactionEnd(this, null);
         threadLocal = null;
      }
   }

   public static FwApplication current() {
      return FwThreadLocalPattern.current();
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

   public HttpServletRequest getHttpServletRequest() {
      return request;
   }

   public void setHttpServletRequest(HttpServletRequest request) {
      this.request = request;
   }

   public HttpServletResponse getHttpServletResponse() {
      return response;
   }

   public void setHttpServletResponse(HttpServletResponse response) {
      this.response = response;
   }

   public void showLoginDialog() {
      LOG.debug(RandomStringUtils.randomAlphabetic(15));
      LOG.debug(RandomStringUtils.randomAlphabetic(15));
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
         for (Cookie cookie : cookies) {
            if (cookie.getName().equals(parameters.getString("cookie.usernameKey"))) {
               usernameAutologin = cookie.getValue();
            } else if (cookie.getName().equals(parameters.getString("cookie.passwordKey"))) {
               passwordAutologin = cookie.getValue();
            }
         }
      }
      if (!FwUtils.isEmpty(usernameAutologin) && !FwUtils.isEmpty(passwordAutologin)) {
         // TODO: kontrola korisnika u bazi
      }
   }

   public void showLogoutDialog() {
   }

   public abstract void createUI();

   /*
    * BUTTONS
    */
   public Button button() {
      return uiBuilder.button();
   }

   public Button button(String caption) {
      return uiBuilder.button(caption);
   }

   public Button button(String caption, ClickListener listener) {
      return uiBuilder.button(caption, listener);
   }
}
