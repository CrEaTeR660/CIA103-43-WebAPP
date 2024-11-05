<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.complaint.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ComplaintService complaintSvc = new ComplaintService();
List<ComplaintVO> list = complaintSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有申訴資料 - listAllComplaint.jsp</title>

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
	width: 800px;
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

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有申訴資料 - listAllComplaint.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img
						src="<%=request.getContextPath()%>/back-end/images/back1.gif"
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
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="complaintVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${complaintVO.complaintId}</td>
<%-- 				<td>${complaintVO.memberid}</td> --%>
<%-- 				<td>${complaintVO.caseid}</td> --%>
				<td>${complaintVO.complaintCon}</td>
				<td>${complaintVO.complaintTime}</td>
				<td>${complaintVO.complaintStatus}</td>
				<td>${complaintVO.complaintResult}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="complaintId" value="${complaintVO.complaintId}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="complaintId" value="${complaintVO.complaintId}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>