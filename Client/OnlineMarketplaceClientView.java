import java.util.Scanner;
import java.io.*;
import java.util.*;

/*
* Client Side - Login View
*/

public class OnlineMarketplaceClientView 
 {  
    // Login View method 
    public String view (){    
        try{	
			Scanner in = new Scanner(System.in);
			String msg = "";
			String userName, password, exit;			
			System.out.println("please enter the username and password");
		    System.out.println("UserName:");
            userName = in.nextLine();
		    System.out.println("Password:");
            password = in.nextLine();
		    msg = userName + "," +password;	  
			return msg;	
		} catch(Exception e){
			System.out.println("Client View Exception: " +
			e.getMessage());
			e.printStackTrace();
		}
	return "";
	}
	
	//Display message for the user when the username and password are wrong
	
	public void printWronginput()
	{
        System.out.println("\f");
	    System.out.println("The Username is not exist, you have to signup");
	}
}