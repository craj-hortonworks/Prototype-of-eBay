package action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import model.*;
import service.*;

public class CreditcardAction extends ActionSupport implements ModelDriven,Action{
	private HashMap<Integer, String> cardmap = new HashMap<Integer,String>();
	CreditcardModel creditcard = new CreditcardModel();
	private static String FAILURE = "failure";
	
	public String execute(){
		Map<String , Object> session = ActionContext.getContext().getSession();
		int userid = (int)session.get("user_key");
		//Map session = ActionContext.getContext().getSession();
		// System.out.println("m herein session debit"+(String)session.get((String)"logined"));
		cardmap.put(1,"visa");
		cardmap.put(2, "american express");
		cardmap.put(3,"eze");
		creditcard.setBank((String)cardmap.get(new Integer(creditcard.getCard())));
		System.out.println("bank value "+creditcard.getBank());
		CreditcardService creditcardservice = new CreditcardService();
		String result = creditcardservice.isValid(creditcard,userid);	
		if(result.equals("success")){
			addActionMessage("Your item registration is :");
			return SUCCESS;
		}
		if(result.equals("incorrect")){
			//addActionMessage("Enter valid details or select other payment option.");
			return FAILURE;
		}	
		if(result.equals("insufficientbal")){
			addActionMessage("Credit Limit exceeds . Select other payment option.");
			return FAILURE;
		}	
		else 
			return ERROR;
			
		
	}
	public Object getModel() {
		
		return creditcard;
	}

}
