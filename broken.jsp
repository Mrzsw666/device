<%@page contentType="text/html;charset=gb2312" %>
<html>

<head>
<title>���ϱ���</title>
<style type="text/css"> 
body{ font-size:20px;} 
#a{text-align:center; font-size:23px;  font-family:"SimHei";}
#container{width:1000px;margin:0px auto; }
#header{width:1000px; height:100px; margin:0px auto; } 
#choose{width:1000px; height:50px; margin: 0px auto;}
#main{width:1000px; height:490px; margin: 0px auto;}
#mainin{width:800px;margin:0px auto;height:490px;line-height:40px;background:white;}
#in{width:400px;margin:0px auto;height:450px;font-family:"SimHei";}
#tip{width:300px;float:none;margin:5px 5px;height:30px;}
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
          <td><a href="lend.jsp" >�豸���</a></td>
          <td><a href="revert.jsp">�豸�黹</a></td>
          <td><a href="broken.jsp" style="font-weight:bold;" >���ϱ���</a></td>
          <td><a href="repair.jsp">ά�����</a></td>
          <td><a href="useless.jsp">�豸����</a></td>
          <td><a href="search.jsp">�豸��ѯ</a></td>
          <td><a href="modify.jsp">��Ϣ�޸�</a></td>
        </tr>
      </tbody>
   </table>
  </div>
 <div id="main">
   <div id="mainin">
    <div id="tip" style="font-size:22px;font-weight:bold;">
                    �����뱨���豸����Ϣ:
     </div>
    <div id="in">
        <form action="broken_Servlet" method=post>     
                     <br>�豸��ţ�<input type=text name="interNum" size=9>
                     <br>���������
                     <br><textarea name="faultCase" rows="5" cols="30"></textarea>
                     <br><input id="button" type="submit" value="�ύ" name="submit">              
            <a style="color:red;font-size:bold;"> &nbsp
             <% 
              if (request.getAttribute("broken_Bean")!=null){ %> 
                         <jsp:useBean id="broken_Bean" class="mybean.jsp_Bean" scope="request"/>
              <% String str=broken_Bean.getResult();%>
              <%=str %> <%} %>             
            </a>            
         </form>
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