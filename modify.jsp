<%@page contentType="text/html;charset=gb2312" %>
<html>

<head>
<title>��Ϣ�޸�</title>
<style type="text/css"> 
body{ font-size:20px;} 
#a{text-align:center; font-size:23px;  font-family:"SimHei";}
#container{width:1000px;margin:0px auto; }
#header{width:1000px; height:100px; margin:0px auto; } 
#choose{width:1000px; height:50px; margin: 0px auto;}
#main{width:1000px; height:490px; margin: 0px auto;}
#mainin{width:800px;margin:0px auto;height:490px;line-height:40px;background:white;}
#in{width:400px;margin:10px auto;height:250px;font-family:"SimHei";text-align:left;}
#tip{width:800px;float:none;margin:0px auto;height:50px;text-align:center;}
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
          <td><a href="revert.jsp" >�豸�黹</a></td>
          <td><a href="broken.jsp" >���ϱ���</a></td>
          <td><a href="repair.jsp">ά�����</a></td>
          <td><a href="useless.jsp">�豸����</a></td>
          <td><a href="search.jsp">�豸��ѯ</a></td>
          <td><a href="modify.jsp" style="font-weight:bold;">��Ϣ�޸�</a></td>
        </tr>
      </tbody>
   </table>
  </div>
 <div id="main">
   <div id="mainin">
   <div id="ex" style="height:70px;"></div>
    <div id="tip" style="font-size:22px;font-weight:bold;">
         <form action = "search_Servlet" method = post>
                                                �������豸���ڲ���ţ�
              <input type = hidden name="condition" value="interNum" >
	          <input type = text name = "str" >
	          <input type = submit value = "�ύ" >
         </form><br>
         <a style="color:red;font-size:bold;">
           <% 
              if (request.getAttribute("search_bean")!=null){ %> 
              <jsp:useBean id="search_bean" class="mybean.jsp_Bean" scope="request"/>
              <% String str=search_bean.getResult();%>
              <%=str %>
            </a>
     </div>
    <div id="in">  
      <%
    String [][]record = search_bean.getTableRecord();
    if(record!=null){
	    int id = search_bean.getId();
       %>
      <form action = "modify_Servlet" method = post>
   
	               �������ţ�<input type = text name = "suborSector" value=<%=record[0][0] %>><br>
	               �ڲ���ţ�<input type = text name = "interNum" value=<%=record[0][1] %>><br>
	               ���λ�ã�<input type = text name = "storePlace" value=<%=record[0][2] %>><br>
	               ��������<input type = text name = "totalAmount" value=<%=record[0][3] %>><br>
	                �ɽ�����<input type = text name = "loanAmount" value=<%=record[0][4] %>><br>
	     <input type = hidden name = "id" value=<%=id %>>
	     <input type = submit value = "�ύ" >
      </form>
     <% 
     }
    } 
              if (request.getAttribute("modify_bean")!=null){ %> 
               <jsp:useBean id="modify_bean" class="mybean.jsp_Bean" scope="request"/>
    <a style="color:red;font-size:bold;">
              <% String s=modify_bean.getResult();%>
              <%=s %>
            </a>   
            <%} %>   
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