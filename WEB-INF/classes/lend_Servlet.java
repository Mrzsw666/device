import mybean.jsp_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class lend_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		jsp_Bean lend_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("lend.jsp");
		try{
			lend_Bean=(jsp_Bean)request.getAttribute("lend_Bean");
			if(lend_Bean==null){
				lend_Bean=new jsp_Bean();
				request.setAttribute("lend_Bean",lend_Bean);
			}
		}
		catch(Exception exp){
			lend_Bean=new jsp_Bean();
			request.setAttribute("lend_Bean",lend_Bean);
		}
		request.setCharacterEncoding("gb2312");
		String dataBase="Device";
		String tableNameA="lendproduct";
		String tableNameB="product";
		String im=request.getParameter("interNum");
		String ut=request.getParameter("useDepart");
		String hp=request.getParameter("handPerson");
		String pp=request.getParameter("purpose");
		String yr=request.getParameter("year");
		String mh=request.getParameter("month");
		String dy=request.getParameter("day");
		String at=request.getParameter("amount");

		if(im==null||im.length()==0){
			lend_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(ut==null||ut.length()==0){
			lend_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(hp==null||hp.length()==0){
			lend_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(pp==null||pp.length()==0){
			lend_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(yr==null||yr.length()==0){
			lend_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(mh==null||mh.length()==0){
			lend_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(dy==null||dy.length()==0){
			lend_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		if(at==null||at.length()==0){
			lend_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
			return;
		}
		int a=Integer.parseInt(yr);
		int b=Integer.parseInt(mh);
		int c=Integer.parseInt(dy);
		int d=Integer.parseInt(at);		
		
		
		String conditionA="INSERT INTO "+tableNameA+" VALUES"+"("+"'"+im+"',"+d+",'"+ut+"','"+hp+"','"+pp+"',"+a+","+b+","+c+","+null+")";
		Connection con;

		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=gb2312&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String conditionB="SELECT * FROM "+tableNameB+" WHERE interNum="+im;
			ResultSet rs=sql.executeQuery(conditionB);
			if(!rs.next()){
				lend_Bean.setResult(" ß∞‹£°«Î ‰»Î’˝»∑µƒ…Ë±∏±‡∫≈");
				dispatcher.forward(request,response);
				return;
			}
			rs.first();
			String number=rs.getString(8);
			int x=Integer.parseInt(number);
			int y=Integer.parseInt(at);
			if(x<y){
				con.close();
				lend_Bean.setResult(" ß∞‹£°ø…ΩË ˝¡ø≤ª◊„");
				dispatcher.forward(request,response);
				return;
			}
			x=x-y;
			number = Integer.toString(x);
			sql.executeUpdate("UPDATE "+tableNameB+" SET loanAmount = "+number+" WHERE interNum = "+im);
			
            sql.executeUpdate(conditionA);
			con.close();
			lend_Bean.setResult("≥…π¶£°");
			dispatcher.forward(request,response);

		}
		catch(SQLException ee){
			System.out.println(ee);
			lend_Bean.setResult(" ß∞‹£°");
			dispatcher.forward(request,response);
		}

		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}