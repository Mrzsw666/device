<%@page contentType="text/html;charset=gb2312" %>
<html>

<head>
<title>设备查询</title>
<style type="text/css"> 
body{ font-size:20px;} 
#a{text-align:center; font-size:23px;  font-family:"SimHei";}
#container{width:1000px;margin:0px auto; }
#header{width:1000px; height:100px; margin:0px auto; } 
#choose{width:1000px; height:50px; margin: 0px auto;}
#main{width:1000px; height:490px; margin: 0px auto;}
#mainin{width:800px;margin:0px auto;height:490px;line-height:40px;background:white;}
#in{width:800px;margin:0px auto;font-family:"SimHei"; text-align:center;}
#tip{width:400px;float:none;margin:5px auto;height:200px;}
#button{width:63px; height:36px; border:0px solid; background:blue; color:white;font-size:18px;font-weight:bold;}
#footer{width:1000px;height:25px;margin:0px auto; color:white;}
a:link{color:blue;text-decoration:none;}
a:visited{color:blue;text-decoration:none;}
a:hover{color:blue;text-decoration:none;font-weight:bold;}
</style>
</head>

<body background="background.jpg">
<div id="container" >
 <div id="header">
   <p style="text-align:center;"><img src="headpicture.jpg" /></p>
 </div>
 <div id="choose"> 
   <table id="a" border=0 width=100% height=80%> 
      <tbody>
        <tr bgcolor="white">
          <td><a href="show.jsp">&nbsp首页&nbsp</a></td>
          <td><a href="purchase.jsp">新增设备</a></td>
          <td><a href="lend.jsp">设备租借</a></td>
          <td><a href="revert.jsp">设备归还</a></td>
          <td><a href="broken.jsp" >故障报修</a></td>
          <td><a href="repair.jsp">维修情况</a></td>
          <td><a href="useless.jsp">设备报废</a></td>
          <td><a href="search.jsp" style="font-weight:bold;">设备查询</a></td>
          <td><a href="modify.jsp">信息修改</a></td>
        </tr>
      </tbody>
   </table>
  </div>
 <div id="main">
   <div id="mainin">
    <div id="tip" style="font-size:22px;font-weight:bold;">
    <form action = "search_Servlet" method = post >	
<br>    
请选择查询设备的条件并输入数值：
<select name = "condition" style="width:120px" >
	<option value="lab"> 所属部门</option>
	<option value="deviceType"> 设备类型 </option>
	<option value="purchaseDate"> 购入日期 </option>
	<option value="brand"> 品牌 </option>
	<option value="broken"> 故障 </option>
	<option value="useless"> 报废 </option>
</select>
<br>请输入查询关键词的值：	
<input type = text name = "str" >
<input type = submit value = "提交" >
</form>              

     </div>
    <div id="in">
      <% 
if(request.getAttribute("search_bean")!=null){
%>
<br>符合查询条件的设备如下：  
<jsp:useBean id="search_bean" class="mybean.jsp_Bean" scope="request"/>
<table border=1 align="center">
<% String []chinese={"年","月","日","名称","品牌","型号","类型","总数量","可借量","编号","所属部门","领用人","存放位置",}; %>
<% String []columnName=search_bean.getColumnName();%>
<tr>
<%if(chinese!=null)
	for(String s:chinese){  
%>      
      <td><%=s %></td>
<% }
%> </tr>
<%String [][]record=search_bean.getTableRecord();

  if(record!=null)
	  for(int i=0;i<record.length;i++){
%>  <tr>
<%   for(int j=0;j<record[i].length-1;j++){
%>     <td> <%=record[i][j]%> </td>
<%   }
%>   </tr>
<%  
   }
  }
%>
</table>
        
    </div>
   </div>
 </div>
 <div id="footer">
<p style="text-align:center;">版权声明 &nbsp免责声明&nbsp关于我们&nbsp联系我们  </p>
 </div>
</div>
</body>
</html>
</html>