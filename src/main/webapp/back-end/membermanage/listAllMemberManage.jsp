<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.membermanage.model.*"%>

<html>
<head>
    <title>會員列表</title>
</head>
<body>
    <h1>會員列表</h1>
    <table border="1">
        <thead>
            <tr>
                <th>會員編號</th>
                <th>會員姓名</th>
                <th>會員等級</th>
                <th>會員狀態</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="member" items="${memberList}">
                <tr>
                    <td>${member.memberId}</td>
                    <td>${member.memberName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${member.memberLvId == 0}">一般會員</c:when>
                            <c:otherwise>白金會員</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${member.memberStatus == 0}">正常</c:when>
                            <c:otherwise>停權</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/back-end/membermanage.do?action=getOne&memberId=${member.memberId}">
                            查詢/修改
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <!-- 返回首頁 -->
    <a href="${pageContext.request.contextPath}/back-end/membermanage/select_page.jsp">返回首頁</a>
</body>
</html>
