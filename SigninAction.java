package action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.SigninModel;
import service.SigninService;

public class SigninAction extends ActionSupport implements ModelDriven,SessionAware {
	
	
	private SigninModel customer = new SigninModel();
	
	public SigninModel getCustomer() {
		return customer;
	}

	public void setCustomer(SigninModel customer) {
		this.customer = customer;
	}
	
	private Map<String, Object> session=ActionContext.getContext().getSession(); 
	 public void setSession(Map<String, Object> session) {
		    this.session = session;
		  }
	  
	
	
public void validate(){

	if (StringUtils.isEmpty(customer.getUsername())){                         //Email is blank
	addFieldError("username", "Username cannot be blank");
	}
	if (StringUtils.isEmpty(customer.getPassword())){                       //password is blank
		addFieldError("password", "password cannot be blank");
		}
}


public String execute(){
	SigninService rs = new SigninService(customer);          //passing model object into service class
	
    String val = rs.authenticate();
	
	 if(val.equals("success")){
		 session.put("username",customer.getUsername());
		 session.put("password",customer.getPassword());
		 
		 return "success";
	}
	
	else
		addFieldError("password","oops!no combination of username and password ");
		return "error";
}




	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

}
