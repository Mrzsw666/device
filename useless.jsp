<%@page contentType="text/html;charset=gb2312" %>
<html>

<head>
<title>�豸����</title>
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
          <td><a href="lend.jsp">�豸���</a></td>
          <td><a href="revert.jsp">�豸�黹</a></td>
          <td><a href="broken.jsp">���ϱ���</a></td>
          <td><a href="repair.jsp">ά�����</a></td>
          <td><a href="useless.jsp" style="font-weight:bold;">�豸����</a></td>
          <td><a href="search.jsp" >�豸��ѯ</a></td>
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
        <form action="useless_Servlet" method=post>    
                                                                         ����ԭ��<input type="text" name="scrapReason">
                     <br>��׼��Ա��<input type="text" name="approvePerson" size=15>
                     <br>������Ա��<input type="text" name="handPerson" size=15>
                     <br>����ʽ��<input type="text" name="treatMode" size=15>
                     <br>����������<input type="text" name="totalQuantify" size=3>
                     <br>�ڲ���ţ�<input type="text" name="interNum" size=3> 
                     <br>�������ڣ�<input type=text name="year" size=2>��<input type=text name="month" size=2>��
                                <input type=text name="day" size=2>��
                    <br><br><input id="button" type="submit" value="�ύ" name="submit"> 
            <a style="color:red;font-size:bold;"> &nbsp
           <% 
              if (request.getAttribute("useless_bean")!=null){ %> 
                         <jsp:useBean id="useless_bean" class="mybean.jsp_Bean" scope="request"/>
              <% String str=useless_bean.getResult();%>
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