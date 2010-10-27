package agrepository.framework.extensions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agrepository.framework.utilities.FwParameters;
import agrepository.framework.utilities.FwTranslator;

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

public abstract class FwListener_ORIGINAL implements Button.ClickListener, Property.ValueChangeListener, URIHandler, ParameterHandler,
         Upload.Receiver, Upload.FinishedListener, Table.ColumnGenerator, Window.CloseListener, Serializable {
   private static final Log LOG = LogFactory.getLog(FwListener_ORIGINAL.class);
   private static final long serialVersionUID = -5630629147168390726L;
   private ByteArrayOutputStream uploadData;
   private boolean lock;
   private FwApplication application;
   private FwTranslator translator;
   private FwParameters parameters;

   public FwListener_ORIGINAL() {
      application = FwApplication.current();
      translator = application.getTranslator();
      parameters = application.getParameters();
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
         FwEvent event = new FwEvent(context, relativeURI);
         execOnce(event);
         if (event.getReturnStream() != null) {
            return event.getReturnStream();
         } else if (event.getReturnData() != null) {
            String type = event.getReturnDataType() != null ? event.getReturnDataType() : parameters
                     .getString("default.downloadMimeType");
            String name = event.getReturnDataName() != null ? event.getReturnDataName() : parameters
                     .getString("default.downloadFileName");
            return new DownloadStream(new ByteArrayInputStream(event.getReturnData()), type, name);
         }
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
      return null;
   }

   public void unhandledError(Throwable throwable) {
      unhandledError(translator.get("error.unhandledException"), throwable);
   }

   public void unhandledError(String message, Throwable t) {
      FwApplication application = FwApplication.current();
      if ((application != null) && (application.getUserMessages() != null)) {
         application.getUserMessages().error(message, t);
      } else {
         LOG.error(t);
      }
   }

   @Override
   public void handleParameters(Map<String, String[]> parameters) {
      try {
         execOnce(new FwEvent(parameters));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   @Override
   public void valueChange(ValueChangeEvent event) {
      try {
         execOnce(new FwEvent(event));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   @Override
   public void buttonClick(ClickEvent event) {
      try {
         execOnce(new FwEvent(event));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   @Override
   public Component generateCell(Table source, Object itemId, Object columnId) {
      FwEvent event = new FwEvent(source, itemId, columnId);
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
         execOnce(new FwEvent(event));
      } catch (Throwable throwable) {
         unhandledError(throwable);
      }
   }

   protected synchronized void execOnce(FwEvent event) throws Exception {
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

   public abstract void exec(FwEvent event) throws Exception;
}
