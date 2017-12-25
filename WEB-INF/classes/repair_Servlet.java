import mybean.jsp_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class repair_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		jsp_Bean repair_Bean=null;
		try{
			repair_Bean=(jsp_Bean)request.getAttribute("repair_Bean");
			if(repair_Bean==null){
				repair_Bean=new jsp_Bean();
				request.setAttribute("repair_Bean",repair_Bean);
			}
		}
		catch(Exception exp){
			repair_Bean=new jsp_Bean();
			request.setAttribute("repair_Bean",repair_Bean);
		}
		request.setCharacterEncoding("gb2312");
		RequestDispatcher dispatcher=request.getRequestDispatcher("repair.jsp");
		String dataBase="Device";
		String tableNameA="repairproduct";
		String tableNameB="product";
		String im=request.getParameter("interNum");
		String rt=request.getParameter("result");
		String py=request.getParameter("pay");
		String pn=request.getParameter("person");
		String yr=request.getParameter("year");
		String mh=request.getParameter("month");
		String dy=request.getParameter("day");
		if(im==null||im.length()==0){
			repair_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(rt==null||rt.length()==0){
			repair_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(py==null||py.length()==0){
			repair_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(pn==null||pn.length()==0){
			repair_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(yr==null||yr.length()==0){
			repair_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(mh==null||mh.length()==0){
			repair_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(dy==null||dy.length()==0){
			repair_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		int a=Integer.parseInt(yr);
		int b=Integer.parseInt(mh);
		int c=Integer.parseInt(dy);
		double p=Double.parseDouble(py);

		String conditionA="UPDATE "+tableNameA+" SET result ="+"'"+rt+"',pay = "+p+",person ="+"'"+pn+"',year = "+a+",month = "+b+",day = "+c+" WHERE interNum ="+im;
		Connection con;
		System.out.println(rt);
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=gb2312&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String conditionB="SELECT * FROM "+tableNameB+" where interNum ="+im;
			
			System.out.println("1");
			
			ResultSet rs=sql.executeQuery(conditionB);
			if(!rs.next()){
				repair_Bean.setResult(" ß∞‹£°«Î ‰»Î’˝»∑µƒ…Ë±∏±‡∫≈");
				dispatcher.forward(request,response);
				return;
			}
			rs.first();
			String number=rs.getString(8);
			int l=Integer.parseInt(number);
			l=l+1;
			System.out.println("2");
			if(rt.equals("≥…π¶")){
				System.out.println("2.2");
			    sql.executeUpdate("UPDATE "+tableNameB+" SET loanAmount = "+l+" WHERE interNum ="+im);
			    }
			System.out.println(conditionA);
			sql.executeUpdate(conditionA);

			System.out.println("3");
			
			
			con.close();
			repair_Bean.setResult("≥…π¶£°");
			dispatcher.forward(request,response);
			}
			catch(SQLException e){
				System.out.println(e);
				repair_Bean.setResult(" ß∞‹£°");
				dispatcher.forward(request,response);
			}		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
}