public interface OnlineMarketplace extends java.rmi.Remote
{
    //All the users can invoke this method.
	String browseItems (Session session) throws java.rmi.RemoteException; 
	
	//Just the Administrator can invoke this method.
	@RequiresRole(value = "Admin")
	String updateItem (Session session, Item modifiedItem) throws java.rmi.RemoteException; 
	
	//Just the Administrator can invoke this method.
	@RequiresRole(value = "Admin")
	void removeItem (Session session, int itemId) throws java.rmi.RemoteException; 
	
	//Just the Administrator can invoke this method.
	@RequiresRole(value = "Admin")
	String addItemSys (Session session, Item newItem) throws java.rmi.RemoteException;
	
	//Just the Customer can invoke this method.
	@RequiresRole(value = "Customer")
	String addItemSC (Session session, int itemId, int itemQuantity) throws java.rmi.RemoteException;
	
	//Just the Customer can invoke this method.
	@RequiresRole(value = "Customer")
	void removeItemSC (Session session, int itemId) throws java.rmi.RemoteException;
	
	//Just the Customer can invoke this method.
	@RequiresRole(value = "Customer")
	String browseItemsSC (Session session) throws java.rmi.RemoteException;
	
	//Just the Customer can invoke this method.
	@RequiresRole(value = "Customer")
	String purchingItems (Session session, int itemId, int itemQuantity) throws java.rmi.RemoteException; 

	//All the users can invoke this method.
    String signUp(Session session, String userName, String passWord, String role) throws java.rmi.RemoteException;
	
	//Just the Administrator can invoke this method.
	@RequiresRole(value = "Admin")
	String removeAccount(Session session, String userName) throws java.rmi.RemoteException;
	
	//All the users can invoke this method.
	Session signIn(String userName, String passWord) throws java.rmi.RemoteException;
 }
