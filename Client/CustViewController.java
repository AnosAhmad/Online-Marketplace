import java.io.*;
import java.text.*;
import java.sql.Timestamp;
import java.util.*;
/* 
* Customer View Controller
*/

public class CustViewController
{ 
  /*Private Data Members*/
  private CustView custView;
  private Session session;
  //private OnlineMarketplace myMarket;
  private OnlineMarketplaceClientController onlineMarketplaceClientController;

  
  /* Constructor*/
  public CustViewController(Session session, OnlineMarketplace myMarket) 
  { 
    this.session = session;
   // this.myMarket = myMarket;
    onlineMarketplaceClientController = new OnlineMarketplaceClientController();
    //Show up the Customer View
    System.out.println("\f");
    custView  = new CustView(this); 
    custView.Welcome(session.getUsername());
  }


/* Method to call browseItems method */
public String browseItems(Calendar custExecutionTime)
{
    try {  
	return onlineMarketplaceClientController.browseItems(session, custExecutionTime);}
	catch(Exception e) 	{ return e.getMessage();}
}

/* Method to call addItem to SC method */
public String addItem( int itemId,  int itemQuantity, Calendar custExecutionTime)
{
   try {
   return onlineMarketplaceClientController.addItem( session, itemId, itemQuantity, custExecutionTime);}
   catch(Exception e) 	{ return e.getMessage();}
}

/* Method to call browseItemsSC method */
public String browseItemsSC( Calendar custExecutionTime)
{
   try { return onlineMarketplaceClientController.browseItemsSC(session, custExecutionTime); }
   catch(Exception e) 	{ return e.getMessage();}
}

/* Method to call removeItemSC method */
public void removeItemSC(int itemId, Calendar custExecutionTime)
{
   try { onlineMarketplaceClientController.removeItemSC(session, itemId, custExecutionTime); }
   catch(Exception e) 	{ System.out.println(e.getMessage());}
}

/* Method to call purchingItems method */
public String purchingItems(int itemId, int itemQuantity, Calendar custExecutionTime)
{
    try { return onlineMarketplaceClientController.purchingItems(session, itemId, itemQuantity, custExecutionTime);}
    catch(Exception e) 	{ return e.getMessage();}
}
}