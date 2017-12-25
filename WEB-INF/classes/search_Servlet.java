import mybean.jsp_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Calendar;

public class search_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		request.setCharacterEncoding("utf-8");
		String cate=request.getParameter("condition");
		String idea=request.getParameter("str");
		try{
		jsp_Bean search_bean=null;
		search_bean=(jsp_Bean)request.getAttribute("search_bean");
		search_bean=new jsp_Bean();
		request.setAttribute("search_bean",search_bean);
		if(idea==null||idea.length()==0||cate==null||cate.length()==0){
			throw new Exception("您填写的信息不完整!");
		}
		Connection con;
		ResultSet rs;
		PreparedStatement sql;
		String dataBase="device";
		String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=gb2312&useSSL=true";
		con=DriverManager.getConnection(url);;
		if(cate.equals("lab")){
			String tableName="product";
			sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE suborSector='"+idea+"'");
			rs=sql.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String[][]tableRecord=search_bean.getTableRecord();
			String[] columnName=new String [columnCount];
			for(int i=0;i<columnCount;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			search_bean.setColumnName(columnName);
			rs.last();
			int rowNumber=rs.getRow();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++){	
					tableRecord[i][k]=rs.getString(k+1);
				}
				i++;
			}
			search_bean.setTableRecord(tableRecord);
		} 
		else if(cate.equals("deviceType")){
			String tableName="product";
			sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE deviceType='"+idea+"'");
			rs=sql.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String[] columnName=new String [columnCount];
			for(int i=0;i<columnCount;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			search_bean.setColumnName(columnName);
			rs.last();
			int rowNumber=rs.getRow();
			String[][]tableRecord=search_bean.getTableRecord();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++){
					tableRecord[i][k]=rs.getString(k+1);
				}
				i++;
			}
			search_bean.setTableRecord(tableRecord);
		}
		else if(cate.equals("purchaseDate")){
			String tableName="product";
			int temp=0;
			String[] s=new String[3];
			s[0]=s[1]=s[2]="";
			char[] str=idea.toCharArray();
			for(int i=0;i<idea.length();i++){
				if(str[i]=='.'){
					temp++;
					continue;
				}
				s[temp]=s[temp]+str[i];
			}
			String year=s[0],month=s[1],day=s[2];
			int y=Integer.parseInt(year);
			int m=Integer.parseInt(month);
			int d=Integer.parseInt(day);
			sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE year="+y+" and month="+m+" and day="+d);
			rs=sql.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String[] columnName=new String [columnCount];
			for(int i=0;i<columnCount;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			search_bean.setColumnName(columnName);
			rs.last();
			int rowNumber=rs.getRow();
			String[][]tableRecord=search_bean.getTableRecord();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++){
					tableRecord[i][k]=rs.getString(k+1);
				}
				i++;
			}
			search_bean.setTableRecord(tableRecord);
		}
		else if(cate.equals("brand")){
			String tableName="product";
			sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE brand ='"+idea+"'");
			rs=sql.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String[] columnName=new String [columnCount];
			for(int i=0;i<columnCount;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			search_bean.setColumnName(columnName);
			rs.last();
			int rowNumber=rs.getRow();
			String[][]tableRecord=search_bean.getTableRecord();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++){
					tableRecord[i][k]=rs.getString(k+1);
				}
				i++;
			}
			search_bean.setTableRecord(tableRecord);
		}
		else if(cate.equals("broken")){
			String tableName="repairproduct";
			sql=con.prepareStatement("SELECT * FROM "+tableName);
			rs=sql.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String[] columnName=new String [columnCount];
			for(int i=0;i<columnCount;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			search_bean.setColumnName(columnName);
			rs.last();
			int rowNumber=rs.getRow();
			String[][]tableRecord=search_bean.getTableRecord();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++){
					tableRecord[i][k]=rs.getString(k+1);
				}
				i++;
			}
			search_bean.setTableRecord(tableRecord);
		}
		else if(cate.equals("useless")){
			String tableName="scrapproduct";
			sql=con.prepareStatement("SELECT * FROM "+tableName);
			rs=sql.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String[] columnName=new String [columnCount];
			for(int i=0;i<columnCount;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			search_bean.setColumnName(columnName);
			rs.last();
			int rowNumber=rs.getRow();
			String[][]tableRecord=search_bean.getTableRecord();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++){
					tableRecord[i][k]=rs.getString(k+1);
				}
				i++;
			}
			search_bean.setTableRecord(tableRecord);
		}
		else if(cate.equals("interNum")){
			String index=request.getParameter("str");
			sql=con.prepareStatement("SELECT suborSector,interNum,storePlace,totalAmount,loanAmount,id FROM product where interNum='"+idea+"'");
			rs=sql.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String[] columnName=new String [5];
			columnName[0]="suborSector";columnName[1]="interNum";columnName[2]="storePlace";columnName[3]="totalAmount";columnName[4]="loanAmount";
			search_bean.setColumnName(columnName);
			if(!rs.next()){
				throw new Exception("未找到对应设备");
			}
			rs.first();
			String[][]tableRecord=search_bean.getTableRecord();
			tableRecord=new String[1][5];
			for(int k=0;k<5;k++){
				tableRecord[0][k]=rs.getString(k+1);
			}
			String iid=rs.getString(6);
			int id=Integer.parseInt(iid);
			search_bean.setId(id);
			search_bean.setTableRecord(tableRecord);
			con.close();
			RequestDispatcher dispatcher=request.getRequestDispatcher("modify.jsp");
			dispatcher.forward(request,response);
			return;
		}
		con.close();
		}
		catch(Exception e){
			System.out.println(e);
			jsp_Bean search_bean=null;
			search_bean=(jsp_Bean)request.getAttribute("search_bean");
			search_bean=new jsp_Bean();
			request.setAttribute("search_bean",search_bean);
			if(cate.equals("interNum")){
				search_bean.setResult("您填写的信息不完整！");
				RequestDispatcher dispatcher=request.getRequestDispatcher("modify.jsp");
				dispatcher.forward(request,response);
			}
			else {
				search_bean.setResult("未找到对应设备，请检查您的输入！");
				RequestDispatcher dispatcher=request.getRequestDispatcher("search.jsp");
				dispatcher.forward(request,response);
			}
			return;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("search.jsp");
		dispatcher.forward(request,response);
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
}
