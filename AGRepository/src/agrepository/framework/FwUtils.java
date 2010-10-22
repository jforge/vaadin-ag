package agrepository.framework;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.vaadin.terminal.gwt.server.JsonPaintTarget;

public class FwUtils implements Serializable {
   private static Logger LOG = Logger.getLogger(FwUtils.class);
   private static final long serialVersionUID = 7563323290240919946L;

   public static String escapeXML(String value) {
      return value == null ? null : JsonPaintTarget.escapeXML(value);
   }

   public static String format(String message, Object... arguments) {
      if (message == null) {
         return null;
      }
      return String.format(message, arguments);
   }

   public static boolean isEmpty(String value) {
      return (value == null) || "".equals(value.trim());
   }
}
