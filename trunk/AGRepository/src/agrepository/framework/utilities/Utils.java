package agrepository.framework.utilities;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.vaadin.terminal.gwt.server.JsonPaintTarget;

public class Utils implements Serializable {
   private static Logger LOG = Logger.getLogger(Utils.class);
   private static final long serialVersionUID = 7563323290240919946L;

   public static String escapeXML(String s) {
      return s == null ? null : JsonPaintTarget.escapeXML(s);
   }

   public static String format(String message, Object... arguments) {
      if (message == null) {
         return null;
      }
      return String.format(message, arguments);
   }

   public static boolean isEmpty(String s) {
      return (s == null) || "".equals(s.trim());
   }
}
