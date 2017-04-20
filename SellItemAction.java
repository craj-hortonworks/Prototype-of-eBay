package action;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.io.File;
import org.apache.commons.lang3.StringUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Action;

import model.SellItemModel;
import service.SellItemService;
import utility.DBConnection;

import java.io.*;

@SuppressWarnings({ "rawtypes", "serial", "unused" })
public class SellItemAction extends ActionSupport implements ModelDriven,Action
{
	private static String SUCCESS = "success";
	private static String ERROR = "failure";
	private static String EXISTING = "existing";
	
	SellItemModel sellItemModel=new SellItemModel();
	SellItemService sellItemService=new SellItemService(sellItemModel);
		
	ArrayList<String> catlist = new ArrayList<String>() ;
	String key;

	public ArrayList<String> getCatlist() {
		return catlist;
	}

	public void setCatlist(ArrayList<String> catlist) {
		this.catlist = catlist;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str); 
	    if(d>=0)
	  	  return true;
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  } 
	 return false;
	  
	}
	
	public String execute() throws IOException{
	
	//	System.out.println("Category : " +sellItemModel.getCategoryName());
	//	System.out.println("Subcategory" +sellItemModel.getSubCategoryName());
		System.out.println("action sell");
		if(sellItemService.insertItemDetails()){
		//	key = sellItemService.getkey();
		//	addActionMessage("Your item registration is :" + key);
			System.out.println("inside insertitemdetails");
			return SUCCESS;
		}	
		
		else 
			return ERROR;
		
		
	}

	@Override
	public SellItemModel getModel() {
		// TODO Auto-generated method stub
		return sellItemModel;
	}
	
	//method to fetch category from the database
	
	

	

	
}
