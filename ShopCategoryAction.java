package action;



import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import model.ShopCategoryModel;

import service.ShopCategoryService;

public class ShopCategoryAction extends ActionSupport implements ModelDriven {
	
	private ArrayList<ShopCategoryModel> catlist = new ArrayList<ShopCategoryModel>();
	


	
	
	
	
	public ArrayList<ShopCategoryModel> getCatlist() {
		return catlist;
	}
@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return catlist;
	}
	public void setCatlist(ArrayList<ShopCategoryModel> catlist) {
		this.catlist = catlist;
	}

	
	
	
	
	public String execute(){
		ShopCategoryService service = new ShopCategoryService();
		//function call for shop by category
				catlist = service.getList(); 
				
				ServletActionContext.getRequest().setAttribute("catlist",catlist);	
				if(catlist != null) 
					return SUCCESS;
				
				else return ERROR;
		
	}
	
	
}
