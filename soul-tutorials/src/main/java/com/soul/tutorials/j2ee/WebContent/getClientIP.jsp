<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��ȡ�ͻ��˵�IP��ַ</title>
	</head>

	<body>
		<%
			String realIP = request.getHeader("x-forwarded-for");
			if (realIP != null && realIP.length() != 0) {
				out.print("���IP��ַ�ǣ�" + realIP);
			} else {
				String ip = request.getRemoteAddr();
				out.print("���IP��ַ�ǣ�" + ip);
			}
		%>
		<br>
	</body>
</html>
