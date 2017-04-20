package action;
import java.io.IOException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.VatModel;
import service.VatService;



public class VatAction extends ActionSupport implements ModelDriven{
	private static String SUCCESS = "success";
	private static String ERROR = "failure";
	private static String EXISTING = "existing";
	VatModel vatModel=new VatModel();
	VatService vatService=new VatService(vatModel);
	
	public String execute() throws IOException{
		
		if(vatService.insert()){
			
			addActionMessage("vat successfully inserted");
			return SUCCESS;
		}
		
		else 
			return ERROR;
		
	}
	
	public void validate()
	{
		if(vatModel.getVat().isEmpty())
		{
			addFieldError("vat", "Vat cannot be blank");
		}
		
	}
	
public Object getModel() {
		
		return vatModel;
	}
}
