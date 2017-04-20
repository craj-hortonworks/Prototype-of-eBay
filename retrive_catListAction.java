package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import model.retrive_catListModel;

import service.retrive_catListService;

 public class retrive_catListAction  {
	
	public ArrayList<String> CatList = new ArrayList<String>();
	private static String SUCCESS = "success";
	private static String ERROR = "failure";
	private static String EXISTING = "existing";
	
	retrive_catListModel catModel = new retrive_catListModel();
	retrive_catListService catService = new retrive_catListService();
	public ArrayList<retrive_catListModel> categoriesList = new ArrayList<retrive_catListModel>();
	public  ArrayList<String> categoriesName = new ArrayList<String>();
	public ArrayList<String> categoriesId = new ArrayList<String>();
	
	public String execute() throws IOException{
		Map<String , Object> session = ActionContext.getContext().getSession();
		int userid = (int)session.get("user_key");
		
		categoriesName=catService.getCategories();
		System.out.println("retrive");
		for(int i =0; i < categoriesName.size(); i++)
		{	
			System.out.println();
		}
		return SUCCESS;
	
		
	}
	
	
}
