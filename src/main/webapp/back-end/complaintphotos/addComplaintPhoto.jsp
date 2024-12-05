<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>新增申訴照片</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">新增申訴照片</h1>
    <form action="${pageContext.request.contextPath}/back-end/complaintphotos/complaintphotos.do"
          method="post"
          enctype="multipart/form-data"
          class="p-4 border rounded shadow">
        <input type="hidden" name="action" value="upload">
        <div class="mb-3">
            <label for="comId" class="form-label">申訴編號:</label>
            <input type="number" id="comId" name="comId" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="photos" class="form-label">上傳圖片:</label>
            <input type="file" id="photos" name="photos" multiple class="form-control" accept="image/*" required>
        </div>
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-success">新增</button>
            <a href="${pageContext.request.contextPath}/back-end/complaintphotos/complaintphotos.do?action=list&page=1"
               class="btn btn-secondary">返回列表</a>
        </div>
    </form>

    <!-- 顯示成功訊息 -->
    <c:if test="${not empty message}">
        <p class="alert alert-success mt-3">${message}</p>
    </c:if>

    <!-- 顯示上傳失敗的文件列表 -->
    <c:if test="${not empty failedFiles}">
        <div class="alert alert-warning mt-3">
            <p>以下文件上傳失敗：</p>
            <ul>
                <c:forEach var="failedFile" items="${failedFiles}">
                    <li>${failedFile}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
</div>
</body>
</html>
