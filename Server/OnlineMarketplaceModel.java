import java.io.*;
import java.util.*;
import java.sql.*;
import java.lang.reflect.*;
import java.text.*;

/*
* Model Layer
*/

public class OnlineMarketplaceModel implements OnlineMarketplace, java.io.Serializable
{ 
  private BDManager dbManagerobj = new BDManager();
 
/* 
* SignUp Method
*/
public synchronized String signUp(Session session,String userName, String passWord, String role)
{
    String sql ,msg = "The Account is created sucessfully!!!";
    try{
	boolean	userNameExist = false;	
	ResultSet rs = dbManagerobj.usersSelectImpl();		
	while(rs.next()){
	   String Role = rs.getString("role");
	   if(Role.equals(role)){
         //Retrieve by column name
         String UserName = rs.getString("username");
		 //check if the user exist in the DB or not.
		 if(UserName.equals(userName))
		   userNameExist = true;
		 }   }     
      rs.close();
	if (userNameExist)
	  msg = "The Username exist";
	else 
     {        dbManagerobj.insertUsersImp(userName, passWord, role); 	    
	}
	}
	catch(SQLException se){
        se.printStackTrace();}		   
	System.out.println(msg);   
    return msg;
 }


/* 
* SignIn Method
*/
public synchronized Session signIn(String userName, String passWord) {
    Session session = null;   
   try{   
	ResultSet rs = dbManagerobj.usersSelectImpl();    	
    if (rs.next()) {
     do {
         //Retrieve by column name
         String UserName = rs.getString("username");
		 String PW = rs.getString("passphrase");
		 String rle = rs.getString("role");		 
		 //check if the user exist in the DB or not.
         if(UserName.equals(userName) && PW.equals(passWord))
		   {session = new SessionImpl(userName, passWord, rle);
		   }
        }while(rs.next());}
   else
	   System.out.println("No Users in the Database");
    rs.close();
	}
    catch(SQLException se){
        se.printStackTrace();} 
    return session;	
 }
 
/* 
* RemoveAccount Method
*/ 
public synchronized String removeAccount(Session session, String userName)
{
    boolean userNameExist = false;
    String msg = "The Account not exist" ;
	try{
	ResultSet rs = dbManagerobj.usersSelectImpl();	
	while(rs.next()){
         //Retrieve by column name
         String UserName = rs.getString("username");
		 //check if the user exist in the DB or not.
		 if(UserName.equals(userName)){			 
			dbManagerobj.deleteUsersImpl(userName);
			msg = "The Account is removed";
			}
        }
    rs.close();
	System.out.println(msg);}
	catch(SQLException se){
	    se.printStackTrace();} 
    return msg;
}

/* 
* Removing Item from the systrm 
*/
public synchronized void removeItem (Session session,int itemId)
 {
   try{
	String sql, msg = "No Item has this ID";	
	ResultSet rs = dbManagerobj.itemSelectImpl();
	
	while(rs.next()){
         //Retrieve by column name
         int id = rs.getInt("ID");
		 //check if the user exist in the DB or not.
		 if(id == itemId){
		    dbManagerobj.deleteItemImpl(itemId);
	        msg = "Item is removied Successfully...";
		 }
        }
    rs.close();
	System.out.println(msg);}
	catch(SQLException se){
        se.printStackTrace();} 
 }

/*
* Browsing the Available Items in the system.
*/

public synchronized String browseItems (Session session)
{
    String availableItems = " ";
	availableItems += "\n\tThe Available Products: \n" + 
	"ID\tQuantity\tPrice\tType\tDescription\n" +
	"-------------------------------------------------\n";;
	try{
    ResultSet rs = dbManagerobj.itemSelectImpl();
    //Extract data from result set
    while(rs.next()){
        //Retrieve by column name  
        int id  = rs.getInt("ID");
        int quantity = rs.getInt("Quantity");
		float price = rs.getFloat("Price");
        String itemtype = rs.getString("itemType");
        String description = rs.getString("Description");
        //Display values
		availableItems += id +"\t"+ quantity +"\t\t" + price + "\t" + itemtype + "\t"+description+ "\n";         
      }
    rs.close();}
	catch(SQLException se){
        se.printStackTrace();} 
    return availableItems;
 } 

/*
* Updating System Item 
*/
public synchronized String updateItem (Session session, Item modifiedItem)
{
     String msg = "No Item/Product in the System has this ID";
	try{
	ResultSet rs = dbManagerobj.itemSelectImpl();
    //Extract data from result set
    while(rs.next()){
        //Retrieve by column name  
        int id  = rs.getInt("ID");
        int quantity = rs.getInt("Quantity");
		
        //update the item
		if(id == modifiedItem.getId())
          {
             if((modifiedItem.getDescription()).length() != 0){
                dbManagerobj.updateItemDescriptionImp(modifiedItem.getDescription(),id);			
				}
             if((modifiedItem.getType()).length() != 0){
			    dbManagerobj.updateItemTypeImp(modifiedItem.getType(),id);				
                }
             if(modifiedItem.getPrice() != 0){
			    dbManagerobj.updateItemPriceImpl(modifiedItem.getPrice() , id);				
                }
             if(modifiedItem.getQualntity() != 0){
			    dbManagerobj.updateItemQuantityImpl( modifiedItem.getQualntity() , id);
                }
            msg = "The Item/Product( " + 
			modifiedItem.getId() + " )is Added/Updated successfully; Username:: "
			+ session.getUsername();
          } 	         
      }
    rs.close();	}
	catch(SQLException se){
        se.printStackTrace();} 
    return msg;
 }  
 
/*
* Adding Item to System.
*/
public synchronized String addItemSys (Session session, Item newItem)
 {
    String msg = "", sql;
	try{
	boolean itemExist = false;
	ResultSet rs = dbManagerobj.itemSelectImpl();
	
    //Extract data from result set
    while(rs.next()){
        //Retrieve by column name  
        int id  = rs.getInt("ID");
		int quantity = rs.getInt("Quantity");
        //Check if the new item exist or not
		if( id == newItem.getId() ){
		  newItem.setQualntity( quantity + newItem.getQualntity());
		  msg = updateItem(session, newItem);
          itemExist = true;
		}
	}
	rs.close();
	
	//if the newitem is not exist in the system, Add the newitem to system.
    if ( ! itemExist ) {        
        dbManagerobj.insertItemImp(newItem.getId(), newItem.getType(), newItem.getDescription(), newItem.getPrice(), newItem.getQualntity());		
        msg = "The item (" + newItem.getId()+ " ) Add succesfully to the system; Username: " + session.getUsername() ;        
       }
    System.out.println("\n" + msg);        	   
    }
	catch(SQLException se){
        se.printStackTrace();} 
    return msg; 
}

/*
* Browsing the Shopping Cart Items
*/
public synchronized String browseItemsSC (Session session)
 { 
    String sql, existingItems;	
	existingItems = "\tThe Existing Products \n"+
	"ID\tQuantity \n";
	try{
	ResultSet rs = dbManagerobj.shoppingCSelectImp(session.getUsername());    	
    //Extract data from result set
    while(rs.next()){
        //Retrieve by column name  
        int id  = rs.getInt("itemID");
		int quantity  = rs.getInt("Quantity");        
		existingItems += id + "\t" + quantity +"\n";        		
	}
	rs.close();  }
    catch(SQLException se){
        se.printStackTrace();} 
    return existingItems ;	
 } 

 /*
* Adding Item to Shopping Cart 
*/
public synchronized String addItemSC (Session session, int itemId, int itemQuantity)
 {
    System.out.println(browseItems(session));
    String msg = "";
	boolean existing = false, checkQuantity = true, Sys = false;
	int sysQuantity =0;
	try{
	ResultSet rs = dbManagerobj.itemSelectImpl();
    //Extract data from result set
	if (rs.next()) {
      do {
        //Retrieve by column name  
        int id  = rs.getInt("ID");
		int quantity  = rs.getInt("Quantity");      
		
        if(id == itemId &&  itemQuantity <= quantity)
		    {Sys = true;     sysQuantity = quantity;}
		} while(rs.next());}
		rs.close();
	if (Sys)
		{   
		    ResultSet rscs = dbManagerobj.shoppingCSelectImp(session.getUsername());			
            if (rscs.next()) {
			  do {
                //Retrieve by column name  
                int idcs  = rscs.getInt("itemID");
		        int quantitycs = rscs.getInt("Quantity");
				if(idcs == itemId){
				   int newQuantity = quantitycs + itemQuantity;
                   if(newQuantity <= sysQuantity){
				        dbManagerobj.updateSCQuantityImpl(newQuantity, idcs, session.getUsername());
						msg = "The item is added sucessfully";						
					}
					else 
					   checkQuantity = false;
					existing = true;				
				}
                }while(rscs.next());	
				}
            else {
			    dbManagerobj.insertSCImpl(itemId, session.getUsername(), itemQuantity);
		        msg = "The item is added sucessfully";
				existing = true; 
	        }			
			rscs.close(); Sys = true;            			
       	}
	else 
      	msg = "Either the items is not in System or the qantity is not exist";	    
    
	if ((!existing )&& Sys){ 
        dbManagerobj.insertSCImpl(itemId, session.getUsername(), itemQuantity);
		msg = "The item is added sucessfully";    
    }
    if(!checkQuantity)
      msg = "The Quantity is larger or smaller than the availability ;; Username:::"+ session.getUsername();
	System.out.println(msg); 
    	}
	catch(SQLException se){
        se.printStackTrace();} 
    return msg; 
}

/*
 * Removing the Item  from the Shopping Cart 
 */
 public synchronized void removeItemSC (Session session, int itemId )
 {
   try{
	String msg = "Either the item is not exist in the shopping cart OR you enter wrong Id value!!";
	ResultSet rscs = dbManagerobj.shoppingCSelectImp(session.getUsername());
    
    while(rscs.next()){
       //Retrieve by column name  
        int idsc  = rscs.getInt("itemID");
	    if(idsc == itemId){
		    dbManagerobj.deleteSCImp(idsc, session.getUsername());		
			msg = "Item is removing Successfully from the shopping cart ...";	
		}
	}
	rscs.close();
	System.out.println(msg);
    }
    catch(SQLException se){
        se.printStackTrace();} 
}

/*
* Purching items from Shopping Cart
*/
public synchronized String purchingItems (Session session, int itemId, int itemQuantity) 
 {
    System.out.println(browseItemsSC(session));	
	String msg  = "";
	try{
	int sysItemQuantity = 0;	
	//Extract the Quantity of the item, which the customer want to buy it from the system.	
	ResultSet rs = dbManagerobj.itemSelectImpl();
	
	while(rs.next()){
	   int id  = rs.getInt("ID");
       if(id == itemId) 
        sysItemQuantity  = rs.getInt("Quantity");
	}
	rs.close();
	
	//Starting the purchase process
	ResultSet rscs = dbManagerobj.shoppingCSelectImp(session.getUsername());	
	if (rscs.next()) {
     do {
	    int idcs  = rscs.getInt("itemID");
		int quantitycs = rscs.getInt("Quantity");
		if ( idcs == itemId && itemQuantity <= sysItemQuantity){
            reduceSysItemQuantity(session, itemId,itemQuantity);
            //modify the quantity of item in the SC
            int newQuantitySC = quantitycs - itemQuantity;
			if(newQuantitySC == 0){
			    dbManagerobj.deleteSCImp(idcs, session.getUsername());				
				}
            else {
			    dbManagerobj.updateSCQuantityImpl(newQuantitySC, idcs, session.getUsername());
				}	
        msg = "Thank you for your shopping item "+ itemId + " Username::" + session.getUsername();
		}        		
	    }while(rscs.next());
    }		
	else
	   msg = "Your shopping cart is empty nothing to buy item;; Username::" + session.getUsername();
	rscs.close();
    System.out.println(msg);}
    catch(SQLException se){
        se.printStackTrace();} 
    return msg;    
}

/*
* Modify the quantity of System items
*/
private void reduceSysItemQuantity(Session session, int itemId, int itemQuantity)
{
    try{
	ResultSet rscs = dbManagerobj.itemSelectImpl();
	
	if (rscs.next()) {
     do {
	    int id = rscs.getInt("ID");
		int quantitycs = rscs.getInt("Quantity");
		if ( id == itemId){
		  if( (quantitycs - itemQuantity) == 0) 
		    removeItem (session,itemId);
		  else {	
            dbManagerobj.updateItemQuantityImpl((quantitycs - itemQuantity),id);
			}		
	 }}while(rscs.next());}
	 }
	 catch(SQLException se){
        se.printStackTrace();} 
}
}