
/* 
* Administrator View Controller
*/

import java.io.*;
import java.util.*;
import java.text.*;

public class AdminViewController
{ 
  /*Private Data Members*/
  private AdminView adminView;
  private Session session;
  private OnlineMarketplaceClientController onlineMarketplaceClientController;

  
  /* Constructors*/
  public AdminViewController(Session session, OnlineMarketplace myMarket ) 
  {  
    onlineMarketplaceClientController = new OnlineMarketplaceClientController();
    this.session = session;
    System.out.println("\f");
    adminView  = new AdminView(this); 
    adminView.Welcome(session.getUsername());
  } 
  
  /* Method to call SignUp method*/
  public String signUp(String userName, String password, String role, Calendar calendar)
  {
    try{
       return onlineMarketplaceClientController.signUp(userName, password,role, session, calendar);     
	}
	catch(Exception e) 
	{ return(e.getMessage());}
  }
  
  /* Method to call removeAccount method */
  public String removeAccount(String userName, Calendar calendar)
  {
    try{
        return onlineMarketplaceClientController.removeAccount(userName, session, calendar);
	}
	catch(Exception e) 
	{ return(e.getMessage());}
  }
  
  /* Method to call updateItem method */
  public String updateItem(String modifiedItem, Calendar calendar)
  {
    try{
    //Create new itemUpdated.
    String[] itemInfor = modifiedItem.split(",");
    Item itemUpdated= new Item();
    itemUpdated.setId(Integer.parseInt(itemInfor[0]));
    itemUpdated.setDescription(itemInfor[1]);
    itemUpdated.setType(itemInfor[2]);
    itemUpdated.setPrice(Integer.parseInt(itemInfor[3]));
    itemUpdated.setQualntity(Integer.parseInt(itemInfor[4]));     
     return onlineMarketplaceClientController.updateItem(itemUpdated , session, calendar);
    }
	catch(Exception e) 
	{ return(e.getMessage());}
  }

  /* Method to call browseItems method */
  public String browseItems(Calendar calendar)
  { 
    try{
   // return myMarket.browseItems(session);
   return onlineMarketplaceClientController.browseItems(session, calendar);
	}
	catch(Exception e) 
	{ return(e.getMessage());}
  }
  
  /* Method to call addItemSys method */
  public String addItemSys(Item newItem, Calendar calendar)
  {
    try{
   // return myMarket.addItemSys(session, newItem);
    return onlineMarketplaceClientController.addItemSys(newItem, calendar, session,calendar );
	}
	catch(Exception e) 
	{ return(e.getMessage());}
  }
  
  /* Method to call removeItem method */
  public void removeItem( int itemId, Calendar calendar)
  {
    try{
	//myMarket.removeItem (session, itemId); 
    onlineMarketplaceClientController.removeItem( itemId , session, calendar);
	}
	catch(Exception e) 
	{ System.out.println(e.getMessage());}
  }
  }