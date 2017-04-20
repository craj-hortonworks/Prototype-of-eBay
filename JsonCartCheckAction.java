package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import com.opensymphony.xwork2.ActionSupport;
import utility.DBConnection;

public class JsonCartCheckAction extends ActionSupport {
	private String itemId;
	private String userId;
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String execute(){
		ResultSet rs;
		PreparedStatement ps;
		PreparedStatement ts;
		DBConnection db=new DBConnection();
		Connection con=db.getConnection();
		try{
			Statement st;
			System.out.println(itemId);
			System.out.println(userId);
			String changestatus = "update cart set select_status=(case when (select_status='Y') then 'N' when (select_status='N') then 'Y' end) where item_id = ? and user_id = ?;";
			ts = con.prepareStatement(changestatus);
			ts.setString(1, itemId);
			ts.setString(2, userId);
			System.out.println("debug");
			ts.executeUpdate();
			System.out.println("status changed");
			return SUCCESS;
		}
		catch(SQLIntegrityConstraintViolationException e)
		{	System.out.println("error 1");
			return ERROR;	
		}catch(Exception e)
			{	System.out.println("error 222");
				e.printStackTrace();
				return ERROR; 
		}
	}
}
