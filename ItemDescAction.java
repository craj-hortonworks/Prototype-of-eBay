package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.ItemDescModel;
import service.ItemDescService;

public class ItemDescAction extends  ActionSupport implements ModelDriven{

	private ItemDescModel item; 
	
	private String itemsk;
	

	public String getItemsk() {
		return itemsk;
	}



	public void setItemsk(String itemsk) {
		this.itemsk = itemsk;
	}



	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return item;
	}



	public ItemDescModel getItem() {
		return item;
	}



	public void setItem(ItemDescModel item) {
		this.item = item;
	}
	
	
	public String execute()
	{
		ItemDescService service = new ItemDescService();
		
		

		
		item = service.getItemDetails(Integer.parseInt(itemsk));
		
		if(item != null)
		return SUCCESS;
		
		else return ERROR;
	}
	

}
