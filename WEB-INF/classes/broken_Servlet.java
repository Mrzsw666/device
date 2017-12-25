import mybean.jsp_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class broken_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		jsp_Bean broken_Bean=null;
		try{
			broken_Bean=(jsp_Bean)request.getAttribute("broken_Bean");
			if(broken_Bean==null){
				broken_Bean=new jsp_Bean();
				request.setAttribute("broken_Bean",broken_Bean);
			}
		}
		catch(Exception exp){
			broken_Bean=new jsp_Bean();
			request.setAttribute("broken_Bean",broken_Bean);
		}
		request.setCharacterEncoding("gb2312");
		RequestDispatcher dispatcher=request.getRequestDispatcher("broken.jsp");
		String dataBase="Device";
		String tableNameA="repairproduct";
		String tableNameB="product";
		String im=request.getParameter("interNum");
		String fe=request.getParameter("faultCase");

		int w=0;

		if(im==null||im.length()==0){
			broken_Bean.setResult("失败！");
			dispatcher.forward(request,response);
			return;
		}
		
		String conditionA="INSERT INTO "+tableNameA+" VALUES"+"("+"'"+im+"','"+fe+"',"+null+","+w+","+null+","+w+","+w+","+w+","+null+")";
		Connection con;
		
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=gb2312&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			String conditionB="SELECT * FROM "+tableNameB+" where interNum ="+im;
			ResultSet rs=sql.executeQuery(conditionB);
			
			if(!rs.next()){
				broken_Bean.setResult("失败！请输入正确的设备编号");
				dispatcher.forward(request,response);
				return;
			}
		
			
			rs.first();	
			String number=rs.getString(8);
			int a=Integer.parseInt(number);
			a=a-1;
			sql.executeUpdate("UPDATE "+tableNameB+" SET loanAmount = "+a+" WHERE interNum ="+im);
			sql.executeUpdate(conditionA);
			con.close();
			broken_Bean.setResult("成功！");
			dispatcher.forward(request,response);
			}
			catch(SQLException e){
				System.out.println(e);
				broken_Bean.setResult("失败！");
				dispatcher.forward(request,response);
			}		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
}