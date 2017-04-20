package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.AddtoCartModel;
import model.CartItemModel;
import service.RemoveFromCartService;
import service.AddtoCartService;

public class RemoveFromCartAction extends ActionSupport {
	
	
	ArrayList<CartItemModel> itemlist = new ArrayList<CartItemModel>() ;
	
	String totalorder;
	String item_surrogatekey;
	String userId;
	String cartQuantity;
	
	public String getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(String cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public String getTotalorder() {
		return totalorder;
	}

	public void setTotalorder(String totalorder) {
		this.totalorder = totalorder;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<CartItemModel> getItemlist() {
		return itemlist;
	}

	public void setItemlist(ArrayList<CartItemModel> itemlist) {
		this.itemlist = itemlist;
	}

	

	public String getItem_surrogatekey() {
		return item_surrogatekey;
	}

	public void setItem_surrogatekey(String item_surrogatekey) {
		this.item_surrogatekey = item_surrogatekey;
	}

	public String execute()
	{
		
		System.out.println("entered in remove action");
		System.out.println("itemid is" + item_surrogatekey);
		
		RemoveFromCartService removefromcartservice = new RemoveFromCartService();
		
		String val = removefromcartservice.removeitem(item_surrogatekey, userId);
		
		if(val=="success"){
			System.out.println("successfully removed from cart");
			itemlist = removefromcartservice.fetchitemlist(userId);
			System.out.println("itemlist got fetched!!!");
			totalorder = removefromcartservice.fetchtotalorder(userId);
			if(itemlist == null){
			System.out.println("No item exists in the cart!!!");
			return SUCCESS;
			}
			else {
			System.out.println("Item exists in the cart");
			return SUCCESS;
			}
		}
		else{
			System.out.println("error in removing!!");
			return ERROR;
		}
	}
	
	public String updateqty(){
		
		System.out.println("enter to update quantity");
		System.out.println("itemid is" + item_surrogatekey);
		
		RemoveFromCartService removefromcartservice = new RemoveFromCartService();
		
		String val = removefromcartservice.updateQty(item_surrogatekey, userId,cartQuantity);
		
		if(val=="success"){
			System.out.println("successfully removed from cart");
			itemlist = removefromcartservice.fetchitemlist(userId);
			System.out.println("itemlist got fetched!!!");
			totalorder = removefromcartservice.fetchtotalorder(userId);
			if(itemlist == null){
			System.out.println("No item exists in the cart!!!");
			return SUCCESS;
			}
			else {
			System.out.println("Item exists in the cart");
			return SUCCESS;
			}
		}
		else{
			System.out.println("error in removing!!");
			return ERROR;
		}
		
	}
}