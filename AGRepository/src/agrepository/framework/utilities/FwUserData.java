package agrepository.framework.utilities;

import java.io.Serializable;

import oracle.jdbc.OracleConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FwUserData implements Serializable {
   @SuppressWarnings("unused")
   private static final Log LOG = LogFactory.getLog(FwUserData.class);
   private static final long serialVersionUID = -3076730510726507250L;
   private String username;
   private String name;
   private String email;
   private boolean autoLogin;
   private boolean autoLogout;
   private OracleConnection connection;

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

   public OracleConnection getConnection() {
      if (connection == null) {
      }
      return connection;
   }

   public boolean isAutoLogin() {
      return autoLogin;
   }

   public void setAutoLogin(boolean autoLogin) {
      this.autoLogin = autoLogin;
   }

   public boolean isAutoLogout() {
      return autoLogout;
   }

   public void setAutoLogout(boolean autoLogout) {
      this.autoLogout = autoLogout;
   }
}