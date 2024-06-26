package cc;

import java.sql.*;
import java.util.Scanner;

import org.junit.Test;

public class helloworld {
	public static void main(String[] args) {
		int i=1;
		System.out.print("helloworld"+i);
	}
	
	@Test
	public void QueryTest() {
		System.out.println("请输入关键字：");
		String input=MyInput();
		System.out.println("符合关键字'"+input+"'的数据如下：");
		Query(input);
	}
	
	public void Query(String keyword) {
		try {
			Driver driver=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			String url="jdbc:mysql://127.0.0.1:3306/glut?useSSL=FALSE";
			String user="root";	
			String password="root";
			Connection conn=DriverManager.getConnection(url, user, password);
			String sql="select id,name,description from location where id like '%"+keyword+
					"%' or name like '%"+keyword+
					"%' or description like '%"+keyword
					+"%';";
			Statement statement=conn.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				System.out.println("地点编号："+rs.getString(1)+
						" 地点名称："+rs.getString(2)+
						" 地点描述："+rs.getString(3));
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void AddTest() {
		Location location=new Location();
		System.out.println("添加规则：地点编号不能与已有数据重复且地点编号为纯数字编号");
		System.out.println("请输入地点编号（数字类型）:");
		location.id=Integer.valueOf(MyInput());
		System.out.println("请输入地点名称（可以为空）:");
		location.name=MyInput();
		System.out.println("请输入地点描述（可以为空）:");
		location.description=MyInput();
		Add(location);
	}
	public void Add(Location location) {
		try {
			Driver driver=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			String url="jdbc:mysql://127.0.0.1:3306/glut?useSSL=FALSE";
			String user="root";	
			String password="root";
			Connection conn=DriverManager.getConnection(url, user, password);
			Statement statement=conn.createStatement();
			String sql="insert into location(id,name,description) values('"+location.id+
					"','"+location.name+"','"+location.description+"')";
			statement.executeUpdate(sql);
			System.out.println("成功添加地点："+"地点编号="+location.id+
					",地点名称="+location.name+
					"，地点描述="+location.description);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("添加失败，请检查输入数据格式");
		}
	}
	
	@Test
	public void UpdateTest() {
		Location location=new Location();
		System.out.println("修改规则：只能修改地点名称与地点描述");
		System.out.println("请输入想要修改的地点编号（数字类型）:");
		location.id=Integer.valueOf(MyInput());
		System.out.println("将要修改的地点：");
		QueryInId(location.id);
		System.out.println("修改地点名称（可以为空）:");
		location.name=MyInput();
		System.out.println("修改地点描述（可以为空）:");
		location.description=MyInput();
		Update(location);
		System.out.println("修改后的地点：");
		QueryInId(location.id);
	}
	public void Update(Location location) {
		try {
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			String url="jdbc:mysql://127.0.0.1:3306/glut?useSSL=FALSE";
			String user="root";	
			String password="root";
			Connection conn=DriverManager.getConnection(url, user, password);
			Statement statement=conn.createStatement();
			String sql="update location set name='"+location.name
					+"',description='"+location.description
					+"' where id="+location.id;
			statement.executeUpdate(sql);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void DeleteTest() {
		System.out.println("请输入要删除的地点的编号（数字类型）：");
		int id=Integer.valueOf(MyInput());
		System.out.println("已经删除如下地点：");
		Delete(id);
		
	}
	public void Delete(int id) {
		try {
			QueryInId(id);
			Driver driver=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			String url="jdbc:mysql://127.0.0.1:3306/glut?useSSL=FALSE";
			String user="root";	
			String password="root";
			Connection conn=DriverManager.getConnection(url, user, password);
			String sql="delete from location where id="+id;
			Statement statement=conn.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String MyInput() {
		Scanner userInput=new Scanner(System.in);
		return userInput.nextLine();
	}
	@Test
	public void QueryInIdTest() {
		System.out.println("地点编号：");
		String input=MyInput();
		System.out.println("地点编号为'"+input+"'的数据如下：");
		QueryInId(Integer.valueOf(input));
	}
	public void QueryInId(int id) {
		try {
			Driver driver=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			String url="jdbc:mysql://127.0.0.1:3306/glut?useSSL=FALSE";
			String user="root";	
			String password="root";
			Connection conn=DriverManager.getConnection(url, user, password);
			Statement statement=conn.createStatement();
			String sql="select id,name,description from location where id ="+id+";";
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				System.out.println("地点编号："+rs.getString(1)+
						" 地点名称："+rs.getString(2)+
						" 地点描述："+rs.getString(3));
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
