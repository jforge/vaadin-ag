package agrepository.framework.widgetset;

import java.text.DecimalFormatSymbols;

import agrepository.framework.widgetset.client.ui.VFwNumericField;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.TextField;

@ClientWidget(VFwNumericField.class)
public class FwNumericField extends TextField {
   private static final long serialVersionUID = 6093861878864667151L;
   private boolean onlyIntegerValues;
   private Object minValue;
   private Object maxValue;

   @Override
   public void paintContent(PaintTarget target) throws PaintException {
      super.paintContent(target);
      target.addAttribute("separatorChar", String.valueOf(DecimalFormatSymbols.getInstance().getDecimalSeparator()));
      target.addAttribute("onlyIntegerValues", onlyIntegerValues);
   }

   public boolean isOnlyIntegerValues() {
      return onlyIntegerValues;
   }

   public void setOnlyIntegerValues(Boolean onlyIntegerValues) {
      this.onlyIntegerValues = onlyIntegerValues;
      requestRepaint();
   }

   public Object getMinValue() {
      return minValue;
   }

   public void setMinValue(Object minValue) {
      this.minValue = minValue;
   }

   public Object getMaxValue() {
      return maxValue;
   }

   public void setMaxValue(Object maxValue) {
      this.maxValue = maxValue;
   }
}
