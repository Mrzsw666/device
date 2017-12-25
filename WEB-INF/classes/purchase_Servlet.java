import mybean.jsp_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class purchase_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		jsp_Bean purchase_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("purchase.jsp");
		try{
			purchase_Bean=(jsp_Bean)request.getAttribute("purchase_Bean");
			if(purchase_Bean==null){
				purchase_Bean=new jsp_Bean();
				request.setAttribute("purchase_Bean",purchase_Bean);
			}
		}
		catch(Exception exp){
			purchase_Bean=new jsp_Bean();
			request.setAttribute("purchase_Bean",purchase_Bean);
		}
		request.setCharacterEncoding("gb2312");
		String dataBase="Device";
		String tableName="product";
		String yr=request.getParameter("year");
		String mh=request.getParameter("month");
		String dy=request.getParameter("day");
		String ne=request.getParameter("name");
		String bd=request.getParameter("brand");
		String ml=request.getParameter("model");
		String de=request.getParameter("deviceType");
		String tt=request.getParameter("totalAmount");
		String lt=request.getParameter("loanAmount");
		String im=request.getParameter("interNum");
		String sr=request.getParameter("suborSector");
		String ln=request.getParameter("leadPerson");
		String se=request.getParameter("storePlace");
		if(yr==null||yr.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(mh==null||mh.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(dy==null||dy.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(ne==null||ne.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(bd==null||bd.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(ml==null||ml.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(de==null||de.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(tt==null||tt.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(lt==null||lt.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(im==null||im.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(sr==null||sr.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(ln==null||ln.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		if(se==null||se.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		
		int a=Integer.parseInt(yr);
		int b=Integer.parseInt(mh);
		int c=Integer.parseInt(dy);
		int d=Integer.parseInt(tt);
		int e=Integer.parseInt(lt);
		if(ne==null||ne.length()==0){
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		String condition="INSERT INTO "+tableName+" VALUES"+"("+a+","+b+","+c+",'"+ne+"','"+bd+"','"+ml+"','"+de+"',"+d+","+e+",'"+im+"','"+sr+"','"+ln+"','"+se+"'"+","+null+")";
		Connection con;
		
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=gb2312&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			sql.executeUpdate(condition);
			ResultSet rs=sql.executeQuery("SELECT*FROM "+tableName);
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String []columnName=new String[columnCount];
			for(int i=0;i<columnName.length;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			purchase_Bean.setColumnName(columnName);
			rs.last();
			int rowNumber=rs.getRow();
			String [][]tableRecord=purchase_Bean.getTableRecord();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++)
					tableRecord[i][k]=rs.getString(k+1);
				i++;
			}
			purchase_Bean.setTableRecord(tableRecord);
			con.close();
			purchase_Bean.setResult("³É¹¦£¡");
			dispatcher.forward(request,response);
			return;
		}
		catch(SQLException ee){
			System.out.println(ee);
			purchase_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return ;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}
