<%@ page contentType="text/html; charset=GB2312" language="java"%>
<%@ page import ="java.util.*" %>

<jsp:useBean id="msgs" class="java.util.HashMap" scope="application" />

<%	
	// �����msgs������ΪӦ�ó�������Ч
	// ��һ������Ӧ�ó�����Ч�ı����ķ�����ʹ��application�������÷���sessionһ��
	// application.setAttribute("msgs", new HashMap());
	// msgs = (HashMap)application.getAttribute("msgs");
	
	request.setCharacterEncoding("GBK");
	String action = request.getParameter("action");
	
	if(action.equals("login")){
	   // �û���½������û�����Ȼ�󴴽�����session�����������û���½��Ϣ��������Ϣ
		String username = request.getParameter("username");
		String msg="��ӭ "+username+" ���ٱ������ң�<br>";
		session.setAttribute("username",username);
		msgs.put(username, msg);
		response.sendRedirect("main.html");
	}
	if(action.equals("sendMsg")){
		String newMsg = session.getAttribute("username")+ ": "
				+(String)request.getParameter("msg");
	    // ������Ϣʱ���������������˵���Ϣ�������µķ�������
		Iterator it = msgs.keySet().iterator();
		String username = null;
		String msg = null;
		while (it.hasNext()){
			username = (String)it.next();
			msg = (String)msgs.get(username);
			msg = msg+"<br>"+ newMsg;
			msgs.put(username, msg);
		}
		response.sendRedirect("inputMsg.jsp");
	}
	if(action.equals("showMsg")){
	    // ��ʾĳ���û�����Ϣ 
		String username = (String)session.getAttribute("username");
	    String msg = (String)msgs.get(username);
		out.println("loadContent.innerHTML=\""+msg+"\";");
	}
%>