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

public class DebitcardAction extends ActionSupport implements ModelDriven,Action{
	private HashMap<Integer, String> cardmap = new HashMap<Integer,String>();
	private HashMap<Integer, String> bankmap = new HashMap<Integer,String>();
	DebitcardModel debitcard = new DebitcardModel();
	private static String FAILURE = "failure";
	
	public String execute(){
		Map<String , Object> session = ActionContext.getContext().getSession();
		int userid = (int)session.get("user_key");
		System.out.println("usesrid " +userid);
		//Map session = ActionContext.getContext().getSession();
		// System.out.println("m herein session debit"+(String)session.get((String)"logined"));
		//insert values in database like this only in bank table
		cardmap.put(1,"visa");
		cardmap.put(2, "rupay");
		bankmap.put(1,"Maestro/ATM" );
		bankmap.put(2,"SBI" );
		bankmap.put(3,"Bharatiya mahila bank" );
		bankmap.put(4,"Bank Of India" );
		bankmap.put(5,"Bank Of Maharashtra" );
		System.out.println("bank value "+(String)bankmap.get(new Integer(debitcard.getBank())));
		if(debitcard.getCard()=="3")
			debitcard.setBank((String)bankmap.get(new Integer(debitcard.getBank())));
		else
			debitcard.setBank((String)cardmap.get(new Integer(debitcard.getCard())));
		System.out.println("bank value "+debitcard.getBank());
		DebitcardService debitcardservice = new DebitcardService();
		String result = debitcardservice.isValid(debitcard,userid);	
		if(result.equals("success")){
			return SUCCESS;
		}
		if(result.equals("incorrect")){
			addActionMessage("Enter valid details or select other payment option.");
			return FAILURE;
		}	
		if(result.equals("insufficientbal")){
			addActionMessage("Insufficient Balance . Select other payment option.");
			return FAILURE;
		}	
		else 
			return ERROR;
			
		
	}
	public Object getModel() {
		
		return debitcard;
	}

}
