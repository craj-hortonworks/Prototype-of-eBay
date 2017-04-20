package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import model.AddAddressModel;
import model.ReviewOrderModel;
import service.AddAddressService;
import service.ReviewOrderService;


public class AddAddressAction extends ActionSupport implements ModelDriven{
	
	ArrayList<ReviewOrderModel> itemlist;
	ReviewOrderModel addressobj;
	ReviewOrderModel totalobj;
	
	Map<String , Object> session = ActionContext.getContext().getSession();
	int userid = (int)session.get("user_key");

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
	 private AddAddressModel addAddressModel=new AddAddressModel();
	 public AddAddressModel getAddAddressModel() {
			return addAddressModel;
		}

		public void setAddAddressModel(AddAddressModel addAddressModel) {
			this.addAddressModel = addAddressModel;
		}

	
	AddAddressService addAddressService=new AddAddressService(addAddressModel);
	
	public String execute() throws IOException{
		
		System.out.println(addAddressModel.getState());
		
		if(addAddressService.insertAddressDetails(userid)){
			
			return fetchaddress();
		}	
		
		else 
			return "error";
		
		
	}
	public String fetchaddress()
	{
		ReviewOrderService service = new ReviewOrderService();
		addressobj = service.fetchaddress(userid);
				System.out.println(addressobj.getAddress1());
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
				System.out.println(totalobj.getTotalprice());
		if(totalobj == null)
			return ERROR;
		
		else return SUCCESS;
	}

	@Override
	public Object  getModel() {
		// TODO Auto-generated method stub
		return addAddressModel;
	}

	

	
	

}
