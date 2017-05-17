<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Content</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link href="${pageContext.request.contextPath }/res/images/skin.css" rel="stylesheet" type="text/css" /> 
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="${pageContext.request.contextPath }/res/images/mail_leftbg.gif"><img src="${pageContext.request.contextPath }/res/images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="${pageContext.request.contextPath }/res/images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">欢迎界面</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="${pageContext.request.contextPath }/res/images/mail_rightbg.gif"><img src="${pageContext.request.contextPath }/res/images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
     
     
     
     
     
     <c:import url="http://www.baidu.com" charEncoding="UTF-8"  >  
     </c:import>
     
     
     
  </tr>
  <tr>
    <td valign="bottom" background="${pageContext.request.contextPath }/res/images/mail_leftbg.gif"><img src="${pageContext.request.contextPath }/res/images/buttom_left2.gif" width="17" height="17" /></td>
    <td background="${pageContext.request.contextPath }/res/images/buttom_bgs.gif"><img src="${pageContext.request.contextPath }/res/images/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="${pageContext.request.contextPath }/res/images/mail_rightbg.gif"><img src="${pageContext.request.contextPath }/res/images/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
</body>
