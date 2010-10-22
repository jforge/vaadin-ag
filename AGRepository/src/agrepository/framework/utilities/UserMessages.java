package agrepository.framework.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;

import org.apache.log4j.Logger;

import agrepository.framework.ExtApplication;

import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

public class UserMessages implements Serializable {
   private static final Logger LOG = Logger.getLogger(UserMessages.class);
   private static final long serialVersionUID = -2353972624960242281L;
   private static final int MAX_DESCRIPTION_LINES = 20;
   private Window window;

   public UserMessages(ExtApplication application) {
      window = application.getMainWindow();
      if (window == null) {
         throw new IllegalStateException("User messages require a window instance");
      }
   }

   public void error(String message) {
      error(message, null, null);
   }

   public void error(String message, String description) {
      error(message, description, null);
   }

   public void error(String message, String description, Throwable t) {
      if (t != null) {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PrintStream ps = new PrintStream(baos, false);
         t.printStackTrace(ps);
         ps.flush();
         try {
            baos.flush();
         } catch (IOException ignored) {
         }
         String s = baos.toString();
         t.printStackTrace();
         if (description == null) {
            description = s;
         } else {
            description += ":\n" + s;
         }
      }
      window.showNotification(Utils.escapeXML(message), formatDescription(description), Notification.TYPE_ERROR_MESSAGE);
   }

   public void error(String message, Throwable t) {
      error(message, null, t);
   }

   public void error(Throwable e) {
      error("Unhandled Exception", null, e);
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
         description = Utils.escapeXML(description);
         description = description.replaceAll("\n", "<br/>");
         if (description.length() > 80) {
            String orig = description;
            description = "";
            while (orig.length() > 0) {
               int last = Math.min(80, orig.length());
               description += orig.substring(0, last);
               int lastnl = description.lastIndexOf("<br");
               int lastwb = description.lastIndexOf(' ');
               if ((lastwb - lastnl > 10) && (lastwb < description.length() - 1)) {
                  description = description.substring(0, lastwb) + "<br/>" + description.substring(lastwb);
               }
               orig = last == orig.length() ? "" : orig.substring(last);
            }
         }
         int pos = description.indexOf("<br");
         int lineCount = 1;
         while ((lineCount < MAX_DESCRIPTION_LINES) && (pos > 0) && (pos < description.length())) {
            pos = description.indexOf("<br", pos + 3);
            lineCount++;
         }
         if ((pos > 0) && (lineCount >= MAX_DESCRIPTION_LINES)) {
            description = description.substring(0, pos) + "<br/>(...)";
         }
      }
      return description;
   }
}
