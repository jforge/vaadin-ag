package agrepository.framework.widgetset;

import java.util.Map;

import agrepository.framework.widgetset.client.ui.VFwExtComponent;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

/**
 * Server side component for the VFwExtComponent widget.
 */
@ClientWidget(VFwExtComponent.class)
public class FwExtComponent extends AbstractComponent {
   private static final long serialVersionUID = 3845322697736693353L;
   private String message = "Click here.";
   private int clicks = 0;

   @Override
   public void paintContent(PaintTarget target) throws PaintException {
      super.paintContent(target);
      // Paint any component specific content by setting attributes
      // These attributes can be read in updateFromUIDL in the widget.
      target.addAttribute("clicks", clicks);
      target.addAttribute("message", message);
      // We could also set variables in which values can be returned
      // but declaring variables here is not required
   }

   /**
    * Receive and handle events and other variable changes from the client.
    * 
    * {@inheritDoc}
    */
   @Override
   public void changeVariables(Object source, Map<String, Object> variables) {
      super.changeVariables(source, variables);
      // Variables set by the widget are returned in the "variables" map.
      if (variables.containsKey("click")) {
         // When the user has clicked the component we increase the
         // click count, update the message and request a repaint so
         // the changes are sent back to the client.
         clicks++;
         message += "<br/>" + variables.get("click");
         requestRepaint();
      }
   }
}
