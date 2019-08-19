import java.util.Scanner;
import java.io.*;
import java.util.*;

/*
* Client Side -- FrontController  
*/

public class OnlineMarketplaceFrontController 
 {
    // Data Members
    private OnlineMarketplace myMarket;
    private Dispatcher dispatcher;
    private OnlineMarketplaceClientView loginView ;
    private Session session;
	   
	//Constructor
    public OnlineMarketplaceFrontController(OnlineMarketplace myMarket) 
    {
	    try {
		    this.myMarket = myMarket;
        	    loginView = new OnlineMarketplaceClientView();
		    dispatcher = new Dispatcher();
                    dispatcher.setMyMarket( myMarket); 
        
            //Collect the data from the login view
		    this.isAuthorized();
		    } 
		catch(Exception e){
			System.out.println("OnlineMarketplaceClientController Exception: " +
			e.getMessage());
			e.printStackTrace();
		}
	}
    
	//Check the authorized
	public void isAuthorized()
	{
	   try{
            String info = loginView.view();
            String[] userInfor = info.split(",");
	    session = myMarket.signIn(userInfor[0], userInfor[1]);
            System.out.println("null");
  
	       // Check the authorization
	       if(session == null)
	       { 	
                loginView.printWronginput();		
		   }
	       else
		   { 
		        dispatcher.dispatch(session);		   
		   }
        }
        catch(Exception e){
			System.out.println("OnlineMarketplaceFrontController Exception: " +
			e.getMessage());
e.printStackTrace();

			
		}          
    }
}