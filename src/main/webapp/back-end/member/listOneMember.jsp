<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>會員資料 - listOneMember.jsp</title>
<!-- css -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/back-end/member/css/listOneMember.css">


</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>會員資料 - listOneMember.jsp</h3>
		 <a href="select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/member/images/back1.gif"  width="100" height="32" border="0">回首頁</a>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員等級編號</th>
		<th>會員姓名</th>
		<th>身分證字號</th>
		<th>生日</th>
		<th>性別</th>
		<th>電子郵件</th>
		<th>手機電話</th>
		<th>地址</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>會員狀態</th>
	</tr>
	<tr>
		<td><%=memberVO.getMemberId()%></td>
		<td><%=memberVO.getMemberLvId()%></td>
		<td><%=memberVO.getMemberName()%></td>
		<td><%=memberVO.getMemberUid()%></td>
		<td><%=memberVO.getMemberBth()%></td>
		<td><%=memberVO.getMemberGender()%></td>
		<td><%=memberVO.getMemberEmail()%></td>
		<td><%=memberVO.getMemberTel()%></td>
		<td><%=memberVO.getMemberAdd()%></td>
		<td><%=memberVO.getMemberAcc()%></td>
		<td><%=memberVO.getMemberPw()%></td>
		<td><%=memberVO.getMemberStatus()%></td>
	</tr>
</table>

</body>
</html>