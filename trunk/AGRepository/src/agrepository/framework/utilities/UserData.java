package agrepository.framework.utilities;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class UserData implements Serializable {
   private static final Logger LOG = Logger.getLogger(UserData.class);
   private static final long serialVersionUID = -3076730510726507250L;
   private String username;
   private String name;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
