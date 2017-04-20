package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import utility.DBConnection;

public class Jsonsubcatfetch extends ActionSupport {
	
	private String dummyMessage;
	
	private Map<String, String> subcatmap = new LinkedHashMap<String, String>();
	
	//parameter from jquery
	private String catid,catname;
	
	
	
	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public String getDummyMessage() {
		return dummyMessage;
	}

	public void setDummyMessage(String dummyMessage) {
		this.dummyMessage = dummyMessage;
	}

	public Map<String, String> getSubcatmap() {
		return subcatmap;
	}

	public void setSubcatmap(Map<String, String> subcatmap) {
		this.subcatmap = subcatmap;
	}

	
	public String execute(){
		
		try{
			DBConnection db=new DBConnection();
			Connection con=db.getConnection();
			
			PreparedStatement ps;
			ResultSet rs;
			
			ps = con.prepareStatement("select cat1_sk from cat1 where name= ?");
     		ps.setString(1, catname);
     		rs = ps.executeQuery();
			rs.next();	
			int catid = rs.getInt(1); 
			
			System.out.println("category id "+ "is"+ catid);
			
			ps =  con.prepareStatement("select * from cat2 where parent_id = ?");
			ps.setInt(1, catid);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				subcatmap.put(Integer.toString(rs.getInt("cat2_sk")), rs.getString("name"));
			}
			
			System.out.println("subcatlist fetched");
			dummyMessage = "Ajax Action Triggered";
			return SUCCESS;
		}
		
		catch(Exception e){
			
			e.printStackTrace();
			return ERROR;
		}

	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	
}
