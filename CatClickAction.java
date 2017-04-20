package action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import model.SubCategoryModel;
import service.CatClickService;

public class CatClickAction extends ActionSupport implements ModelDriven {
	
	private ArrayList<SubCategoryModel> subcatlist = new ArrayList<SubCategoryModel>();
	
	String catid;
	String catname;
	
	public String getCatname() {
		return catname;
	}


	public void setCatname(String catname) {
		this.catname = catname;
	}


	public String getCatid() {
		return catid;
	}


	public void setCatid(String catid) {
		this.catid = catid;
	}


	public ArrayList<SubCategoryModel> getSubcatlist() {
		return subcatlist;
	}


	public void setSubcatlist(ArrayList<SubCategoryModel> subcatlist) {
		this.subcatlist = subcatlist;
	}


	public Object getModel() {
		// TODO Auto-generated method stub
		return subcatlist;
	}
	
	
	public String execute()	
	{
		CatClickService service = new CatClickService();
		
		catname = service.getCatname(Integer.parseInt(catid));
		subcatlist = service.getSubcatList(Integer.parseInt(catid));
		return SUCCESS;
	}

}
