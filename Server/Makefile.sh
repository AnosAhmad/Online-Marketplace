JCC = javac -cp .:mysql-connector-java-5.0.8-bin.jar:.~/Server/mysql-connector-java-5.0.8-bin.jar
default: BDManager.class ConnectionFuture.class AuthorizationException.class AuthorizationInvocationHandler.class RequiresRole.class OnlineMarketplaceApplicationController.class Item.class OnlineMarketplaceModel.class SessionImpl.class Session.class OnlineMarketplaceServer.class OnlineMarketplace.class 
OnlineMarketplace.class: OnlineMarketplace.java
	$(JCC) OnlineMarketplace.java
OnlineMarketplaceServer.class: OnlineMarketplaceServer.java
	$(JCC) OnlineMarketplaceServer.java
OnlineMarketplaceApplicationController.class: OnlineMarketplaceApplicationController.java
	$(JCC) OnlineMarketplaceApplicationController.java	
Session.class: Session.java
	$(JCC) Session.java
AuthorizationException.class: AuthorizationException.java
	$(JCC) AuthorizationException.java
AuthorizationInvocationHandler.class: AuthorizationInvocationHandler.java
	$(JCC) AuthorizationInvocationHandler.java
RequiresRole.class: RequiresRole.java
	$(JCC) RequiresRole.java
SessionImpl.class: SessionImpl.java
	$(JCC) SessionImpl.java
OnlineMarketplaceModel.class: OnlineMarketplaceModel.java
	$(JCC) OnlineMarketplaceModel.java
Item.class: Item.java
	$(JCC) Item.java
ConnectionFuture.class: ConnectionFuture.java
	$(JCC) ConnectionFuture.java
BDManager.class: BDManager.java
	$(JCC) BDManager.java
run: BDManager.class ConnectionFuture.class AuthorizationException.class AuthorizationInvocationHandler.class RequiresRole.class OnlineMarketplaceApplicationController.class Item.class OnlineMarketplaceModel.class SessionImpl.class Session.class OnlineMarketplaceServer.class OnlineMarketplace.class
clean:
	$(RM) *.class