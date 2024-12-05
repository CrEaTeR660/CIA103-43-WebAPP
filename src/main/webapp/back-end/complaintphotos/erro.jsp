<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>錯誤頁面</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center text-danger">錯誤發生</h1>
        <div class="alert alert-danger">
            <p><strong>錯誤訊息：</strong>${errorMessage}</p>
            <p>請稍後再試。</p>
        </div>
        <div class="text-center">
            <a href="${pageContext.request.contextPath}/back-end/complaintphotos/listComplaintPhotos.jsp" class="btn btn-primary">返回列表</a>
        </div>
    </div>
</body>
</html>
