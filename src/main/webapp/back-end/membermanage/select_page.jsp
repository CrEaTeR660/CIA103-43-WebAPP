<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>會員查詢</title>
</head>
<body>
    <h1>會員查詢</h1>

    <!-- 顯示錯誤訊息 -->
    <c:if test="${not empty errorMsg}">
        <p style="color: red;">${errorMsg}</p>
    </c:if>

  <form action="${pageContext.request.contextPath}/back-end/membermanage.do" method="get">
        <label for="memberId">輸入會員編號：</label>
        <input type="text" id="memberId" name="memberId" placeholder="請輸入會員編號" required>
        <input type="hidden" name="action" value="getOne">
        <button type="submit">查詢</button>
    </form>
    <hr>

    <!-- 顯示全部會員 -->
    <h2>顯示全部會員</h2>
    <form action="${pageContext.request.contextPath}/back-end/membermanage.do" method="get">
        <input type="hidden" name="action" value="getAll">
        <button type="submit">顯示全部會員</button>
    </form>
</body>
</html>
