package agrepository.framework.widgetset.client.ui;

import java.util.Set;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Container;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.RenderSpace;
import com.vaadin.terminal.gwt.client.UIDL;

public class VFwSpaceWrapper extends SimplePanel implements Paintable, Container {
   public static final String CLASSNAME = "v-spacewrapper";
   public static final String CLICK_EVENT_IDENTIFIER = "click";
   protected String paintableId;
   protected ApplicationConnection client;
   private Widget wrapped;
   private String margTop = "";
   private String margRight = "";
   private String margBottom = "";
   private String margLeft = "";
   private String padTop = "";
   private String padRight = "";
   private String padBottom = "";
   private String padLeft = "";

   public VFwSpaceWrapper() {
      setStyleName(CLASSNAME);
   }

   @Override
   public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
      if (client.updateComponent(this, uidl, true)) {
         return;
      }
      this.client = client;
      paintableId = uidl.getId();
      updateMargins(uidl);
      updatePaddings(uidl);
      final UIDL contentUidl = uidl.getChildUIDL(0);
      Paintable contentContent = null;
      if (contentUidl.getChildCount() > 0) {
         contentContent = client.getPaintable(contentUidl.getChildUIDL(0));
         if (wrapped == null) {
            setWidget((Widget) contentContent);
            wrapped = (Widget) contentContent;
         } else if (wrapped != (Widget) contentContent) {
            replaceChildComponent(wrapped, (Widget) contentContent);
            wrapped = (Widget) contentContent;
         }
         contentContent.updateFromUIDL(contentUidl.getChildUIDL(0), client);
      }
   }

   private void updateMargins(UIDL uidl) {
      if (uidl.hasAttribute("margtop")) {
         String top = uidl.getStringAttribute("margtop");
         if (top != margTop) {
            DOM.setStyleAttribute(getElement(), "marginTop", top);
         }
         margTop = top;
      }
      if (uidl.hasAttribute("margright")) {
         String right = uidl.getStringAttribute("margright");
         if (right != margRight) {
            DOM.setStyleAttribute(getElement(), "marginRight", right);
         }
         margRight = right;
      }
      if (uidl.hasAttribute("margbottom")) {
         String bot = uidl.getStringAttribute("margbottom");
         if (bot != margBottom) {
            DOM.setStyleAttribute(getElement(), "marginBottom", bot);
         }
         margBottom = bot;
      }
      if (uidl.hasAttribute("margleft")) {
         String left = uidl.getStringAttribute("margleft");
         if (left != margLeft) {
            DOM.setStyleAttribute(getElement(), "marginLeft", left);
         }
         margLeft = left;
      }
   }

   private void updatePaddings(UIDL uidl) {
      if (uidl.hasAttribute("padtop")) {
         String top = uidl.getStringAttribute("padtop");
         if (top != padTop) {
            DOM.setStyleAttribute(getElement(), "paddingTop", top);
         }
         padTop = top;
      }
      if (uidl.hasAttribute("padright")) {
         String right = uidl.getStringAttribute("padright");
         if (right != padRight) {
            DOM.setStyleAttribute(getElement(), "paddingRight", right);
         }
         padRight = right;
      }
      if (uidl.hasAttribute("padbottom")) {
         String bot = uidl.getStringAttribute("padbottom");
         if (bot != padBottom) {
            DOM.setStyleAttribute(getElement(), "paddingBottom", bot);
         }
         padBottom = bot;
      }
      if (uidl.hasAttribute("padleft")) {
         String left = uidl.getStringAttribute("padleft");
         if (left != padLeft) {
            DOM.setStyleAttribute(getElement(), "paddingLeft", left);
         }
         padLeft = left;
      }
   }

   @Override
   public RenderSpace getAllocatedSpace(Widget child) {
      return new RenderSpace(child.getElement().getParentElement().getOffsetWidth(), child.getElement().getParentElement()
               .getOffsetHeight());
   }

   @Override
   public boolean hasChildComponent(Widget component) {
      return component == wrapped;
   }

   @Override
   public void replaceChildComponent(Widget oldComponent, Widget newComponent) {
   }

   @Override
   public boolean requestLayout(Set<Paintable> children) {
      boolean bool = false;
      for (Paintable paintable : children) {
         if (client.handleComponentRelativeSize((Widget) paintable)) {
            bool = true;
         }
      }
      return bool;
   }

   @Override
   public void updateCaption(Paintable component, UIDL uidl) {
   }
}
