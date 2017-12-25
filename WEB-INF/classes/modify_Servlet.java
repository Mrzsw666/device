import mybean.jsp_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class modify_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		request.setCharacterEncoding("gb2312");
		jsp_Bean modify_bean=null;
		modify_bean=(jsp_Bean)request.getAttribute("modify_bean");
		modify_bean=new jsp_Bean();
		request.setAttribute("modify_bean",modify_bean);
		try{
		String suborSector=request.getParameter("suborSector");
		String interNum=request.getParameter("interNum");
		String storePlace=request.getParameter("storePlace");
		String totalAmount=request.getParameter("totalAmount");
		String loanAmount=request.getParameter("loanAmount");
		String d=request.getParameter("id");
		if(suborSector==null||suborSector.length()==0||interNum==null||interNum.length()==0||storePlace==null||storePlace.length()==0||totalAmount==null||totalAmount.length()==0||loanAmount==null||loanAmount.length()==0){
			throw new Exception("您输入的信息不完整！");
		}
		Connection con;
		PreparedStatement sql;
	    String dataBase="device";
	    int id=Integer.parseInt(d);
	    String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=gb2312&useSSL=true";
		con=DriverManager.getConnection(url);
		sql=con.prepareStatement("UPDATE product SET suborSector='"+suborSector+"',"+"interNum='"+interNum+"',"+"storePlace='"+storePlace+"',"+"totalAmount="+totalAmount+","+"loanAmount="+loanAmount+" where id="+id);
		sql.executeUpdate();
		String idea="修改成功";
		modify_bean.setResult(idea);
		}
		catch(Exception e){
		   System.out.println(e);
		   String idea="修改失败！";
		   modify_bean.setResult(idea);
		   RequestDispatcher dispatcher=request.getRequestDispatcher("modify.jsp");
			dispatcher.forward(request,response);
		   return ;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("modify.jsp");
		dispatcher.forward(request,response);
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
}
