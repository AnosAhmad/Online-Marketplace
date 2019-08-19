import java.io.*;
import java.util.*;
import java.text.*;

/* 
* Customer View
*/

public class CustView
{ 
  private CustViewController custViewController;
  
  /* Constructor*/
  public CustView(CustViewController custViewController)
  {
     this.custViewController = custViewController;      
  }
  
  public void Welcome(String customerName )
  {
    try{
            System.out.println("welcome (" + customerName + ") to Customer View!!!\n");
            Scanner in = new Scanner(System.in);
			String choice = "", role;
			String done, userName, password, updatedItem, modifiedItem;
			String CurrentTime;
			int idex, itemId, itemQuantity, itemNumbers;
		    while (choice != "7") {
			 System.out.println("\nYou can do the following :\n ");
			 System.out.println("1. Browse/Add Availabe Products");
			 System.out.println("2. Removing Product from Shopping Cart");
			 System.out.println("3. Browsing Product in the Shopping Cart");
			 System.out.println("4. Purshing Products from Shopping Cart");
			 System.out.println("5. Exit");
			 System.out.println("\nEnter your choice:");
			 choice = in.nextLine();
			 switch (choice) {
			 case "1":
    			System.out.println(custViewController.browseItems(getCurrentTime()));
				System.out.println("If you want to add Item to the shopping cart,\nplease enter the number of Items,\nelse please enter zero:");
				itemNumbers = Integer.parseInt( in.nextLine());
				idex = 1;
				while (idex <= itemNumbers)
				{
				  System.out.println("Enter the " + idex + " Item Id:");
				  itemId = Integer.parseInt( in.nextLine());
				  System.out.println("Enter the " + idex  + " item Quantity:");
				  itemQuantity = Integer.parseInt( in.nextLine());
				  System.out.println("\n" + custViewController.addItem(itemId, itemQuantity, getCurrentTime())); 
				  idex += 1;
				}
                break;

			 case "2":
     			System.out.println(custViewController.browseItemsSC(getCurrentTime()));				
				System.out.println();
				System.out.println("Enter The Id of the Product/Item:");
				itemId = Integer.parseInt( in.nextLine());
				custViewController.removeItemSC(itemId, getCurrentTime());
				break;
             
			 case "3": 
				System.out.println(custViewController.browseItemsSC(getCurrentTime()));
				System.out.println();
				break;
            
			 case "4":
System.out.println(custViewController.browseItemsSC(getCurrentTime()));
				System.out.println(); 
                System.out.println("Enter The ID of the Product/Item:");
				itemId = Integer.parseInt( in.nextLine());
                System.out.println("Enter The quantity of the Product/Item:");
				itemQuantity = Integer.parseInt( in.nextLine());
				System.out.println("\n" + custViewController.purchingItems(itemId ,itemQuantity , getCurrentTime()));				
				break;
             
			 case "5":
				System.out.println("Exited");
				System.exit(0);
				break;			
				
			 default:
				System.out.println("Invalid choice entered. Please enter between 1-6");
				break;

			} // switch case end
		} // while end
			
		} catch(Exception e){
			System.out.println("OnlineMarketplaceClient Exception: " +
			e.getMessage());
			e.printStackTrace();
		}
  }

/*
* Determine when the Customer request will send to the server 
* side this method is used in order to produce concurrent requests
*/
public Calendar getCurrentTime() { 
  Calendar calendar = Calendar.getInstance();
  calendar.add(Calendar.SECOND, 60);
  calendar.set(Calendar.SECOND, 0);
  calendar.set(Calendar.MILLISECOND, 0);
  return calendar;
}  
}