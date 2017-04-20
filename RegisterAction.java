package action;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import service.RegisterService;
import model.RegisterModel;

public class RegisterAction extends ActionSupport implements ModelDriven{

	private RegisterModel customer = new RegisterModel();
	
	public RegisterModel getCustomer() {
		return customer;
	}


	public void setCustomer(RegisterModel customer) {
		this.customer = customer;
	}

private String firstname;

public String getFirstname() {
	return firstname;
}


public void setFirstname(String firstname) {
	this.firstname = firstname;
}

private String lasttname;

public String getLasttname() {
	return lasttname;
}


public void setLasttname(String lasttname) {
	this.lasttname = lasttname;
}



	public void validate(){

		if (StringUtils.isEmpty(customer.getEmail())){                         //Email is blank
		addFieldError("email", "Email cannot be blank");
		}
		if (StringUtils.isEmpty(customer. getReenter_email())){                 // reentered Email is blank
			addFieldError("reenter_email", "Email cannot be blank");
			}
		if (StringUtils.isEmpty(customer.getPassword())){                       //password is blank
			addFieldError("password", "password cannot be blank");
			}
		if (StringUtils.isEmpty(customer.getFirstname())){                      //firstname is blank
			addFieldError("firstname", "firstname cannot be blank");
			}
		if (StringUtils.isEmpty(customer.getLastname())){                       //lastname is blank
			addFieldError("lastname", "lastname cannot be blank");
			}
			
		if (StringUtils.isEmpty(customer.getUsername())){                       //lastname is blank
			addFieldError("username", "username cannot be blank");
			}
		
		
			
	}
	
	
	
	
	
	
	
	
	
	
	public String execute(){
		RegisterService rs = new RegisterService(customer);          //passing model object into service class
		
        String val = rs.insert();
		
		if(val.equals("existing")){
			addFieldError("email","Email id or username already exists!!");
			return "existing";
		}
		else if(val.equals("success")){
			//addActionMessage("Registration successfully completed");
			
			return "success";
		}
		
		else
			return "error";
	}
	
	
	
	
@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return customer;
	}









}
