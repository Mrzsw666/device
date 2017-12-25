import mybean.jsp_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Calendar;

public class show_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public long getDaySub(String beginDateStr)
    {
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
        Calendar c = Calendar.getInstance();
        String endDateStr= String.valueOf(c.get(Calendar.YEAR))+"-"+String.valueOf(c.get(Calendar.MONTH)+1)+"-"+String.valueOf(c.get(Calendar.DATE));
        java.util.Date beginDate;
        java.util.Date endDate;
        try
        {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);    
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);      
        } catch (Exception e)
        {
            e.printStackTrace();
        }   
        return day;
    }
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		request.setCharacterEncoding("gb2312");
		try{
		jsp_Bean show_bean=null;
		show_bean=(jsp_Bean)request.getAttribute("show_bean");
		show_bean=new jsp_Bean();
		request.setAttribute("show_bean",show_bean);
		Connection con;
		ResultSet rs;
		PreparedStatement sql;
		String dataBase="Device";
		String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=gb2312&useSSL=true";
		con=DriverManager.getConnection(url);
		String tableName="product";
		sql=con.prepareStatement("SELECT * FROM "+tableName);
		rs=sql.executeQuery();
		if(!rs.next()){
			show_bean.setResult("ÔÝÎÞ¼ÇÂ¼");
			RequestDispatcher dispatcher=request.getRequestDispatcher("show.jsp");
			dispatcher.forward(request,response);
			return;
		}
		ResultSetMetaData metaData=rs.getMetaData();
		int columnCount=metaData.getColumnCount();
		String[] columnName=new String [columnCount];
		for(int i=0;i<columnCount;i++){
			columnName[i]=metaData.getColumnName(i+1);
		}
		show_bean.setColumnName(columnName);
		rs.last();
		int rowNumber=rs.getRow();
		String[][]tableRecord=show_bean.getTableRecord();
		tableRecord=new String[rowNumber][columnCount];
		rs.beforeFirst();
		int i=0,w=0,v=0;
		while(rs.next()){
			for(int k=0;k<columnCount;k++){
				tableRecord[i][k]=rs.getString(k+1);
			}
			i++;
		}
		show_bean.setTableRecord(tableRecord);
		
		jsp_Bean show_bean2=null;
		show_bean2=(jsp_Bean)request.getAttribute("show_bean2");
		show_bean2=new jsp_Bean();
		String[][]breaking=show_bean.getTableRecord();
		request.setAttribute("show_bean2",show_bean2);
		tableName="scrapproduct";
		sql=con.prepareStatement("SELECT * FROM "+tableName);
		rs=sql.executeQuery();
		metaData=rs.getMetaData();
		columnCount=metaData.getColumnCount();
		columnName=new String [columnCount];
		for(int k=0;k<columnCount;k++){			
			columnName[k]=metaData.getColumnName(k+1);
		}
		show_bean2.setColumnName(columnName);
		rs.last();
		rowNumber=rs.getRow();
		breaking=new String[rowNumber][columnCount];
		rs.beforeFirst();
		i=0;w=0;v=0;
		String str;
		while(rs.next()){
			str=rs.getString(6)+"-"+rs.getString(7)+"-"+rs.getString(8);
			if(getDaySub(str)<=30){
			for(int k=0;k<columnCount;k++){
				breaking[i][k]=rs.getString(k+1);
			}
			i++;
			}
		}
		show_bean2.setTableRecord(breaking);
		con.close();
		}
		catch(Exception e){
			System.out.println(e);
			jsp_Bean show_bean2=null;
			show_bean2=(jsp_Bean)request.getAttribute("show_bean2");
			show_bean2=new jsp_Bean();
			show_bean2.setResult(e.toString());
			response.sendRedirect("show.jsp");
			return;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("show.jsp");
		dispatcher.forward(request,response);
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
}
