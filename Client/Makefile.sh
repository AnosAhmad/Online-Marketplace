JCC = javac -cp .:mysql-connector-java-5.0.8-bin.jar:.~/Server/mysql-connector-java-5.0.8-bin.jar
default: Item.class AuthorizationInvocationHandler.class AuthorizationException.class SessionImpl.class RequiresRole.class OnlineMarketplace.class OnlineMarketplaceClientController.class CustView.class CustViewController.class AdminView.class AdminViewController.class Dispatcher.class OnlineMarketplaceFrontController.class OnlineMarketplaceClientController.class OnlineMarketplaceClientView.class Session.class 
Item.class: Item.java
	$(JCC) Item.java
AuthorizationInvocationHandler.class: AuthorizationInvocationHandler.java
	$(JCC) AuthorizationInvocationHandler.java
AuthorizationException.class: AuthorizationException.java
	$(JCC) AuthorizationException.java
Session.class: Session.java
	$(JCC) Session.java
SessionImpl.class: SessionImpl.java
	$(JCC) SessionImpl.java
RequiresRole.class: RequiresRole.java
	$(JCC) RequiresRole.java
OnlineMarketplace.class: OnlineMarketplace.java
	$(JCC) OnlineMarketplace.java
OnlineMarketplaceClientView.class: OnlineMarketplaceClientView.java
	$(JCC) OnlineMarketplaceClientView.java
OnlineMarketplaceClientController.class: OnlineMarketplaceClientController.java
	$(JCC) OnlineMarketplaceClientController.java
OnlineMarketplaceFrontController.class: OnlineMarketplaceFrontController.java
	$(JCC) OnlineMarketplaceFrontController.java
Dispatcher.class: Dispatcher.java
	$(JCC) Dispatcher.java
AdminViewController.class: AdminViewController.java
	$(JCC) AdminViewController.java
AdminView.class: AdminView.java
	$(JCC) AdminView.java 
CustViewController.class: CustViewController.java
	$(JCC) CustViewController.java
CustView.class: CustView.java
	$(JCC) CustView.java
run: Item.class AuthorizationInvocationHandler.class AuthorizationException.class SessionImpl.class RequiresRole.class OnlineMarketplace.class OnlineMarketplaceClientController.class CustView.class CustViewController.class AdminView.class AdminViewController.class Dispatcher.class OnlineMarketplaceFrontController.class OnlineMarketplaceClientController.class OnlineMarketplaceClientView.class Session.class
clean:
	$(RM)*.class