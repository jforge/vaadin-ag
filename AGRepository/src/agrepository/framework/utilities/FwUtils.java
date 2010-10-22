package agrepository.framework.utilities;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.terminal.gwt.server.JsonPaintTarget;

public class FwUtils implements Serializable {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwUtils.class);
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
