<%@ page contentType="text/html; charset=gb2312" pageEncoding="gb2312" language="java" 
import="book.servlet.page.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%// ���session������Ϊfalse����ʾ���session�����ڣ��򲻴�����
			session = request.getSession(false);
			PageableResultSet pageResultSet = (PageableResultSet) session
					.getAttribute("pageResultSet");
			boolean hasResult = (pageResultSet == null) ? false : true;
			int pageIndex = 0;
			int pageCount = 0;
			if (hasResult) {
				pageIndex = Integer.parseInt((String) request
						.getParameter("pageIndex"));
				pageResultSet.gotoPage(pageIndex);
				pageCount = pageResultSet.getPageCount();
			}
			%>
	<head>
		<title>���ݿ�鿴��</title>
			
	<script language="javascript">
	<%if (hasResult){%>
	function firstpage(){
		alert("hehe");
		fobj = document.pageForm;
		fobj.pageIndex.value = "1";
		fobj.submit();	
	}
	function lastpage(){
		fobj = document.pageForm;
		fobj.pageIndex.value = "<%= pageCount%>";
		fobj.submit();	
	}
	function prepage(){
		alert("hehe");
		fobj = document.pageForm;
		fobj.pageIndex.value = "<%= (pageIndex - 1)%>";
		fobj.submit();	
	}
	function nextpage(){
		fobj = document.pageForm;
		alert("hehe");
		fobj.pageIndex.value = "<%= (pageIndex + 1)%>";
		fobj.submit();	
	}
	<%}%>
	</script>
		
	</head>


	<body>
		<%@ include file="header.jsp" %>
		<br/>
		<FORM name="queryForm" METHOD="POST" action="/book/queryDB">
			������SQL��䣺
			<input type="text" name="querySQL" size="50">
			<input type="hidden" name="servleturl" value="/db/queryDB.jsp">
			<input type="hidden" name="errorurl" value="/db/queryDB.jsp">
			<input type="hidden" name="pageIndex" value="1">
			<input type="hidden" name="pageSize" value="5">
			<input type="Submit" name="Submit" value="��  ѯ"/>
		</FORM>

			<%//������ڴ�����Ϣ������error.jsp��ʾ������Ϣ
			String errormsg = (String) request.getAttribute("errormsg");
			if (errormsg != null) {%>
				<jsp:include page="error.jsp"/>
			<%}%>

			<%if (hasResult) {%>
			<FORM name="pageForm" METHOD="POST" action="/book/db/queryDB.jsp">
			
			<%
				int begin = (pageResultSet.getCurPage() - 1) * pageResultSet.getPageSize()+1;
				int end = (pageResultSet.getCurPage() - 1) * pageResultSet.getPageSize()
						 + pageResultSet.getPageRowsCount();
				out.println(new HtmlResultSet(pageResultSet).toString(begin, end));%>
				
				<BR />
				��ǰҳ��<font color="red"><%=pageIndex%></font>&nbsp;&nbsp;&nbsp;&nbsp; 
				��ҳ����<font color="red"><%=pageCount%></font>
				<BR />
				<input type="hidden" name="pageIndex" value=""/>
			</FORM>

				<input type="button" name="firstpage" value="��һҳ" onclick="javascript:firstpage()" 
				 <%if (pageIndex == 1){%> disabled <%}%> />
				<input type="button" name="prepage" value="��һҳ" onclick="javascript:prepage()"
				 <%if (pageIndex <= 1){%> disabled <%}%>/>
				<input type="button" name="nextpage" value="��һҳ" onclick="javascript:nextpage()" 
				<%if (pageIndex >= pageCount){out.print(" disabled");}%> />
				<input type="button" name="lastpage" value="���һҳ" onclick="javascript:lastpage()" 
				 <%if (pageIndex == pageCount){%> disabled <%}%>/>

			<%}%>
	</body>
</html>
