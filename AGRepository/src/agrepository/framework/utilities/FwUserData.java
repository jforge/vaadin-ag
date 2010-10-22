package agrepository.framework.utilities;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class FwUserData implements Serializable {
   private static final Logger LOG = Logger.getLogger(FwUserData.class);
   private static final long serialVersionUID = -3076730510726507250L;
   private String username;
   private String name;
   private String email;

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

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
