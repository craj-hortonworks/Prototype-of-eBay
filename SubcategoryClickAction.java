package action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.SubcategoryClickModel;
import service.SubcategoryClickService;

public class SubcategoryClickAction extends ActionSupport{


	String subcatid;
	ArrayList<SubcategoryClickModel> items = new ArrayList<SubcategoryClickModel>(); 
	//item id, itema name, price, image(each one is an object)
   // Map<String, String> brandList = new LinkedHashMap<String, String>();
	ArrayList<String> brndlist = new ArrayList<String>();
	
	public ArrayList<String> getBrndlist() {
		return brndlist;
	}

	public void setBrndlist(ArrayList<String> brndlist) {
		this.brndlist = brndlist;
	}

//	public Map<String, String> getBrandList() {
//		return brandList;
//	}
//
//	public void setBrandList(Map<String, String> brandList) {
//		this.brandList = brandList;
//	}

	public ArrayList<SubcategoryClickModel> getItems() {
		return items;
	}

	public void setItems(ArrayList<SubcategoryClickModel> items) {
		this.items = items;
	}

	public String getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(String subcatid) {
		this.subcatid = subcatid;
	}

	public String execute(){

		System.out.println("In Subcategory click action");
		SubcategoryClickService service = new SubcategoryClickService();
		System.out.println("subcat id:" + subcatid);
		items = service.getAllItems(subcatid);
		
		if(items != null)
			return SUCCESS;
		else
			return ERROR;			
	}
	
	public String brandFilter(){
		
		SubcategoryClickService service = new SubcategoryClickService();
		System.out.println("fetching brandlist for catid "+ subcatid);
//		brandList = service.getBrand(subcatid);
//		System.out.println(brandList.size());
//
		brndlist = service.getBrand(subcatid);
		ServletActionContext.getRequest().setAttribute("brndlist",brndlist);
		return SUCCESS;
	}
	
}
