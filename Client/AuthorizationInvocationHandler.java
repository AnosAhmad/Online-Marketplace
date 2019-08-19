import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AuthorizationInvocationHandler implements InvocationHandler, Serializable 
{
  /* Private Memebers */
  private Object objectImpl;
 
  /*Contructor */
  public AuthorizationInvocationHandler(Object impl) 
  {
   this.objectImpl = impl;  
  }
 
 /* 
 * Check the authority of user befor invoke the method through match the role in the Java RMI with Session object role,
 where if the two role match the method will be invoked else an Exception message will be displayed. 
 */
 
 @Override
 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
 {
  if (method.isAnnotationPresent(RequiresRole.class)) {
	RequiresRole test = method.getAnnotation(RequiresRole.class);
	Session session = (Session) args[0];

  if (session.getRole().equals(test.value())) {
 	return method.invoke(objectImpl, args);
  } else {
    throw new AuthorizationException(method.getName());
  }
  } else {
	 return method.invoke(objectImpl, args);
    }   
 }
}