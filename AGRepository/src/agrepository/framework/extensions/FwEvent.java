package agrepository.framework.extensions;

import java.io.Serializable;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vaadin.data.Property;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

public class FwEvent implements Serializable {
   private static final Logger LOG = Logger.getLogger(FwEvent.class);
   private static final long serialVersionUID = -2603225525242512107L;
   private Button.ClickEvent clickEvent;
   private Property.ValueChangeEvent valueChangeEvent;
   private FinishedEvent finishedEvent;
   private Map<String, String[]> parameters;
   private URL contextURL;
   private String relativeURI;
   private byte[] data;
   private DownloadStream returnStream;
   private byte[] returnData;
   private String returnDataType;
   private String returnDataName;
   private Component returnComponent;
   private Table table;
   private Object itemId;
   private Object columnId;
   private CloseEvent closeEvent;
   private int[] modifiers;
   private int key;

   public FwEvent(Upload.FinishedEvent event, byte[] data) {
      finishedEvent = event;
      this.data = data;
   }

   public FwEvent(Button.ClickEvent event) {
      clickEvent = event;
   }

   public FwEvent(Property.ValueChangeEvent event) {
      valueChangeEvent = event;
   }

   public FwEvent(Map<String, String[]> parameters) {
      this.parameters = parameters;
   }

   public FwEvent(URL contextURL, String relativeURI) {
      this.contextURL = contextURL;
      this.relativeURI = relativeURI;
   }

   public FwEvent(Table table, Object itemId, Object columnId) {
      this.table = table;
      this.itemId = itemId;
      this.columnId = columnId;
   }

   public FwEvent(CloseEvent event) {
      closeEvent = event;
   }

   public FwEvent(int key, int[] modifiers) {
      this.key = key;
      this.modifiers = modifiers;
   }

   public String getURI() {
      return relativeURI;
   }

   public URL getContextURL() {
      return contextURL;
   }

   public Map<String, String[]> getParameters() {
      return parameters;
   }

   public Button.ClickEvent getClickEvent() {
      return clickEvent;
   }

   public Window.CloseEvent getCloseEvent() {
      return closeEvent;
   }

   public Property.ValueChangeEvent getValueChangeEvent() {
      return valueChangeEvent;
   }

   public String getParameter(String key) {
      if (parameters != null) {
         String[] values = parameters.get(key);
         if ((values != null) && (values.length > 0)) {
            return values[0];
         }
      }
      return null;
   }

   public void returnStream(DownloadStream returnStream) {
      this.returnStream = returnStream;
   }

   public DownloadStream getReturnStream() {
      return returnStream;
   }

   public void returnData(byte[] returnData) {
      this.returnData = returnData;
   }

   public void returnDataType(String returnDataType) {
      this.returnDataType = returnDataType;
   }

   public void returnDataName(String returnDataName) {
      this.returnDataName = returnDataName;
   }

   public byte[] getReturnData() {
      return returnData;
   }

   public String getReturnDataType() {
      return returnDataType;
   }

   public String getReturnDataName() {
      return returnDataName;
   }

   public Object getValue() {
      Object value = null;
      if ((valueChangeEvent != null) && (valueChangeEvent.getProperty() != null)) {
         value = valueChangeEvent.getProperty().getValue();
      }
      return value == null ? null : value;
   }

   public String getStringValue() {
      Object value = getValue();
      return value == null ? null : value.toString();
   }

   public byte[] getData() {
      return data;
   }

   public FinishedEvent getFinishedEvent() {
      return finishedEvent;
   }

   public String getText() {
      return getStringValue();
   }

   public Component getReturnComponent() {
      return returnComponent;
   }

   public void returnComponent(Component returnComponent) {
      this.returnComponent = returnComponent;
   }

   public Table getTable() {
      return table;
   }

   public Window getWindow() {
      return closeEvent != null ? closeEvent.getWindow() : null;
   }

   public Object getItemId() {
      return itemId;
   }

   public Object getColumnId() {
      return columnId;
   }

   public Button getButton() {
      return clickEvent != null ? clickEvent.getButton() : null;
   }

   public int getKey() {
      return key;
   }

   public int[] getModifiers() {
      return modifiers;
   }
}
