package cc;

import java.sql.*;

import org.junit.Test;

public class helloworld {
	public static void main(String[] args) {
		int i=1;
		System.out.print("helloworld"+i);
		Connection conn=null;
		Statement state=null;
		ResultSet rs=null;
		try 
		{
			Driver driver=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			String url="jdbc:mysql://127.0.0.1:3306/test?useSSL=FALSE";
			String user="root";	
			String password="root";
			conn=DriverManager.getConnection(url, user, password);
			System.out.println(conn);
			String sql="select id,username,password from users;";
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next()) {
				System.out.println("id= "+rs.getString(1)+" name="+rs.getString(2)+" password="+rs.getString(3));
			}
			rs.close();
			state.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void Function() {
		System.out.println("测试中");
	}
	@org.junit.Before
	public void Before() {
		System.out.println("测试前");
	}
	@org.junit.After
	public void After() {
		System.out.println("测试后");
	}
}
