import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.reflect.Proxy; 

/*
* Server Side Class - Application Controller
*/

public class OnlineMarketplaceApplicationController extends UnicastRemoteObject implements OnlineMarketplace 
{ 
 // Data Members
 private OnlineMarketplaceModel onlineMarket ; 
 
 public OnlineMarketplaceApplicationController() throws RemoteException {}
 public void binding(String name) throws RemoteException
  {
    System.setSecurityManager(new SecurityManager());
	try{
		onlineMarket  = new OnlineMarketplaceModel();
		System.out.println("Creating a OnlineMarketplace Server!");
 		System.out.println("OnlineMarketplaceServer : binding it to name: " + name);
		OnlineMarketplace assignment = (OnlineMarketplace) Proxy.newProxyInstance(OnlineMarketplace.class.getClassLoader(),
        new Class<?>[] {OnlineMarketplace.class}, new AuthorizationInvocationHandler(this));
		Naming.rebind(name, assignment );            
		System.out.println("OnlineMarketplaceServer Server Ready!");
		} catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
		}	
  }

public synchronized  String signUp(Session session, String userName, String passWord, String role) throws RemoteException
 {
    return onlineMarket.signUp(session, userName, passWord, role);
 }
 
public  synchronized  Session signIn(String userName, String passWord) throws RemoteException
 {	
    return onlineMarket.signIn( userName, passWord);
 }
	
public  synchronized  String removeAccount(Session session, String userName) throws RemoteException
{
   return onlineMarket.removeAccount(session, userName);
}   

public synchronized String addItemSys (Session session, Item newItem) throws RemoteException
{
   return onlineMarket.addItemSys(session, newItem);
}
public  synchronized void removeItem (Session session,int itemId) throws RemoteException
{ 
   onlineMarket.removeItem (session, itemId);
}
 
public  synchronized String browseItems (Session session) throws RemoteException
 {
    return onlineMarket.browseItems(session);
}

public synchronized String addItemSC (Session session,int itemId, int itemQuantity) throws RemoteException
 {
    return onlineMarket.addItemSC (session, itemId, itemQuantity);
 }
 
public synchronized String updateItem (Session session,Item modifiedItem) throws RemoteException
 {
    return onlineMarket.updateItem (session, modifiedItem);
 }
	
public synchronized void removeItemSC (Session session,int itemId) throws RemoteException
 {
    onlineMarket.removeItemSC (session, itemId);
	}

public synchronized String browseItemsSC (Session session) throws RemoteException
{
   return onlineMarket.browseItemsSC(session);
   }
   
public synchronized  String purchingItems (Session session, int itemId, int itemQuantity) throws RemoteException
{ 
    return  onlineMarket.purchingItems (session, itemId, itemQuantity);
	}	
}