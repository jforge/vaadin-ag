package agrepository.framework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.ParameterHandler;
import com.vaadin.terminal.URIHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

public abstract class ExtListener implements Button.ClickListener, Property.ValueChangeListener, URIHandler, ParameterHandler,
         Upload.Receiver, Upload.FinishedListener, Table.ColumnGenerator, Window.CloseListener, Serializable {
   private static final Logger LOG = Logger.getLogger(ExtListener.class);
   private static final long serialVersionUID = -5630629147168390726L;
   private ByteArrayOutputStream uploadData;
   private boolean lock;
   private ExtApplication application;

   public ExtListener() {
      application = ExtApplication.current();
   }

   @Override
   public void uploadFinished(FinishedEvent event) {
   }

   @Override
   public OutputStream receiveUpload(String filename, String MIMEType) {
      uploadData = new ByteArrayOutputStream();
      return uploadData;
   }

   @Override
   public DownloadStream handleURI(URL context, String relativeURI) {
      try {
         ExtEvent event = new ExtEvent(context, relativeURI);
         execOnce(event);
         if (event.getReturnStream() != null) {
            return event.getReturnStream();
         } else if (event.getReturnData() != null) {
            String type = event.getReturnDataType() != null ? event.getReturnDataType() : application.getParameters().get(
                     "default.downloadMimeType");
            String name = event.getReturnDataName() != null ? event.getReturnDataName() : application.getParameters().get(
                     "default.downloadFileName");
            return new DownloadStream(new ByteArrayInputStream(event.getReturnData()), type, name);
         }
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
      return null;
   }

   public void unhandledError(Throwable throwable) {
      unhandledError(application.getTranslator().get("error.unhandledException"), throwable);
   }

   public void unhandledError(String message, Throwable t) {
      ExtApplication application = ExtApplication.current();
      if ((application != null) && (application.getUserMessages() != null)) {
         application.getUserMessages().error(message, t);
      } else {
         LOG.error(t);
      }
   }

   @Override
   public void handleParameters(Map<String, String[]> parameters) {
      try {
         execOnce(new ExtEvent(parameters));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   @Override
   public void valueChange(ValueChangeEvent event) {
      try {
         execOnce(new ExtEvent(event));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   @Override
   public void buttonClick(ClickEvent event) {
      try {
         execOnce(new ExtEvent(event));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   @Override
   public Component generateCell(Table source, Object itemId, Object columnId) {
      ExtEvent event = new ExtEvent(source, itemId, columnId);
      try {
         execOnce(event);
         return event.getReturnComponent();
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
      return null;
   }

   @Override
   public void windowClose(CloseEvent event) {
      try {
         execOnce(new ExtEvent(event));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   protected synchronized void execOnce(ExtEvent event) throws Exception {
      if (lock) {
         return;
      }
      lock = true;
      try {
         exec(event);
      } catch (Exception exception) {
         throw exception;
      } finally {
         lock = false;
      }
   }

   public abstract void exec(ExtEvent event) throws Exception;
}
