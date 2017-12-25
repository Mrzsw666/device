import mybean.jsp_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class useless_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		request.setCharacterEncoding("gb2312");
		String index=request.getParameter("interNum");
		String ulReason=request.getParameter("scrapReason");
		String approval=request.getParameter("approvePerson");
		String handleP=request.getParameter("handPerson");
		String handleW=request.getParameter("treatMode");
		String a=request.getParameter("totalQuantify");
		String yearr=request.getParameter("year");
		String monthh=request.getParameter("month");
		String dayy=request.getParameter("day");
		try{
		if(index==null||index.length()==0||ulReason==null||ulReason.length()==0||approval==null||approval.length()==0||handleP==null||handleP.length()==0||handleW==null||handleW.length()==0||a==null||a.length()==0||yearr==null||yearr.length()==0||monthh==null||monthh.length()==0||dayy.length()==0||dayy==null){
			throw new Exception("您所填写的信息不完整！");
		}
		int year=Integer.parseInt(yearr);
		int month=Integer.parseInt(monthh);
		int day=Integer.parseInt(dayy);
		int amount=Integer.parseInt(a);
		jsp_Bean useless_bean=null;
		useless_bean=(jsp_Bean)request.getAttribute("useless_bean");
		useless_bean=new jsp_Bean();
		request.setAttribute("useless_bean",useless_bean);
		Connection con;
		PreparedStatement sql;
		String dataBase="Device";
		String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=gb2312&useSSL=true";
		con=DriverManager.getConnection(url);
		sql=con.prepareStatement("insert into scrapproduct VALUES(?,?,?,?,?,?,?,?,"+null+")");
		sql.setString(1,index);
		sql.setString(2,ulReason);
		sql.setString(3,approval);
		sql.setString(4,handleP);
		sql.setString(5,handleW);
		sql.setInt(6,year);
		sql.setInt(7,month);
		sql.setInt(8,day);
		sql.executeUpdate();
		sql=con.prepareStatement("SELECT loanAmount FROM product WHERE interNum=+"+index);
		ResultSet rs=sql.executeQuery();
		rs.next();
		String le=rs.getString(1);
		int lend=Integer.parseInt(le);
		lend-=amount;
		sql.executeUpdate("UPDATE product SET loanAmount="+lend+" WHERE interNum="+index);
		String error="添加成功！";
		useless_bean.setResult(error);
		RequestDispatcher dispatcher=request.getRequestDispatcher("useless.jsp");
		dispatcher.forward(request,response);
	}catch(Exception e){
		System.out.println(e);
		jsp_Bean useless_bean=null;
		useless_bean=(jsp_Bean)request.getAttribute("useless_bean");
		useless_bean=new jsp_Bean();
		request.setAttribute("useless_bean",useless_bean);
		String error="添加失败！";
		useless_bean.setResult(error);
		RequestDispatcher dispatcher=request.getRequestDispatcher("useless.jsp");
		dispatcher.forward(request,response);
	}
}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
}
