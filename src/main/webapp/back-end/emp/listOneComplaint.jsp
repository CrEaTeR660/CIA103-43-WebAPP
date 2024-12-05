<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.complaint.model.*"%>

<%
ComplaintService complaintSvc = new ComplaintService();
List<ComplaintVO> list = complaintSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有申訴資料 - listAllComplaint.jsp</title>

<!-- 引入Bootstrap样式 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<style>
/* 自定义页面样式 */
body {
    background-color: #f4f7fc;
    font-family: 'Arial', sans-serif;
}

.container {
    margin-top: 50px;
    margin-bottom: 50px;
}

table {
    width: 100%;
    background-color: white;
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid #CCCCFF;
}

th, td {
    padding: 10px;
    text-align: center;
}

th {
    background-color: #f2f2f2;
}

h3 {
    color: #2c3e50;
    text-align: center;
    margin-bottom: 30px;
}

button.btn {
    background-color: #007bff;
    color: white;
    border-radius: 5px;
    padding: 10px 20px;
    border: none;
}

button.btn:hover {
    background-color: #0056b3;
}

footer {
    background-color: #2c3e50;
    color: white;
    padding: 15px;
    text-align: center;
}

footer a {
    color: #f39c12;
    text-decoration: none;
}

footer a:hover {
    text-decoration: underline;
}

</style>

</head>
<body>

<!-- 页面容器 -->
<div class="container">

    <h3>所有申訴資料</h3>

    <!-- 返回首页按钮 -->
    <div class="mb-4 text-center">
        <a href="select_page.jsp">
            <button class="btn">回首頁</button>
        </a>
    </div>

    <!-- 申訴資料表格 -->
    <table class="table table-bordered">
        <thead class="thead-light">
            <tr>
                <th>申訴編號</th>
                <th>會員編號</th>
                <th>案件編號</th>
                <th>申訴案件內容</th>
                <th>申訴日期</th>
                <th>申訴狀態</th>
                <th>處理結果</th>
                <th>修改</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="complaintVO" items="${list}">
                <tr>
                    <td>${complaintVO.complaintId}</td>
                    <td>${complaintVO.memberId}</td>
                    <td>${complaintVO.caseId}</td>
                    <td>${complaintVO.complaintCon}</td>
                    <td>
                        <fmt:formatDate value="${complaintVO.complaintTime}" pattern="yyyy-MM-dd" />
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${complaintVO.complaintStatus == 0}">申訴成功</c:when>
                            <c:when test="${complaintVO.complaintStatus == 1}">申訴失敗</c:when>
                            <c:otherwise>未知</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${complaintVO.complaintResult}</td>
                    <td>
                        <form method="post" action="<%=request.getContextPath()%>/back-end/emp/emp.do">
                            <button class="btn btn-sm btn-primary">修改</button>
                            <input type="hidden" name="complaintId" value="${complaintVO.complaintId}">
                            <input type="hidden" name="action" value="getOne_For_Update">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- 页脚 -->
<footer>
    <p>© 2024 所有版權歸公司所有. <a href="#">隐私政策</a> | <a href="#">聯繫我們</a></p>
</footer>

<!-- 引入Bootstrap JS和jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
