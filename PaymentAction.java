package action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import service.PaymentService;

public class PaymentAction extends ActionSupport {
	//private  int userId=1;
	private  double total_cost;

	
	
	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}
	
	public String execute(){
		Map<String , Object> session = ActionContext.getContext().getSession();
		int userid = (int)session.get("user_key");
		session.put("totalcost", total_cost);
		PaymentService payservice = new PaymentService();
		total_cost = payservice.getTotal(userid);
		session.put("totalcost", total_cost);
		
		// Map session = ActionContext.getContext().getSession();
		// session.put("logined","true");
		// System.out.println("m herein session"+(String)session.get((String)"logined"));
		if(total_cost>=0)
			return SUCCESS;
		else 
			return ERROR;
		//return ERROR;
	}

	
	

}
