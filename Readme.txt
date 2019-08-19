Name: Enas Alikhashashneh
Object-Oriented Design and Programming 
Assignment5
________________________

Executing the program (Commands):
________________________

1. Unzip the folder and place the contents into your directory on Pegasus or Tesla. Open an instance of putty.

2. Run the RMI Registry in the background - this will run at the 2222 port:
   % rmiregistry 2222 &  
   
4. Run the OnlinemarketplaceServer by executing the following commands:
   % cd Server
   % rm *.class
   % make
   % rmiregistry 2222 &
   % java -cp .:mysql-connector-java-5.0.8-bin.jar:.~/Server/mysql-connector-java-5.0.8-bin.jar -Djava.security.policy=policy OnlineMarketplaceServer "//10.234.136.55:2222/Server/OnlineMarketplaceApplicationController"

Note: you can replace the IP Address of the server to be any Assignment machines' IP Address, because the ip addresss of server is determined
as user input. 

5. Open a second instance of putty.
   
6. Run the OnlinemarketplaceClient by executing the following commands:
   % cd Client
   % rm *.class
   % make
   % java -Djava.security.policy=policy OnlineMarketplaceClientController "//in-csci-rrpc01.cs.iupui.edu:2222/Server/OnlineMarketplaceApplicationController"
   
7. Login view will be displayed, enter the Username and Password and press Enter
   (e.g. Username: A1
         Password: 1234) this is administrator account
   (e.g. Username: A10
         Password: 4321) this is customer account

8. Type "6" to exit the View.

9. If you complete please remember to clean up by terminating the RMI Registry through the following command:
   % fg
   At which point you can kill the process. 
_________________________________

Files:
The following files are separated into two folders ( Server and Client):

1. OnlineMarketplace.java

This file represents the remote interface, which contains server related methods implemented by OnlinemarketplaceServerI.java

2. OnlineMarketplaceServer.java

This file represents the server side. Also contains main function 

3. OnlineMarketplaceApplicationController.java

This file represent the controller layer in the server side; which is controll the access into the model layer.

4. OnlineMarketplaceModel.java

This file represents the model layer. it contains implementation of the methods, which are listed in the OnlineMarketplace interface.
     
5. Session.java

This file represents the Session interface, which contains server related methods, which the client can use them,
implemented by SessionImpl.java

6. SessionImpl.java

This file creates the session for the client. it contains implementation of the methods, which are listed in the Session interface.

7. item.java

This file represent the information of the products that will be selled through the onlinemarketplace application.

8. Users.java

This file represent the information of the Users that will use the onlinemarketplace application.

9. OnlineMarketplaceClientController.java

This file remotely invokes the methods, which are declared on the OnlineMarketplace interface in order to get the return values; thus this 
file connect with the OnlineMarketplaceApplicationController in the server side.

10. OnlineMarketplaceClientView.java

This file represents the Login view in the client side, where the users need to enter the username and password in order to get the authorization.

11. OnlineMarketplaceFrontController.java

This file check if the users have authorization to the application through connect to the OnlineMarketClientController and delegate the resposability
to decide which view must be shown to the Dispatcher.

12. Dispatcher.java

This file reciving the request from the OnlineMarketplaceFrontController in order to check the associated role for the Session object and show up the
respective view.

13. AdminViewController.java 

This file connect with the OnlineMarketplaceClientController and the AdminView in order to perform the specific action or functionality for the Administrator.

14. AdminView.java

This file represents the Administrator view.

15. CustViewController.java 

This file connect with the OnlineMarketplaceClientController and the CustView in order to perform the specific action or functionality for the Customer.

16. AdminView.java

This file represents the Customer view.

17. AuthorizationInvocationHandler

This file is used at runtime in order to check the role of the client, who invoke the method, is same to the role that specificed in the RMI interface.  

18. RequiresRole 

This file is used in order to specifies the roles that the application procides it for the clients.

19. AuthorizationException

This file represent a custom exception, which is build in order to arise message when unvalid access to remote object methods is happen.

20.policy

this file contains access/permission information. you need not edit this file

21. Makefile

to compile all files.

22. A4_Report.docx

Assignment#4 report documentation.