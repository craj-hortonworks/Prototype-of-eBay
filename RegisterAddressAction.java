package action;

import java.io.IOException;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.RegisterAddressModel;
import service.RegisterAddressService;

public class RegisterAddressAction extends ActionSupport implements ModelDriven {
	private static String SUCCESS = "success";
	private static String ERROR = "failure";
	private static String EXISTING = "existing";
	RegisterAddressModel registerAddressModel=new RegisterAddressModel();
	RegisterAddressService registerAddressService=new RegisterAddressService(registerAddressModel);
	

	
	public String execute() throws IOException{
		
		
		if(registerAddressService.insertItemDetails()){
			return SUCCESS;
		}	
		
		else 
			return ERROR;
		
		
	}
	

	public Object getModel() {
		
		return registerAddressModel;
	}

}
