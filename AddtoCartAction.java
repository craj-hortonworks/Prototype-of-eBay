package action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import model.AddtoCartModel;
import model.CartItemModel;
import service.AddtoCartService;


public class AddtoCartAction extends ActionSupport implements ModelDriven<AddtoCartModel>
{
	

	
	AddtoCartModel addtoCartModel=new AddtoCartModel();
	ArrayList<CartItemModel> itemlist = new ArrayList<CartItemModel>() ;
	
	String totalorder;
	
	public ArrayList<CartItemModel> getItemlist() {
		return itemlist;
	}

	public void setItemlist(ArrayList<CartItemModel> itemlist) {
		this.itemlist = itemlist;
	}

		
	public String execute(){
		
		Map<String , Object> session = ActionContext.getContext().getSession();
		//int userid = (int)session.get("user_key");
		if ((session.get("user_key"))==null)
			return "signin";
		
		addtoCartModel.setUserId((session.get("user_key")).toString());
		
		System.out.println("entering in action class!!!");
		AddtoCartService addtoCartService=new AddtoCartService(addtoCartModel);
		String val = addtoCartService.addtoCartDetails();
		System.out.println(val);
		
		if(val=="success"){	
			System.out.println("successfully added to cart");
			itemlist = addtoCartService.fetchitemlist();
			System.out.println("itemlist got fetched!!!");
			totalorder = addtoCartService.fetchtotalorder();
			if(itemlist == null){
			System.out.println("No item exists in the cart!!!");
			return SUCCESS;
			}
			else {
			System.out.println("Item added to the cart");
			return SUCCESS;
			}
		}
		
		else{
			System.out.println("some error!!");		
			return ERROR;
		}
		
	}

	@Override
	public AddtoCartModel getModel() {
		// TODO Auto-generated method stub
		return addtoCartModel;
	}

	
}
