import java.io.*;
import java.util.*;

/*
* Session Class -- Implement the Session Interface
*/

public class SessionImpl implements  Session,java.io.Serializable
{ 
 // Data Members
 private String username, password, role;
 
 /*Constructor */ 
 public SessionImpl (String username,  String password, String role )
 {  
   this.username = username;
   this.password = password;
   this.role = role; 
 }

 //Get methods
 public String getUsername(){ return username;}
 public String getPassword(){ return password;}
 public String getRole(){ return role;}
}