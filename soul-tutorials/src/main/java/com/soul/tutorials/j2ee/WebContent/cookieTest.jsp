<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>ʹ��Cookie��¼�û����ʱ���ҳ�Ĵ���</title>
	</head>

	<body>
		<%  Cookie temp = null;
			//ȡ��Cookie����
			Cookie[] cookies = request.getCookies();
			int cookielen = cookies.length;
			int count = 0;
			String date = "";
			if (cookielen != 0) {
				// ����ȡ��cookies�����е�Cookie����
				for (int i = 0; i < cookielen; i++) {
					temp = cookies[i];
					// �ҵ�����ҳ���õ�cookie��
					if (temp.getName().equals("accessCount")) {
						// �õ���ǰ�ķ�����
						count = Integer.parseInt(temp.getValue());
					} else if(temp.getName().equals("accessDate")){
						date = temp.getValue();
					}
					// �ҵ�������ֵ���˳�����
					if ((count != 0) && !date.equals("")){
						break;
					}
				}
			}
			// ��ʾ�û��ĵ�½����
			if (count == 0) {
				out.println("�׶ȹ��ٵ������ѣ���ӭ�������ҵ���ҳ��");
			} else {
				out.println("�������� <font color = red>" + (count + 1)
						+ "</font> �η����ҵ���ҳ��<BR/><BR/>");
				out.println("���ϴη��ʵ�ʱ���� <font color = red>" + date
						+ "</font> ");
			}
			// ������ʴ���С��500�������cookie
			if (count < 500) {
				Cookie accessCountCookie = new Cookie("accessCount", String
						.valueOf(count + 1));
				// ��Cookie����Чʱ���趨Ϊ1��
				int validateTime = 7 * 24 * 60 * 60;
				accessCountCookie.setMaxAge(validateTime);
				// ����Cookie�ķ���·�������û����ʱ���������/book·��ʱ����Cookie�������request��
				accessCountCookie.setPath("/book");
				// �����º�����ݴ���cookie���������ڿͻ���
				response.addCookie(accessCountCookie);
				
				// ���·���ʱ��cookie
				Cookie accessDateCookie = new Cookie("accessDate", new Date().toString());
				accessDateCookie.setMaxAge(validateTime);
				accessDateCookie.setPath("/book");
				response.addCookie(accessDateCookie);

			} else {
				// ������������500ʱ����cookieɾ��
				Cookie killMyCookie = new Cookie("accessCount", null);
				killMyCookie.setMaxAge(0);
				killMyCookie.setPath("/book");
				response.addCookie(killMyCookie);
				killMyCookie = new Cookie("accessDate", null);
				killMyCookie.setMaxAge(0);
				killMyCookie.setPath("/book");
				response.addCookie(killMyCookie);
			}
		%>
		<br>
	</body>
</html>
