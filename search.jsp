<%@page contentType="text/html;charset=gb2312" %>
<html>

<head>
<title>�豸��ѯ</title>
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
          <td><a href="show.jsp">&nbsp��ҳ&nbsp</a></td>
          <td><a href="purchase.jsp">�����豸</a></td>
          <td><a href="lend.jsp">�豸���</a></td>
          <td><a href="revert.jsp">�豸�黹</a></td>
          <td><a href="broken.jsp" >���ϱ���</a></td>
          <td><a href="repair.jsp">ά�����</a></td>
          <td><a href="useless.jsp">�豸����</a></td>
          <td><a href="search.jsp" style="font-weight:bold;">�豸��ѯ</a></td>
          <td><a href="modify.jsp">��Ϣ�޸�</a></td>
        </tr>
      </tbody>
   </table>
  </div>
 <div id="main">
   <div id="mainin">
    <div id="tip" style="font-size:22px;font-weight:bold;">
    <form action = "search_Servlet" method = post >	
<br>    
��ѡ���ѯ�豸��������������ֵ��
<select name = "condition" style="width:120px" >
	<option value="lab"> ��������</option>
	<option value="deviceType"> �豸���� </option>
	<option value="purchaseDate"> �������� </option>
	<option value="brand"> Ʒ�� </option>
	<option value="broken"> ���� </option>
	<option value="useless"> ���� </option>
</select>
<br>�������ѯ�ؼ��ʵ�ֵ��	
<input type = text name = "str" >
<input type = submit value = "�ύ" >
</form>              

     </div>
    <div id="in">
      <% 
if(request.getAttribute("search_bean")!=null){
%>
<br>���ϲ�ѯ�������豸���£�  
<jsp:useBean id="search_bean" class="mybean.jsp_Bean" scope="request"/>
<table border=1 align="center">
<% String []chinese={"��","��","��","����","Ʒ��","�ͺ�","����","������","�ɽ���","���","��������","������","���λ��",}; %>
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
<p style="text-align:center;">��Ȩ���� &nbsp��������&nbsp��������&nbsp��ϵ����  </p>
 </div>
</div>
</body>
</html>
</html>