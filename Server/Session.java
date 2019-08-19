/*
* Session Interface
*/

public interface Session extends java.io.Serializable
{ 
  String getUsername();
  String getPassword();
  String getRole();
}