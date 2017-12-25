<%@page contentType="text/html;charset=gb2312" %>
<html>

<head>
<title>设备租借</title>
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
          <td><a href="show.jsp">&nbsp首页&nbsp</a></td>
          <td><a href="purchase.jsp">新增设备</a></td>
          <td><a href="lend.jsp" style="font-weight:bold;"">设备租借</a></td>
          <td><a href="revert.jsp">设备归还</a></td>
          <td><a href="broken.jsp" >故障报修</a></td>
          <td><a href="repair.jsp">维修情况</a></td>
          <td><a href="useless.jsp">设备报废</a></td>
          <td><a href="search.jsp">设备查询</a></td>
          <td><a href="modify.jsp">信息修改</a></td>
        </tr>
      </tbody>
   </table>
  </div>
 <div id="main">
   <div id="mainin">
    <div id="tip" style="font-size:22px;font-weight:bold;">
                    请输入租借设备的信息:
     </div>
    <div id="in">
        <form action="lend_Servlet" method=post>     
                     <br>设备编号：<input type=text name="interNum" size=9>
                     <br>租借数量：<input type=text name="amount" size=9>
                     <br>借用部门：<input type=text name="useDepart" size=9>
                     <br>经手人员：<input type=text name="handPerson" size=9>                     
                     <br>拟还日期：<input type=text name="year" size=2>年<input type=text name="month" size=2>月
                                <input type=text name="day" size=2>日
                     <br>用途：
                     <br><textarea name="purpose" rows="5" cols="30"></textarea>
                    <br><br><input id="button" type="submit" value="提交" name="submit"> 
            <a style="color:red;font-size:bold;"> &nbsp
             <% 
              if (request.getAttribute("lend_Bean")!=null){ %> 
                         <jsp:useBean id="lend_Bean" class="mybean.jsp_Bean" scope="request"/>
              <% String str=lend_Bean.getResult();%>
              <%=str %> <%} %>
             
            </a>
            
         </form>
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