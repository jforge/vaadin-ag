package agrepository.framework.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.extensions.FwExtApplication;

import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

public class FwUserMessages implements Serializable {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwUserMessages.class);
   private static final long serialVersionUID = -2353972624960242281L;
   private Window window;
   private FwExtApplication application;

   public FwUserMessages() {
      application = FwExtApplication.current();
      window = application.getMainWindow();
      if (window == null) {
         throw new IllegalStateException(application.getTranslator().get("error.missingWindow"));
      }
   }

   public void error(String message) {
      error(message, null, null);
   }

   public void error(String message, String description) {
      error(message, description, null);
   }

   public void error(String message, String description, Throwable throwable) {
      if (throwable != null) {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PrintStream ps = new PrintStream(baos, false);
         throwable.printStackTrace(ps);
         ps.flush();
         try {
            baos.flush();
         } catch (IOException ignored) {
         }
         String value = baos.toString();
         throwable.printStackTrace();
         if (description == null) {
            description = value;
         } else {
            description += ":\n" + value;
         }
      }
      window.showNotification(FwUtils.escapeXML(message), formatDescription(description), Notification.TYPE_ERROR_MESSAGE);
   }

   public void error(String message, Throwable throwable) {
      error(message, null, throwable);
   }

   public void error(Throwable throwable) {
      error(application.getTranslator().get("error.unhandledException"), null, throwable);
   }

   public void warning(String message) {
      warning(message, null);
   }

   public void warning(String message, String description) {
      window.showNotification(message, formatDescription(description), Notification.TYPE_WARNING_MESSAGE);
   }

   public void trayNotification(String message) {
      trayNotification(message, null);
   }

   public void trayNotification(String message, String description) {
      window.showNotification(message, formatDescription(description), Notification.TYPE_TRAY_NOTIFICATION);
   }

   public void notification(String message) {
      notification(message, null);
   }

   public void notification(String message, String description) {
      window.showNotification(message, formatDescription(description));
   }

   public void alert(String message) {
      alert(message, null);
   }

   public void alert(String message, String description) {
      window.showNotification(message, formatDescription(description), Notification.DELAY_FOREVER);
   }

   private String formatDescription(String description) {
      if (description != null) {
         description = FwUtils.escapeXML(description);
         description = description.replaceAll("\n", "<br/>");
         if (description.length() > 80) {
            String descriptionTemp = description;
            description = "";
            while (descriptionTemp.length() > 0) {
               int last = Math.min(80, descriptionTemp.length());
               description += descriptionTemp.substring(0, last);
               int lastNL = description.lastIndexOf("<br");
               int lastWB = description.lastIndexOf(' ');
               if ((lastWB - lastNL > 10) && (lastWB < description.length() - 1)) {
                  description = description.substring(0, lastWB) + "<br/>" + description.substring(lastWB);
               }
               descriptionTemp = last == descriptionTemp.length() ? "" : descriptionTemp.substring(last);
            }
         }
         int position = description.indexOf("<br");
         int lineCount = 1;
         while ((lineCount < application.getParameters().getInt("default.descriptionLines")) && (position > 0)
                  && (position < description.length())) {
            position = description.indexOf("<br", position + 3);
            lineCount++;
         }
         if ((position > 0) && (lineCount >= application.getParameters().getInt("default.descriptionLines"))) {
            description = description.substring(0, position) + "<br/>(...)";
         }
      }
      return description;
   }
}
