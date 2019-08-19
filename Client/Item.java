/*
* Class Item to store the information
*/

public class Item implements java.io.Serializable
{
 private int id;
 private String description;
 private String type;
 private float price;
 private int qualntity;
 
 // set and get funtion for item id
 public void setId ( int idValue) 
 { 
   if (idValue > 0 ) {id = idValue;} 
   else {System.out.println(" Please enter integer value for Item Id > 0 ");}
 }
 
 public int getId () 
 { 
   return id;
 }
 
 // set and get funtion for item description
 public void setDescription ( String descriptionValue) 
 { 
   if (descriptionValue != "" ) {description = descriptionValue;} 
   else {System.out.println(" Please enter item description: ");}
 }
 
 public String getDescription () 
 { 
   return description;
 }
 
 // set and get funtion for item type
 public void setType ( String typeValue) 
 { 
   if (typeValue != "" ) {type = typeValue;} 
   else {System.out.println(" Please enter item type: ");}
 }
 
 public String getType () 
 { 
   return type;
 }
 
 // set and get funtion for item price
 public void setPrice ( float priceValue) 
 { 
   if (priceValue > 0.0 ) {price = priceValue;} 
   else {System.out.println(" Please enter item price: ");}
 }
 
 public float getPrice () 
 { 
   return price;
 }
 
 // set and get funtion for item quantity
 public void setQualntity ( int qualntityValue) 
 { 
   if (qualntityValue >= 0 ) {qualntity = qualntityValue;} 
   else {System.out.println(" Please enter integer value for item quantity >= 0 ");}
 }
 
 public int getQualntity () 
 { 
   return qualntity;
 }
 }