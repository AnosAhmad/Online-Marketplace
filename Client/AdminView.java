/* 
* Administrator View
*/

import java.io.*;
import java.util.*;
import java.text.*;


public class AdminView
{ 
  private AdminViewController adminViewController;
  
  /* Constructor*/
  public AdminView(AdminViewController adminViewController)  {  
    this.adminViewController = adminViewController;
    }
  
  public void Welcome(String adminName)
  {
    try{
    System.out.println("\tWelcome (" + adminName + ") to Administrator View!!!!\n");
    Scanner in = new Scanner(System.in);
    String choice = "", role;
    String done, userName, password, updatedItem, modifiedItem, itemType, itemDescription, itemprice;
    int idex, itemId, itemQuantity, itemNumbers, itemPrice;
    
    while (choice != "7") {
			 System.out.println("You can do the following : ");
			 System.out.println("1. SignUp Account");
			 System.out.println("2. Remove Account");
             System.out.println("3. Browse system products");
			 System.out.println("4. Update system product Information");
			 System.out.println("5. Remove Product from the System");
             System.out.println("6. Adding product to the System"); 
			 System.out.println("7. Exit");
			 System.out.println("\nEnter your choice:");
			 choice = in.nextLine();
			 switch (choice) {
			 case "1":						
				System.out.println("UserName:");
                userName = in.nextLine();
				System.out.println("Password:");
                password = in.nextLine();
                System.out.println("Enter the Role value either Admin or Customer:");
                role = in.nextLine();
	            System.out.println("\n" + adminViewController.signUp(userName, password,role, getCurrentTime()) + "\n");	
				break;
				
			 case "2":
				System.out.println("UserName:");
                userName = in.nextLine();
				System.out.println("\n" + adminViewController.removeAccount(userName, getCurrentTime()) +"\n");				
				break;
                          
             case "3":
			    System.out.println("\n"+ adminViewController.browseItems(getCurrentTime()) + "\n");
				System.out.println();
                break;
				
			 case "4":
                System.out.println(adminViewController.browseItems(getCurrentTime()) + "\n");
                System.out.println("\n");
				System.out.println("Enter how many Items/Products you want to update:");
				itemNumbers = Integer.parseInt( in.nextLine());
				idex = 1;
				while (idex <= itemNumbers)
				{
				  System.out.print("Please enter the updated information in this format:\n");
		          System.out.println("ID value, description value, type value, price value, quantity value");
				  modifiedItem = in.nextLine();
                  System.out.println(adminViewController.updateItem(modifiedItem, getCurrentTime()));
				  idex += 1;
				}
				break;
            	
			 case "5":
			    System.out.println(adminViewController.browseItems(getCurrentTime()) + "\n");
				System.out.println();
				System.out.println("Enter The Id of the Product/Item:");
				itemId = Integer.parseInt( in.nextLine());
				adminViewController.removeItem(itemId, getCurrentTime());				
				break;

             case "6":
                 		System.out.println("Enter the number of Items you wanna to add them:");
				itemNumbers = Integer.parseInt( in.nextLine());
				idex = 1;
				while (idex <= itemNumbers)
				{
				  System.out.println("Enter the " + idex + " item product/Item Id:");
				  itemId = Integer.parseInt( in.nextLine());
				  System.out.println("Enter the " + idex  + " item Quantity:");
				  itemQuantity = Integer.parseInt( in.nextLine());
                                  System.out.println("Enter the " + idex  + " item Description:");
				  itemDescription =  in.nextLine();
                                  System.out.println("Enter the " + idex  + " item Type:");
				  itemType=  in.nextLine();
                                  System.out.println("Enter the " + idex  + " item Price:");
				  itemprice =  in.nextLine();

				  //Create the item
          		Item I1 = new Item();
                I1.setQualntity(itemQuantity );
                I1.setPrice(Float.parseFloat(itemprice ));
                I1.setType(itemType);
                I1.setDescription(itemDescription );
                I1.setId(itemId );
	            System.out.println("\n" + adminViewController.addItemSys(I1, getCurrentTime()));
				  idex += 1;
				}
   						
				break;
			    
			 case "7":
				System.out.println("Exited");
				System.exit(0);
				break;			
				
			 default:
				System.out.println("Invalid choice entered. Please enter between 1-5");
				break;
			} 
		} 
    } catch(Exception e){
			System.out.println("OnlineMarketplace Exception: " + e.getMessage());
			}
} 

/*
* Determine when the Admin request will send to the server side
* this method is used in order to produce concurrent requests
*/
public Calendar getCurrentTime() { 
Calendar calendar = Calendar.getInstance();
calendar.add(Calendar.SECOND, 60);
calendar.set(Calendar.SECOND, 0);
calendar.set(Calendar.MILLISECOND, 0);
return calendar;
}     
}



  
	 