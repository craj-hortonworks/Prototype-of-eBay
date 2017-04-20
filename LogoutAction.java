package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import java.util.*;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;




public class LogoutAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionID=1L;
	private String message;
	private Map<String,Object>sessionMap=null;
    
  public String execute()  {
    System.out.println(sessionMap.get("email"));
    sessionMap.clear();
    setMessage("You logged out successfully");
    return "success";
    }

  
  public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

  
public void setSession(Map<String, Object> map) {
	
	this.sessionMap=(SessionMap)map;
}


}



