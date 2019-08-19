import java.io.*;
import java.util.*;
import java.rmi.*;
import java.util.concurrent.*;
import java.lang.reflect.*;

/*
* Server Side
*/
 
public class OnlineMarketplaceServer 
{ 
 private static OnlineMarketplaceApplicationController obj;
   
 public OnlineMarketplaceServer() 
 {  
    try{   
    obj = new OnlineMarketplaceApplicationController();	
    }
    catch (Exception e){
		System.out.println("Exception: " + e.getMessage());
	}
 }
 
 public static void main(String[] args)
 {  try{
	    new OnlineMarketplaceServer();               
	    String name  = (args.length < 1) ? null : args[0];
	    obj.binding(name);
	}
    catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
	} 
 System.out.println("server starting");
 }
}