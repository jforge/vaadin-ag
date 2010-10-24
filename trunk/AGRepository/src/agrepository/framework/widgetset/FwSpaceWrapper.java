package agrepository.framework.widgetset;

import agrepository.framework.widgetset.client.ui.VSpaceWrapper;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

/**
 * Server side component for the VSpaceWrapper widget.
 */
@ClientWidget(VSpaceWrapper.class)
public class FwSpaceWrapper extends AbstractComponent {
   private static final long serialVersionUID = 2982631323804915038L;
   private AbstractComponent wrappedComponent;

   public FwSpaceWrapper(AbstractComponent cmp) {
      wrappedComponent = cmp;
   }

   int[] margins = {
            0, 0, 0, 0
   };
   String[] marginSizeUnits = {
            "px", "px", "px", "px"
   };
   int[] paddings = {
            0, 0, 0, 0
   };
   String[] paddingSizeUnits = {
            "px", "px", "px", "px"
   };

   @Override
   public void paintContent(PaintTarget target) throws PaintException {
      super.paintContent(target);
      if (wrappedComponent.getParent() != this) {
         wrappedComponent.setParent(this);
      }
      target.addAttribute("margtop", margins[0] + marginSizeUnits[0]);
      target.addAttribute("margright", margins[1] + marginSizeUnits[1]);
      target.addAttribute("margbottom", margins[2] + marginSizeUnits[2]);
      target.addAttribute("margleft", margins[3] + marginSizeUnits[3]);
      target.addAttribute("padtop", paddings[0] + paddingSizeUnits[0]);
      target.addAttribute("padright", paddings[1] + paddingSizeUnits[1]);
      target.addAttribute("padbottom", paddings[2] + paddingSizeUnits[2]);
      target.addAttribute("padleft", paddings[3] + paddingSizeUnits[3]);
      target.startTag("wrapped");
      wrappedComponent.paint(target);
      target.endTag("wrapped");
   }

   public void setMargins(int[] margins) {
      this.margins = margins;
      requestRepaint();
   }

   public void setMarginTop(int top) {
      this.margins[0] = top;
      requestRepaint();
   }

   public void setMarginRight(int right) {
      this.margins[1] = right;
      requestRepaint();
   }

   public void setMarginBottom(int bottom) {
      this.margins[2] = bottom;
      requestRepaint();
   }

   public void setMarginLeft(int left) {
      this.margins[3] = left;
      requestRepaint();
   }

   public void setMargins(String[] margins) {
      for (int i = 0; i < margins.length; i++) {
         parseMargins(i, margins[i]);
      }
      requestRepaint();
   }

   public void setMarginTop(String top) {
      parseMargins(0, top);
      requestRepaint();
   }

   public void setMarginRight(String right) {
      parseMargins(1, right);
      requestRepaint();
   }

   public void setMarginBottom(String bottom) {
      parseMargins(2, bottom);
      requestRepaint();
   }

   public void setMarginLeft(String left) {
      parseMargins(3, left);
      requestRepaint();
   }

   private void parseMargins(int i, String str) {
      if ((str == null) || str.equals("")) {
         return;
      }
      try {
         String word = str.replaceAll("\\d", "");
         if (word.length() > 2) {
            return;
         }
         String number = str.replace(word, "");
         this.margins[i] = Integer.parseInt(number);
         this.marginSizeUnits[i] = word;
      } catch (Exception e) {
      }
   }

   public void addMarginTop(int top) {
      this.margins[0] += top;
      requestRepaint();
   }

   public void addMarginRight(int right) {
      this.margins[1] += right;
      requestRepaint();
   }

   public void addMarginBottom(int bottom) {
      this.margins[2] += bottom;
      requestRepaint();
   }

   public void addMarginLeft(int left) {
      this.margins[3] += left;
      requestRepaint();
   }

   public int[] getMargins() {
      return margins;
   }

   public int getMarginTop() {
      return margins[0];
   }

   public int getMarginRight() {
      return margins[1];
   }

   public int getMarginBottom() {
      return margins[2];
   }

   public int getMarginLeft() {
      return margins[3];
   }

   public void setPaddings(int[] paddings) {
      this.paddings = paddings;
      requestRepaint();
   }

   public void setPaddingTop(int top) {
      this.paddings[0] = top;
      requestRepaint();
   }

   public void setPaddingRight(int right) {
      this.paddings[1] = right;
      requestRepaint();
   }

   public void setPaddingBottom(int bottom) {
      this.paddings[2] = bottom;
      requestRepaint();
   }

   public void setPaddingLeft(int left) {
      this.paddings[3] = left;
      requestRepaint();
   }

   public void setPaddings(String[] paddings) {
      for (int i = 0; i < paddings.length; i++) {
         parsePaddings(i, paddings[i]);
      }
      requestRepaint();
   }

   public void setPaddingTop(String top) {
      parsePaddings(0, top);
      requestRepaint();
   }

   public void setPaddingRight(String right) {
      parsePaddings(1, right);
      requestRepaint();
   }

   public void setPaddingBottom(String bottom) {
      parsePaddings(2, bottom);
      requestRepaint();
   }

   public void setPaddingLeft(String left) {
      parsePaddings(3, left);
      requestRepaint();
   }

   private void parsePaddings(int i, String str) {
      if ((str == null) || str.equals("")) {
         return;
      }
      try {
         String word = str.replaceAll("\\d", "");
         if (word.length() > 2) {
            return;
         }
         String number = str.replace(word, "");
         this.paddings[i] = Integer.parseInt(number);
         this.paddingSizeUnits[i] = word;
      } catch (Exception e) {
      }
   }

   public void addPaddingTop(int top) {
      this.paddings[0] += top;
      requestRepaint();
   }

   public void addPaddingRight(int right) {
      this.paddings[1] += right;
      requestRepaint();
   }

   public void addPaddingBottom(int bottom) {
      this.paddings[2] += bottom;
      requestRepaint();
   }

   public void addPaddingLeft(int left) {
      this.paddings[3] += left;
      requestRepaint();
   }

   public int[] getPaddings() {
      return paddings;
   }

   public int getPaddingTop() {
      return paddings[0];
   }

   public int getPaddingRight() {
      return paddings[1];
   }

   public int getPaddingBottom() {
      return paddings[2];
   }

   public int getPaddingLeft() {
      return paddings[3];
   }

   @Override
   public void setWidth(String width) {
      if (width.contains("%")) {
         wrappedComponent.setWidth("100%");
         super.setWidth(width);
      } else {
         wrappedComponent.setWidth(width);
         super.setWidth(width);
      }
   }

   @Override
   public void setHeight(String height) {
      if (height.contains("%")) {
         wrappedComponent.setHeight("100%");
         super.setHeight(height);
      } else {
         wrappedComponent.setHeight(height);
         super.setHeight(height);
      }
   }

   @Override
   public void setSizeFull() {
   }

   @Override
   public void setSizeUndefined() {
   }

   public void setWidth(int width) {
   }

   public void setHeight(int height) {
   }

   public String[] getMarginSizeUnits() {
      return marginSizeUnits;
   }

   public void setMarginSizeUnits(String[] marginSizeUnits) {
      this.marginSizeUnits = marginSizeUnits;
   }

   public String[] getPaddingSizeUnits() {
      return paddingSizeUnits;
   }

   public void setPaddingSizeUnits(String[] paddingSizeUnits) {
      this.paddingSizeUnits = paddingSizeUnits;
   }

   public void setComponent(AbstractComponent component) {
      if (wrappedComponent != null) {
         wrappedComponent.setParent(null);
         wrappedComponent = null;
      }
      wrappedComponent = component;
      if (wrappedComponent == null) {
         return;
      }
      wrappedComponent.setParent(this);
      requestRepaint();
   }

   public AbstractComponent getComponent() {
      return wrappedComponent;
   }

   public String asHtmlString() {
      return "<b>Margins:</b> " + "[Top:<b> " + margins[0] + marginSizeUnits[0] + "</b>, Right:<b> " + margins[1]
               + marginSizeUnits[1] + "</b>, Bottom:<b> " + margins[2] + marginSizeUnits[2] + "</b> Left:<b> " + margins[3]
               + marginSizeUnits[3] + "</b>] -- <b>Paddings:</b> " + " [Top:<b> " + paddings[0] + paddingSizeUnits[0]
               + "</b>, Right:<b> " + paddings[1] + paddingSizeUnits[1] + "</b>, Bottom:<b> " + paddings[2] + paddingSizeUnits[2]
               + "</b>, Left:<b> " + paddings[3] + paddingSizeUnits[3] + "</b>] -- <b>Contained component:</b> "
               + wrappedComponent.getClass();
   }
}
