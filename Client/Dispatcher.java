public class Dispatcher
{
//Type of flows 
private AdminViewController adminViewCon;
private CustViewController custViewCon;
private OnlineMarketplace myMarket;


/*
* Constructor
*/
public Dispatcher()
{ }

/*
* Dispatch the flow; either Administrator or Customer
*/
public void dispatch(Session session)
{
   if (session.getRole().equals("Admin"))
   {
      adminViewCon = new AdminViewController(session, myMarket); 
   }
   else
   {
      custViewCon = new CustViewController(session, myMarket);    
   }
}

public void setMyMarket (OnlineMarketplace myMarket)
{
  this.myMarket  = myMarket ;
}
}