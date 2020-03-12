package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Info;
import utils.DBUtil;

public class dao {
	//添加数据入库
	public boolean add(Info info) {
		String sql="insert into crawl (province,Confirmed_num,Cured_num,Dead_num) values (?,?,?,?)";
		Object obj[]= {info.getProvince(),info.getConfirmed_num(),info.getCured_num(),info.getDead_num()};
		return DBUtil.executeUpdate(sql, obj);
	}
	
	//查询数据
	 public List<Info> Query() {
  	   List<Info> infos=new ArrayList<>();
  	   Info info= null;
	       ResultSet rs = null; 
  	   try {
	    	 String sql="select * from crawl  " ;
	    	 Object [] params= {};
	    	 rs=DBUtil.executeQuery(sql, params);
	         while(rs.next()) {
	        	 int Id=rs.getInt("id");
	        	 String Province=rs.getString("province");
	        	 String Confirmed_num=rs.getString("Confirmed_num");
	        	 String Cured_num=rs.getString("Cured_num");
	        	 String Dead_num=rs.getString("Dead_num");
	        	 info=new Info(Id,Province,Confirmed_num,Cured_num,Dead_num);
	        	 infos.add(info);
	         }
	     }catch(SQLException e) {
	    	 e.printStackTrace();
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }finally {
	    	 try {
     			//先开的后关，后开的先关
     		if(rs!=null)rs.close();
     		if(DBUtil.pstmt!=null)DBUtil.pstmt.close();
     		if(DBUtil.connection !=null)DBUtil.connection.close();
     		}catch(SQLException e) {
     			e.printStackTrace();
     		}finally {
     			
     		}
	    	
	     }
  	   return infos;
     }
}
