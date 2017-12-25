import mybean.jsp_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class revert_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		jsp_Bean revert_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("revert.jsp");
		try{
			revert_Bean=(jsp_Bean)request.getAttribute("revert_Bean");
			if(revert_Bean==null){
				revert_Bean=new jsp_Bean();
				request.setAttribute("revert_Bean",revert_Bean);
			}
		}
		catch(Exception exp){
			revert_Bean=new jsp_Bean();
			request.setAttribute("revert_Bean",revert_Bean);
		}
		request.setCharacterEncoding("gb2312");
		String dataBase="Device";
		String tableNameA="lendproduct";
		String tableNameB="product";
		String im=request.getParameter("interNum");
		String ud=request.getParameter("useDepart");
		String at=request.getParameter("amount");

		if(im==null||im.length()==0){
			revert_Bean.setResult("失败！");
			dispatcher.forward(request,response);
			return;
		}
		Connection con;
		
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=gb2312&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			String condition="SELECT * FROM "+tableNameA+" WHERE interNum = "+im+" and useDepart ="+"'"+ud+"'";
			ResultSet rs=sql.executeQuery(condition);
			if(!rs.next()){
				revert_Bean.setResult("失败！请输入正确的设备编号");
				dispatcher.forward(request,response);
				return;
			}
			
			rs.first();	
			String number=rs.getString(2);
			int a=Integer.parseInt(number);
			int b=Integer.parseInt(at);
			if(a==b){
				sql.executeUpdate("DELETE FROM "+tableNameA+" WHERE interNum = "+im+" and useDepart ="+"'"+ud+"'");}
			else if(a<b){
					revert_Bean.setResult("失败！请输入借用数量");
					dispatcher.forward(request,response);
					return;}
			else{
	            a=a-b;
				sql.executeUpdate("UPDATE "+tableNameA+" SET amount = "+a+" WHERE interNum = "+im+" and useDepart ="+"'"+ud+"'");
			}			
			
			String conditionB="SELECT*FROM "+tableNameB+" where interNum ="+im;
			ResultSet rsB=sql.executeQuery(conditionB);
			if(!rsB.next()){
				revert_Bean.setResult("失败！请输入正确的设备编号或部门");
				dispatcher.forward(request,response);
				return;
			}
			rsB.first();
			String lnumber=rsB.getString(8);
			int c=Integer.parseInt(lnumber);
			int d=Integer.parseInt(at);
			c=c+d;
			sql.executeUpdate("UPDATE "+tableNameB+" SET loanAmount = "+c+" WHERE interNum = "+im);
			con.close();
			revert_Bean.setResult("成功！");
			dispatcher.forward(request,response);
		}
		catch(SQLException ee){
			System.out.println(ee);
			revert_Bean.setResult("失败！");
			dispatcher.forward(request,response);
		}
		
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}