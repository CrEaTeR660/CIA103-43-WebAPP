<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllMember.jsp</title>
<!--css路徑  -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/back-end/member/css/listAllMember.css">


</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>	
		 <h3>所有員工資料 - listAllMember.jsp</h3>
		 <h4>
		 <a href="select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/member/images/back1.gif"  width="100" height="32" border="0">回首頁</a>
		 </h4>
	</td></tr>
</table>

<div style="overflow-x: auto;"> 
<!-- 表格內容過長捲動條  -->
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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memberVO.memberId}</td>
			<td>${memberVO.memberLvId}</td>
			<td>${memberVO.memberName}</td>
			<td>${memberVO.memberUid}</td>
			<td>${memberVO.memberBth}</td>
			<td>${memberVO.memberGender}</td> 
			<td>${memberVO.memberEmail}</td>
			<td>${memberVO.memberTel}</td>
			<td>${memberVO.memberAdd}</td>
			<td>${memberVO.memberAcc}</td>
			<td>${memberVO.memberPw}</td>
			<td>${memberVO.memberStatus}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/member.do" style= "margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memberId"  value="${memberVO.memberId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="memberId"  value="${memberVO.memberId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="page2.file" %>

</body>
</html>