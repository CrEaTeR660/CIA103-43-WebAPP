<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.complaint.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ComplaintVO complaintVO = (ComplaintVO) request.getAttribute("ComplaintVO"); //ComplaintServlet.java(Concroller), 存入req的ComplaintVO物件
%>

<html>
<head>
<title>申訴資料 - listOneComplaint.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>申訴資料 - listOneComplaint.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>申訴編號</th>
<!-- 			<th>會員編號</th> -->
<!-- 			<th>案件編號</th> -->
			<th>申訴案件內容</th>
			<th>申訴日期</th>
			<th>申訴狀態</th>
			<th>處理結果</th>
		</tr>
		<tr>
			<td><%=complaintVO.getComplaintid()%></td>
			<%-- 		<td><%=complaintVO.getComplaintcon()%></td> --%>
			<%-- 		<td><%=complaintVO.getCaseid()%></td> --%>
			<td><%=complaintVO.getComplaintcon()%></td>
			<td><%=complaintVO.getComplainttime()%></td>
			<td><%=complaintVO.getComplaintstatus()%></td>
			<td><%=t(complaintVO.%></td>
		</tr>
	</table>

</body>
</html>