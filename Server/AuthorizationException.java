/*
* Exception Class if the client try to access into unauthorized methods
*/

public class AuthorizationException extends RuntimeException 
{
    public AuthorizationException(String methodName) 
	{
	    // Exception message;
	    super("Invalid Authorization - Access Denined to " + methodName + " function!\n");
    }
}