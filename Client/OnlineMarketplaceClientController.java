import java.rmi.*;
import java.util.Scanner;
import java.rmi.Naming;
import java.io.*;
import java.util.*;
import java.text.*;

/*
* Client Side -- Client Controller 
*/

public class OnlineMarketplaceClientController 
 {
    static private OnlineMarketplace myMarket;
    private String currentTime;
    private String executionTime;

    public static void main(String args[]) 
    {
	    System.out.println("\n \nWelcome!!!!!!\n");
	    System.setSecurityManager(new SecurityManager());
	    try{  
            String name = (args.length < 1) ? null : args[0];  
            myMarket = (OnlineMarketplace) Naming.lookup(name);            
            OnlineMarketplaceFrontController obj = new OnlineMarketplaceFrontController (myMarket);
            } catch(Exception e){
			System.out.println("OnlineMarketplaceClientController Exception: " +
			e.getMessage()); e.printStackTrace();
			
		}
		System.exit(0);
	}

  /* Method to call SignUp method*/
  public String signUp(String userName, String password, String role, Session session,Calendar usercurrentTime)
  {
    try{
    currentTime = getCurrentTimeStamp();
    while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime()))){
       currentTime = getCurrentTimeStamp();
    }
    System.out.println(currentTime);
    return myMarket.signUp(session,userName, password,role);
    }
	catch(Exception e) 
	{ e.printStackTrace();
	return(e.getMessage());  
	}
  }
  
  /* Method to call removeAccount method */
  public String removeAccount(String userName, Session session, Calendar usercurrentTime)
  {
    try{
    currentTime = getCurrentTimeStamp();
    while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime()))){
       currentTime = getCurrentTimeStamp();
	}
    System.out.println("Execute Time" + currentTime);
    return myMarket.removeAccount(session, userName); 
	}
	catch(Exception e) 
	{ return(e.getMessage());}
  }
  
  /* Method to call Additem to system method */
 public String addItemSys(Item newItem, Calendar calendar, Session session, Calendar usercurrentTime)
  {
    try{
      currentTime = getCurrentTimeStamp();
      while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime()))){
       currentTime = getCurrentTimeStamp();}
       System.out.println("Execute Time" + currentTime);
      return myMarket.addItemSys(session, newItem);
    }
    catch(Exception e) 
	{ return(e.getMessage());}
}

/* Method to call Update item in system method */
 public String updateItem(Item newItem, Session session, Calendar usercurrentTime)
  {
    try{
       currentTime = getCurrentTimeStamp();
       while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime())))
       {
         currentTime = getCurrentTimeStamp();
       }
       System.out.println("Execute Time" + currentTime);
      return myMarket.updateItem(session, newItem);}
    catch(Exception e) 
	{ return(e.getMessage());}
}
      
/* Method to call removeItem method */
  public void removeItem( int itemId, Session session, Calendar usercurrentTime)
  {
    try{
       currentTime = getCurrentTimeStamp();
       while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime()))){
         currentTime = getCurrentTimeStamp();
       }
       System.out.println("Execute Time" + currentTime);
	   myMarket.removeItem (session, itemId); 
   	}
	catch(Exception e) 
	{ System.out.println(e.getMessage());}
  }
  
/* Method to call browseItems method */
public String browseItems(Session session, Calendar usercurrentTime)
{
    try { 
        currentTime = getCurrentTimeStamp();
        while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime()))) {
         currentTime = getCurrentTimeStamp();
        }
        System.out.println("Execute Time" + currentTime);
        return myMarket.browseItems(session); 
	}
	catch(Exception e) 	{ return e.getMessage();}
}

/* Method to call addItem to SC method */
public String addItem( Session session,int itemId,  int itemQuantity, Calendar usercurrentTime)
{
   try{
    currentTime = getCurrentTimeStamp();
    while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime()))) {
      currentTime = getCurrentTimeStamp();}
    System.out.println("Execute Time" + currentTime);
    return myMarket.addItemSC ( session, itemId, itemQuantity);}
    catch(Exception e) 	{ return e.getMessage();}
}

/* Method to call browseItemsSC method */
public String browseItemsSC(Session session, Calendar usercurrentTime)
{
   try {
    currentTime = getCurrentTimeStamp();
    while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime()))) {
        currentTime = getCurrentTimeStamp();
    }
    System.out.println("Execute Time" + currentTime); 
    return myMarket.browseItemsSC(session); }
    catch(Exception e) 	{ return e.getMessage();}
}

/* Method to call removeItemSC method */
public void removeItemSC(Session session, int itemId, Calendar usercurrentTime)
{
  try{
    currentTime = getCurrentTimeStamp();
    while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime()))) {
        currentTime = getCurrentTimeStamp();     }
    System.out.println("Execute Time" + currentTime);
    myMarket.removeItemSC(session, itemId); }
    catch(Exception e) 	{ System.out.println(e.getMessage());}
}

/* Method to call purchingItems method */
public String purchingItems(Session session, int itemId, int itemQuantity, Calendar usercurrentTime)
{
    try{
       currentTime = getCurrentTimeStamp();
       while (!currentTime.equals(new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(usercurrentTime.getTime())))
       {
        currentTime = getCurrentTimeStamp();
       }
       System.out.println("Execute Time" + currentTime);    
       return myMarket.purchingItems(session, itemId, itemQuantity);}
       catch(Exception e) 	{ return e.getMessage();}
}

public String getCurrentTimeStamp() { 
   Calendar calendar = Calendar.getInstance();
   return new SimpleDateFormat("yyyy MMM dd HH:mm:ss").format(calendar.getTime());
} 
}