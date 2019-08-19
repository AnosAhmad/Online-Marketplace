import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.Future;
import java.lang.reflect.*;
import java.sql.*;

/*
* Database Manager: The methods of this class are public methods so to 
* keep them thread-safe we have to invoke any method using only the OnlineMarketplaceModel
* instance. In this class I can't apply the Thread-safe Interface pattern. 
*/

public class BDManager  {
/*
* Private Memebers
*/
private ExecutorService threadpool = Executors.newFixedThreadPool(1);
private Future<Connection>  conn = null;
private Future f; 

public BDManager(){
  f = threadpool.submit(new ConnectionFuture());
  fillUpItems();
  fillUpUsersInfo();}

/* 
* Fillup the information for the avilable Items in the system
*/
private void fillUpItems(){
    try{
	Connection c = (Connection)f.get();
    String sql = "DELETE FROM item";
	PreparedStatement preparedStmt = c.prepareStatement(sql);
    preparedStmt.executeUpdate();
    sql= "DELETE FROM ShoppingCart";
	preparedStmt = c.prepareStatement(sql);
    preparedStmt.executeUpdate();
     
	for( int i = 1; i <= 5; i++)
        insertItemImp(i, "Goods", "Pen", (i*5), (i*10));
    }
	catch(SQLException se){
        se.printStackTrace();}  
	catch(Exception e){
      System.out.print("Handle errors for Class.forName: ");
	  e.printStackTrace();}
	}
	
/* 
* Fillup the username and passphrase for some users
*/
private void fillUpUsersInfo()
{
    try{
	Connection c = (Connection)f.get();
    String sql , role;
    sql	= "DELETE FROM Users";
	PreparedStatement preparedStmt = c.prepareStatement(sql);
    preparedStmt.executeUpdate();
    
	for( int i = 1; i <= 8; i++)
    {   
	    if( i%2 == 0)  role = "Customer";
		else role = "Admin";
	    insertUsersImp( ("A" + i),  "1234",  role);
    }}
   catch(SQLException se){
        se.printStackTrace();}  
    catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}
}

/*
* Insert records to the Users table
*/
public void insertUsersImp(String userName, String passWord, String role)
{
  try{
  Connection c = (Connection)f.get();
  String sql = "INSERT INTO Users VALUES ( ?, ? ,? )"; 
  PreparedStatement preparedStmt = c.prepareStatement(sql);
  preparedStmt.setString(1,userName);
  preparedStmt.setString(2,passWord);
  preparedStmt.setString(3,role);
  preparedStmt.executeUpdate();}
  catch(SQLException se){
    se.printStackTrace();} 
  catch (Exception e){
	System.out.println("Exception: " + e.getMessage() );
	e.printStackTrace();		}	
}

/*
* Insert records to the item table
*/
public void insertItemImp(int Id, String itemType, String Description, float price, int quantity)
{
    try{
	Connection c = (Connection)f.get();
	String sql = "INSERT INTO item VALUES ( ?, ?, ?, ?,?)";
	PreparedStatement preparedStmt = c.prepareStatement(sql);
    preparedStmt.setInt(1, Id );
    preparedStmt.setString(2,itemType);
    preparedStmt.setString(3,Description);
    preparedStmt.setFloat(4,price);
	preparedStmt.setInt(5, quantity);
	preparedStmt.executeUpdate();}
    catch(SQLException se){
        se.printStackTrace();} 
    catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}		
}

/*
* Insert records to the ShoppingCart table
*/
public void insertSCImpl(int Id, String username, int quantity)
{
   try{
    Connection c = (Connection)f.get();
    String  sql = "INSERT INTO ShoppingCart VALUES (?, ?, ?)";
	PreparedStatement preparedStmt = c.prepareStatement(sql);
    preparedStmt.setString(1, username);
	preparedStmt.setInt(2, Id);
    preparedStmt.setInt(3, quantity);
	preparedStmt.executeUpdate(); }
	catch(SQLException se){
        se.printStackTrace();}
    catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}		
}

/* 
* Delete records from the Shopping Cart table
*/
public void deleteSCImp(int idsc, String username)
{
  try{
   Connection c = (Connection)f.get();
   String sql = "DELETE FROM ShoppingCart WHERE itemID = ? and username LIKE ?";
   PreparedStatement preparedStmt = c.prepareStatement(sql);
   preparedStmt.setInt(1, idsc);
   preparedStmt.setString(2, username);
   preparedStmt.executeUpdate();}
  catch(SQLException se){
        se.printStackTrace();}
   catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}		
}   

/* 
* Delete records from the item table 
*/
public void deleteItemImpl(int itemId)
{
  try{
   Connection c = (Connection)f.get();
   String sql = "DELETE FROM item WHERE ID = ?";
   PreparedStatement preparedStmt = c.prepareStatement(sql);
   preparedStmt.setInt(1,itemId);
   preparedStmt.executeUpdate();}
   catch(SQLException se){
        se.printStackTrace();}
catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}		
}   

/* 
* Delete records from the Users table 
*/
public void deleteUsersImpl(String UserName)
{
  try{
   Connection c = (Connection)f.get();
   String sql = "DELETE FROM Users Where username LIKE ?";
   PreparedStatement preparedStmt = c.prepareStatement(sql);
   preparedStmt.setString(1,UserName);	
   preparedStmt.executeUpdate(); }
  catch(SQLException se){
    se.printStackTrace();} 
catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}    
}   

/*
* Update Item Description
*/
public void updateItemDescriptionImp(String Description, int id)
{  
   try{
    Connection c = (Connection)f.get();
    String sql = "UPDATE item SET Description = ? WHERE ID = ?";
	PreparedStatement preparedStmt = c.prepareStatement(sql);
	preparedStmt.setString(1,Description);
	preparedStmt.setInt(2,id);
	preparedStmt.executeUpdate();}
   catch(SQLException se){
        se.printStackTrace();} 
catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}		
}

/*
* Update Item Type
*/
public void updateItemTypeImp(String itemType, int id)
{  
   try{
    Connection c = (Connection)f.get();
    String sql = "UPDATE item SET itemType = ? WHERE ID = ?" ;
	PreparedStatement preparedStmt = c.prepareStatement(sql);
	preparedStmt.setString(1,itemType);
	preparedStmt.setInt(2,id);
	preparedStmt.executeUpdate(); }
   catch(SQLException se){
        se.printStackTrace();}
catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}		
}

/*
* Update Item Price
*/
public void updateItemPriceImpl(float Price, int id)
{  
   try{
    Connection c = (Connection)f.get();
    String sql = "UPDATE item SET Price = ? WHERE ID = ?" ;
	PreparedStatement preparedStmt = c.prepareStatement(sql);
	preparedStmt.setFloat(1,Price);
	preparedStmt.setInt(2,id);
	preparedStmt.executeUpdate(); }
	catch(SQLException se){
        se.printStackTrace();}  
		catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}
}

/*
* Update Item Quantity
*/
public void updateItemQuantityImpl(int quantity, int id)
{  
    try{
	Connection c = (Connection)f.get();
	String sql = "UPDATE item SET Quantity = ? WHERE ID = ?";
	PreparedStatement preparedStmt = c.prepareStatement(sql);
	preparedStmt.setInt(1, quantity);
	preparedStmt.setInt(2,id);
	preparedStmt.executeUpdate();}
    catch(SQLException se){
        se.printStackTrace();} 
catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}		
}

/*
* Update Shooping Cart
*/
public void updateSCQuantityImpl(int quantity, int id, String username)
{  
    try{
	Connection c = (Connection)f.get();
	String sql = "UPDATE ShoppingCart SET Quantity = ? WHERE itemID = ? AND username LIKE ?";
    PreparedStatement preparedStmt = c.prepareStatement(sql);
	preparedStmt.setInt(1, quantity);
    preparedStmt.setInt(2, id);
	preparedStmt.setString(3, username);
	preparedStmt.executeUpdate();}
    catch(SQLException se){
        se.printStackTrace();}  	
		catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}
 }

/*
* Retrive the user informations
*/
public ResultSet usersSelectImpl()
{
    ResultSet rs = null;
    try{
	Connection c = (Connection)f.get();
    String sql ;   
    sql = "SELECT * FROM Users";
	//System.out.println("Select Users");
	PreparedStatement preparedStmt = c.prepareStatement(sql);
	rs = preparedStmt.executeQuery();}
	catch(SQLException se){
        se.printStackTrace();}  
		catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}
    return rs;	
}
 
/*
* Retrive the item informations
*/
public ResultSet itemSelectImpl()
{
   ResultSet rs = null;
   try{
    Connection c = (Connection)f.get();
    String sql ;   
    sql = "SELECT * FROM item";
	PreparedStatement preparedStmt = c.prepareStatement(sql);
	rs = preparedStmt.executeQuery();
    }
	catch(SQLException se){
        se.printStackTrace();}  
		catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}
	return rs;	
}

/*
* Retrive the Shopping Cart informations
*/
public ResultSet shoppingCSelectImp(String username)
{
    ResultSet rs = null;
    try{
	Connection c = (Connection)f.get();
	String sql ;   
    sql = "SELECT * FROM ShoppingCart WHERE username LIKE ?";
	PreparedStatement preparedStmt = c.prepareStatement(sql);
	preparedStmt.setString(1,username);
	rs = preparedStmt.executeQuery();}
	catch(SQLException se){
        se.printStackTrace();}  
		catch (Exception e){
			System.out.println("Exception: " + e.getMessage() );
			e.printStackTrace();		}
    return rs;	
}
}