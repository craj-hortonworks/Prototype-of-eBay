package action;



import model.retrive_catListModel;
import model.sellModel;
import service.retrive_catListService;
import service.sellService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.mapper.ActionMapping;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "serial", "rawtypes" })
public class sellAction extends ActionSupport implements ModelDriven{

	public  ArrayList<String> categoriesName = new ArrayList<String>();
	retrive_catListModel catModel = new retrive_catListModel();
	retrive_catListService catService = new retrive_catListService();
	private sellModel seller = new sellModel();
	
	public sellModel getSeller() {
		return seller;
	}

	public void setSeller(sellModel seller) {
		this.seller = seller;
	}
	

	public String execute() throws IOException{

		Map<String , Object> session = ActionContext.getContext().getSession();
		int userid = (int)session.get("user_key");
		
		categoriesName=catService.getCategories();
		System.out.println("in action");
		sellService rs = new sellService(seller,userid);
		String val = rs.insert();
		System.out.println("val"+val);
		 if(val.equals("success")){
			addActionMessage("successfull");
			
			return SUCCESS;
		}
		
		else
			return ERROR;
	}
	

	@Override
	public sellModel getModel() {
		// TODO Auto-generated method stub
		return seller;
	}

}
