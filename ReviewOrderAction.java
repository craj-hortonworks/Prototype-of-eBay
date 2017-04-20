package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import model.ReviewOrderModel;
import service.ReviewOrderService;


public class ReviewOrderAction extends ActionSupport implements ModelDriven
{
	
	ArrayList<ReviewOrderModel> itemlist;
	ReviewOrderModel addressobj;
	ReviewOrderModel totalobj;
	private int count=0;
	private int quantity;
	
	Map<String , Object> session = ActionContext.getContext().getSession();
	int userid = (int)session.get("user_key");
	
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity=quantity;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count=count;
	}

	public ReviewOrderModel getAddressobj() {
		return addressobj;
	}

	public void setAddressobj(ReviewOrderModel addressobj) {
		this.addressobj = addressobj;
	}
	
	public ReviewOrderModel getTotalobj() {
		return totalobj;
	}

	public void setTotalobj(ReviewOrderModel totalobj) {
		this.totalobj = totalobj;
	}
	
	public ArrayList<ReviewOrderModel> getItemlist() {
		return itemlist;
	}

	public void setItemlist(ArrayList<ReviewOrderModel> itemlist) {
		this.itemlist = itemlist;
	}
	
	 


	
	public String execute()
	{
		
		
		
		ReviewOrderService service = new ReviewOrderService();
		quantity=service.insertintorevieworder(userid);
		System.out.println("quantity"+quantity);
			return getCount1();
		/*else 
			return "error";
			/*ReviewOrderService service = new ReviewOrderService();
		addressobj = service.getItem();
				System.out.println(addressobj.getTotalprice());
		if(addressobj == null)
			return ERROR;
		
		else return SUCCESS;
	}*/
		
		// To get the itemlist
		/*ReviewOrderService service = new ReviewOrderService();
			
			itemlist = service.getItem();
			
			totalprice=service.totalprice;	
			System.out.println("Actiontotalprice"+totalprice);
			System.out.println(itemlist.get(1).getPrice());
			
			
		if(itemlist == null || itemlist.size() == 0 )
		return "error";
		
		else return "success";
		}*/
		
		//To get the address
	/*ReviewOrderService service = new ReviewOrderService();
		addressobj = service.fetchaddress(Integer.parseInt("1"));
				System.out.println(addressobj.getAddress1());
		/*if(addressobj == null)
			return ERROR;
		
		else return SUCCESS;
				return fetchitemlist();*/
		
		
	}
	public String getCount1()
	{
		ReviewOrderService service = new ReviewOrderService();
		 count=service.getCount(userid);
		System.out.println("count"+count);
		return fetchaddress();
	}
	
	public String fetchaddress()
	{
		ReviewOrderService service = new ReviewOrderService();
		addressobj = service.fetchaddress(userid);
				//System.out.println(addressobj.getAddress1());
		/*if(addressobj == null)
			return ERROR;
		
		else return SUCCESS;*/
				return fetchitemlist();
	
	}
	public String fetchitemlist()
	{
		ReviewOrderService service = new ReviewOrderService();
		
		itemlist = service.getItem(userid);
	
		//System.out.println(itemlist.get(1).getPrice());
		return fetchtotal();
		
	
	}
	
	public String fetchtotal()
	{
		ReviewOrderService service = new ReviewOrderService();
		totalobj = service.getTotal(userid);
				System.out.println("total"+totalobj.getTotalprice());
				System.out.println("grand"+totalobj.getGrandtotal());
				System.out.println("vat"+totalobj.getVat());
		if(totalobj == null)
			return ERROR;
		
		else return SUCCESS;
	}
	
	

	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
